在spark on yarn任务进行时，大家都指导用4040端口监控（默认是，设置其他或者多个任务同时会递增等例外）;
辣么，任务结束了，还要看图形化界面，那就要开history-server了。CDH安装spark on yarn的时候，就自动安装了history的实例。
现在不用CDH自带的spark（版本太久了），自己安装spark新版，所以还得具体配置。
搜了一下帖子，2个步骤：
spark 查看 job history 日志
```
http://blog.csdn.net/stark_summer/article/details/46459701
```

1、spark-defaults.conf 增加如下内容:
```
#History
spark.eventLog.dir=hdfs://mycluster/user/spark/applicationHistory
spark.eventLog.enabled=true
spark.yarn.historyServer.address=http://snn.hadoop:18018
#####################
```

2、spark-env.sh 增加如下内容
```
##History-server
export SPARK_HISTORY_OPTS="-Dspark.history.ui.port=18018 -Dspark.history.fs.logDirectory=hdfs://mycluster/user/spark/applicationHistory"
###################
```
3、strt-history-server.sh 启动即可，查看端口监听，网页浏览，没有问题。

```
[hadoop@snn sbin]$ netstat -tnlp |grep 18018
(Not all processes could be identified, non-owned process info
 will not be shown, you would have to be root to see it all.)
tcp        0      0 :::18018                    :::*                        LISTEN      7791/java
[Hadoop@snn sbin]$
```
--原来之前用CDH Spark的时候配置了HDFS保存日志，现在用外面的版本，之前的日志也能看到。

#########################################################
但是在实践中发现几个问题：有时候yarn中“history”按钮并没有链接过去。而是要自己过去看，这怎么回事。
还有端口我改成了18018，但是还是默认的18080呢？

比较一下任务的“Environment”,发现很大。

原来是spark-env.sh和spark-defaults.conf两个配置，是不同用途的。
1、spark.eventLog.dir 和 spark.history.fs.logDirectory  区别？
设置了 spark.eventLog.dir ，start-history-server.sh 启动后面不带地址，还是使用默认地址：报错（因为本地的目录没有创建），设置spark.history.fs.logDirectory 能不带参数启动。

2、spark.yarn.historyServer.address 和 spark.history.ui.port 区别？
启动：spark.yarn.historyServer.address 设置的端口并没有生效。需要spark.history.ui.port设置才生效。
如果不设置spark.yarn.historyServer.address，虽然直接在history-server中能直接看，但是在完成任务那里点击“History”，不会链接到history-server。在任务的"Environment"中也没看到这个属性。
但是设置了，"Environment"中可以看到这个属性，那么大胆的认为，这个属性在任务运行中会记录下来，后面才可以链接。

总结：也就是 spark-env.sh 里面的 SPARK_HISTORY_OPTS 才是设置 history-server 启动的配置。
辣么 spark-defaults.conf 这里面设置神马用？任务中让yarn RM知道这些配置，给后面的链接用。
