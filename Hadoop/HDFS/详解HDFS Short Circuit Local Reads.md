> 详解HDFS Short Circuit Local Reads

Hadoop的一大基本原则是移动计算的开销要比移动数据的开销小。因此，Hadoop通常是尽量移动计算到拥有数据的节点上。这就使得Hadoop中读取数据的客户端DFSClient和提供数据的Datanode经常是在一个节点上，也就造成了很多“Local Reads”。
最初设计的时候，这种Local Reads和Remote Reads（DFSClient和Datanode不在同一个节点）的处理方式都是一样的，也就是都是先由Datanode读取数据，然后再通过RPC把数据传给DFSClient。这样处理是比较简单的，但是性能会受到一些影响，因为需要Datanode在中间做一次中转。本文将介绍针对这个问题的一些优化。
既然DFSClient和数据是在一个机器上面，那么很自然的想法，就是让DFSClient绕开Datanode自己去读取数据，在具体实现上有如下两种方案。
HDFS-2246
在这个JIRA中，工程师们的想法是既然读取数据DFSClient和数据在同一台机器上，那么Datanode就把数据在文件系统中的路径，从什么地方开始读(offset)和需要读取多少(length)等信息告诉DFSClient，然后DFSClient去打开文件自己读取。想法很好，问题在于配置复杂以及安全问题。
首先是配置问题，因为是让DFSClient自己打开文件读取数据，那么就需要配置一个白名单，定义哪些用户拥有访问Datanode的数据目录权限。如果有新用户加入，那么就得修改白名单。需要注意的是，这里是允许客户端访问Datanode的数据目录，也就意味着，任何用户拥有了这个权限，就可以访问目录下其他数据，从而导致了安全漏洞。因此，这个实现已经不建议使用了。
HDFS-347
在Linux中，有个技术叫做Unix Domain Socket。Unix Domain Socket是一种进程间的通讯方式，它使得同一个机器上的两个进程能以Socket的方式通讯。它带来的另一大好处是，利用它两个进程除了可以传递普通数据外，还可以在进程间传递文件描述符。
假设机器上的两个用户A和B，A拥有访问某个文件的权限而B没有，而B又需要访问这个文件。借助Unix Domain Socket，可以让A打开文件得到一个文件描述符，然后把文件描述符传递给B，B就能读取文件里面的内容了即使它没有相应的权限。在HDFS的场景里面，A就是Datanode，B就是DFSClient，需要读取的文件就是Datanode数据目录中的某个文件。
这个方案在安全上就比上一个方案上好一些，至少它只允许DFSClient读取它需要的文件。
```
如果你想了解更多关于Unix Domain Socket的知识，可以看看：http://www.thomasstover.com/uds.html 和http://troydhanson.github.io/misc/Unix_domain_sockets.html
```
如何配置
因为Java不能直接操作Unix Domain Socket，所以需要安装Hadoop的native包libhadoop.so。如果你的集群是用各大Hadoop发行版（比如Pivotal HD，CDH等）来安装的，这些native包通常在安装Hadoop的时候会被安装好的。你可以用如下命令来检查这些native包是否安装好。
```shell
[vagrant@c6402 ~]$ hadoop checknative

hadoop: true /usr/lib/hadoop/lib/native/libhadoop.so.1.0.0

zlib:   true /lib64/libz.so.1

snappy: true /usr/lib64/libsnappy.so.1

lz4:    true revision:99

bzip2:  true /lib64/libbz2.so.1

```
> Short Circuit Local Reads相关的配置项（在hdfs-site.xml中）如下：

```xml
  <property>

    <name>dfs.client.read.shortcircuit</name>

    <value>true</value>

  </property>

  <property>

    <name>dfs.domain.socket.path</name>

    <value>/var/lib/hadoop-hdfs/dn_socket</value>

  </property>
```

其中：dfs.client.read.shortcircuit是打开这个功能的开关，dfs.domain.socket.path是Datanode和DFSClient之间沟通的Socket的本地路径。
如何确认配置生效了
按照上面的配置，如何确认从HDFS读取数据的时候，Short Circuit Local Reads真的起作用了？有两个途径：
  1. 查看Datanode的日志
在Datanode的启动日志中，也可以看到如下相关的日志表明Unix Domain Socket被启用了。
```shell
2014-10-17 08:18:59,789 INFO  datanode.DataNode (DataNode.java:<init>(277)) - File descriptor passing is enabled.

...

2014-10-17 08:18:59,867 INFO  datanode.DataNode (DataNode.java:initDataXceiver(579)) - Listening on UNIX domain socket: /var/lib/hadoop-hdfs/dn_socket
```

我们再来读取一个文件看看。在我的测试集群中文件/tmp/hive-0.13.1.phd.3.0.0.0-1.el6.src.rpm及其相关信息如下：
```shell
[hdfs@c6402 ~]$ hdfs dfs -ls /tmp/hive-0.13.1.phd.3.0.0.0-1.el6.src.rpm

-rw-r--r--   3 hdfs hdfs  109028097 2014-10-17 08:31 /tmp/hive-0.13.1.phd.3.0.0.0-1.el6.src.rpm

[hdfs@c6402 ~]$ hdfs fsck /tmp/hive-0.13.1.phd.3.0.0.0-1.el6.src.rpm -files -blocks

Connecting to namenode via http://c6404.ambari.apache.org:50070

FSCK started by hdfs (auth:SIMPLE) from /192.168.64.102 for path /tmp/hive-0.13.1.phd.3.0.0.0-1.el6.src.rpm at Fri Oct 17 08:40:47 UTC 2014

/tmp/hive-0.13.1.phd.3.0.0.0-1.el6.src.rpm 109028097 bytes, 1 block(s):  OK

0. BP-1796216370-192.168.64.104-1413533983834:blk_1073741962_1138 len=109028097 repl=3
```

该文件有一个block，id是：blk_1073741962
现在我把该文件拷贝到本地
```shell
hadoop fs -get /tmp/hive-0.13.1.phd.3.0.0.0-1.el6.src.rpm /tmp
```

然后打开该节点上的Datanode的日志，下面的日志就表明读取block1073741962的时候用到了Short Circuit Local Reads。
2014-10-17 08:32:53,983 INFO  DataNode.clienttrace (DataXceiver.java:requestShortCircuitFds(334)) - src: 127.0.0.1, dest: 127.0.0.1, op: REQUEST_SHORT_CIRCUIT_FDS, blockid: 1073741962, srvID: 4ff4d539-1bca-480d-91e3-e5dc8c6bc4a8, success: true


2 . ReadStatistics API
另外一种方法是通过HdfsDataInputStream的getReadStatistics API来获取读取数据的统计信息。相关实例代码如下：

```java
public class FileSystemCat {

  public static void main(String[] args) throws IOException {

    String uri = args[0];

    Configuration conf = new Configuration();

    FileSystem fs = FileSystem.get(URI.create(uri), conf);

    OutputStream out = new FileOutputStream("/tmp/out");

    FSDataInputStream in = null;

    try {

      in = fs.open(new Path(uri));

      IOUtils.copy(in, out);

      if (in instanceof HdfsDataInputStream) {

        HdfsDataInputStream hdfsIn = (HdfsDataInputStream) in;

        DFSInputStream.ReadStatistics readStatistics = hdfsIn.getReadStatistics();

        System.out.println("Total Bytes Read Bytes: " + readStatistics.getTotalBytesRead());

        System.out.println("Short Circuit Read Bytes: " + readStatistics.getTotalShortCircuitBytesRead());

        System.out.println("Local Read Bytes:" + readStatistics.getTotalLocalBytesRead());

      }

    } finally {

      IOUtils.closeQuietly(in);

      IOUtils.closeQuietly(out);

    }

  }

}
```


我们再来试试：

```shell
[hdfs@c6402 classes]$ hdfs dfs -ls /tmp/hive-0.13.1.phd.3.0.0.0-1.el6.src.rpm

-rw-r--r--   3 hdfs hdfs  109028097 2014-10-17 08:31 /tmp/hive-0.13.1.phd.3.0.0.0-1.el6.src.rpm

[hdfs@c6402 classes]$ hadoop FileSystemCat /tmp/hive-0.13.1.phd.3.0.0.0-1.el6.src.rpm

Total Bytes Read Bytes: 109028097

Short Circuit Read Bytes: 109028097

Local Read Bytes:109028097
```

可以看到所有的数据都是通过Short Circuit Local Read来读取的。
总结
本文介绍了HDFS中Short Circuit Local Reads的两个实现，并详细介绍了基于Unix Domain Socket的配置及其相关知识。
