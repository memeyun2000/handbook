一、首先准备需要安装的软件
scala-2.10.4
下载地址：http://www.scala-lang.org/download/2.10.4.html
scala-SDK-4.4.1-vfinal-2.11-win32.win32.x86_64
下载地址：http://scala-ide.org/
spark-1.6.2-bin-hadoop2.6
下载地址：http://spark.apache.org/
当然还有jdk这里就不说了
scala-2.10.4下载后直接安装~
scala-SDK-4.4.1-vfinal-2.11-win32.win32.x86_64和spark-1.6.2-bin-hadoop2.6下载后直接解压
scalaIDE解压后就是一个eclipse，这个大家都比较熟悉了。打开IDE，在解压后的spark包中的lib文件夹下找到spark-assembly-1.6.2-hadoop2.6.0，添加到IDE中。
然后环境就搭建完成了~
下面就来开发一个测试程序试一下：

```scala
package com.day1.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object WordCount {
  def  main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setAppName("MY frist Spark App")
    conf.setMaster("local")

    var sc = new SparkContext(conf)

    val lines = sc.textFile("D:\\test.txt", 1)

    val words = lines.flatMap { line => line.split(" ") }

    val pairs = words.map { word => (word,1) }

    val WordCount = pairs.reduceByKey(_+_)

    WordCount.foreach(wordNumberPair=>println(wordNumberPair._1 + ":" + wordNumberPair._2))

    sc.stop()
  }
}
```

右键运行：
```shell
Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties
16/07/18 19:07:04 INFO SparkContext: Running Spark version 1.6.216/07/18 19:07:13 INFO SecurityManager: Changing view acls to: qinchaofeng
16/07/18 19:07:13 INFO SecurityManager: Changing modify acls to: qinchaofeng
16/07/18 19:07:13 INFO SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users with view permissions: Set(qinchaofeng); users with modify permissions: Set(qinchaofeng)
16/07/18 19:07:14 INFO Utils: Successfully started service 'sparkDriver' on port 50513.16/07/18 19:07:14 INFO Slf4jLogger: Slf4jLogger started
16/07/18 19:07:14 INFO Remoting: Starting remoting
16/07/18 19:07:14 INFO Remoting: Remoting started; listening on addresses :[akka.tcp://sparkDriverActorSystem@192.168.108.207:50526]
16/07/18 19:07:14 INFO Utils: Successfully started service 'sparkDriverActorSystem' on port 50526.16/07/18 19:07:14 INFO SparkEnv: Registering MapOutputTracker
16/07/18 19:07:14 INFO SparkEnv: Registering BlockManagerMaster
16/07/18 19:07:14 INFO DiskBlockManager: Created local directory at C:\Users\qinchaofeng\AppData\Local\Temp\blockmgr-25c047c4-505d-4cfb-addd-d777e5a430d7
16/07/18 19:07:14 INFO MemoryStore: MemoryStore started with capacity 797.6 MB
16/07/18 19:07:14 INFO SparkEnv: Registering OutputCommitCoordinator
16/07/18 19:07:15 INFO Utils: Successfully started service 'SparkUI' on port 4040.16/07/18 19:07:15 INFO SparkUI: Started SparkUI at http://192.168.108.207:404016/07/18 19:07:15 INFO Executor: Starting executor ID driver on host localhost
16/07/18 19:07:15 INFO Utils: Successfully started service 'org.apache.spark.network.netty.NettyBlockTransferService' on port 50533.16/07/18 19:07:15 INFO NettyBlockTransferService: Server created on 5053316/07/18 19:07:15 INFO BlockManagerMaster: Trying to register BlockManager
16/07/18 19:07:15 INFO BlockManagerMasterEndpoint: Registering block manager localhost:50533 with 797.6 MB RAM, BlockManagerId(driver, localhost, 50533)
16/07/18 19:07:15 INFO BlockManagerMaster: Registered BlockManager
16/07/18 19:07:15 INFO MemoryStore: Block broadcast_0 stored as values in memory (estimated size 153.6 KB, free 153.6 KB)
16/07/18 19:07:15 INFO MemoryStore: Block broadcast_0_piece0 stored as bytes in memory (estimated size 13.9 KB, free 167.5 KB)
16/07/18 19:07:15 INFO BlockManagerInfo: Added broadcast_0_piece0 in memory on localhost:50533 (size: 13.9 KB, free: 797.6 MB)
16/07/18 19:07:15 INFO SparkContext: Created broadcast 0 from textFile at WordCount.scala:1516/07/18 19:07:16 WARN : Your hostname, qinchaofeng1 resolves to a loopback/non-reachable address: fe80:0:0:0:0:5efe:c0a8:6ccf%12, but we couldn't find any external IP address!
16/07/18 19:07:24 INFO FileInputFormat: Total input paths to process : 116/07/18 19:07:24 INFO SparkContext: Starting job: foreach at WordCount.scala:2316/07/18 19:07:24 INFO DAGScheduler: Registering RDD 3 (map at WordCount.scala:19)
16/07/18 19:07:24 INFO DAGScheduler: Got job 0 (foreach at WordCount.scala:23) with 1 output partitions
16/07/18 19:07:24 INFO DAGScheduler: Final stage: ResultStage 1 (foreach at WordCount.scala:23)
16/07/18 19:07:24 INFO DAGScheduler: Parents of final stage: List(ShuffleMapStage 0)
16/07/18 19:07:24 INFO DAGScheduler: Missing parents: List(ShuffleMapStage 0)
16/07/18 19:07:24 INFO DAGScheduler: Submitting ShuffleMapStage 0 (MapPartitionsRDD[3] at map at WordCount.scala:19), which has no missing parents
16/07/18 19:07:24 INFO MemoryStore: Block broadcast_1 stored as values in memory (estimated size 4.1 KB, free 171.6 KB)
16/07/18 19:07:25 INFO MemoryStore: Block broadcast_1_piece0 stored as bytes in memory (estimated size 2.3 KB, free 173.8 KB)
16/07/18 19:07:25 INFO BlockManagerInfo: Added broadcast_1_piece0 in memory on localhost:50533 (size: 2.3 KB, free: 797.6 MB)
16/07/18 19:07:25 INFO SparkContext: Created broadcast 1 from broadcast at DAGScheduler.scala:100616/07/18 19:07:25 INFO DAGScheduler: Submitting 1 missing tasks from ShuffleMapStage 0 (MapPartitionsRDD[3] at map at WordCount.scala:19)
16/07/18 19:07:25 INFO TaskSchedulerImpl: Adding task set 0.0 with 1 tasks
16/07/18 19:07:25 INFO TaskSetManager: Starting task 0.0 in stage 0.0 (TID 0, localhost, partition 0,PROCESS_LOCAL, 2108 bytes)
16/07/18 19:07:25 INFO Executor: Running task 0.0 in stage 0.0 (TID 0)
16/07/18 19:07:25 INFO HadoopRDD: Input split: file:/D:/test.txt:0+21416/07/18 19:07:25 INFO deprecation: mapred.tip.id is deprecated. Instead, use mapreduce.task.id
16/07/18 19:07:25 INFO deprecation: mapred.task.id is deprecated. Instead, use mapreduce.task.attempt.id
16/07/18 19:07:25 INFO deprecation: mapred.task.is.map is deprecated. Instead, use mapreduce.task.ismap
16/07/18 19:07:25 INFO deprecation: mapred.task.partition is deprecated. Instead, use mapreduce.task.partition
16/07/18 19:07:25 INFO deprecation: mapred.job.id is deprecated. Instead, use mapreduce.job.id
16/07/18 19:07:25 INFO Executor: Finished task 0.0 in stage 0.0 (TID 0). 2253 bytes result sent to driver
16/07/18 19:07:25 INFO TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 156 ms on localhost (1/1)
16/07/18 19:07:25 INFO TaskSchedulerImpl: Removed TaskSet 0.0, whose tasks have all completed, from pool
16/07/18 19:07:25 INFO DAGScheduler: ShuffleMapStage 0 (map at WordCount.scala:19) finished in 0.156 s
16/07/18 19:07:25 INFO DAGScheduler: looking for newly runnable stages
16/07/18 19:07:25 INFO DAGScheduler: running: Set()
16/07/18 19:07:25 INFO DAGScheduler: waiting: Set(ResultStage 1)
16/07/18 19:07:25 INFO DAGScheduler: failed: Set()
16/07/18 19:07:25 INFO DAGScheduler: Submitting ResultStage 1 (ShuffledRDD[4] at reduceByKey at WordCount.scala:21), which has no missing parents
16/07/18 19:07:25 INFO MemoryStore: Block broadcast_2 stored as values in memory (estimated size 2.5 KB, free 176.3 KB)
16/07/18 19:07:25 INFO MemoryStore: Block broadcast_2_piece0 stored as bytes in memory (estimated size 1583.0 B, free 177.9 KB)
16/07/18 19:07:25 INFO BlockManagerInfo: Added broadcast_2_piece0 in memory on localhost:50533 (size: 1583.0 B, free: 797.6 MB)
16/07/18 19:07:25 INFO SparkContext: Created broadcast 2 from broadcast at DAGScheduler.scala:100616/07/18 19:07:25 INFO DAGScheduler: Submitting 1 missing tasks from ResultStage 1 (ShuffledRDD[4] at reduceByKey at WordCount.scala:21)
16/07/18 19:07:25 INFO TaskSchedulerImpl: Adding task set 1.0 with 1 tasks
16/07/18 19:07:25 INFO TaskSetManager: Starting task 0.0 in stage 1.0 (TID 1, localhost, partition 0,NODE_LOCAL, 1894 bytes)
16/07/18 19:07:25 INFO Executor: Running task 0.0 in stage 1.0 (TID 1)
16/07/18 19:07:25 INFO ShuffleBlockFetcherIterator: Getting 1 non-empty blocks out of 1 blocks
16/07/18 19:07:25 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 16 ms
avg_flow:1
avg_flow_fee:1
local_city_nm:1
cust_num:1
birthday:1
Gender:1
cust_nm:1
join_net_time:1
avg_call_fee:1
net_age:1
prov_code:1
cmpgn:1
buy_time:1
using_meal:1
chnl:1
arpu:1
term_brand:1
term_model:1
avg_call_times:1
flow_pkg:116/07/18 19:07:25 INFO Executor: Finished task 0.0 in stage 1.0 (TID 1). 1165 bytes result sent to driver
16/07/18 19:07:25 INFO DAGScheduler: ResultStage 1 (foreach at WordCount.scala:23) finished in 0.062 s
16/07/18 19:07:25 INFO TaskSetManager: Finished task 0.0 in stage 1.0 (TID 1) in 62 ms on localhost (1/1)
16/07/18 19:07:25 INFO TaskSchedulerImpl: Removed TaskSet 1.0, whose tasks have all completed, from pool
16/07/18 19:07:25 INFO DAGScheduler: Job 0 finished: foreach at WordCount.scala:23, took 0.356812 s
16/07/18 19:07:25 INFO SparkUI: Stopped Spark web UI at http://192.168.108.207:404016/07/18 19:07:25 INFO MapOutputTrackerMasterEndpoint: MapOutputTrackerMasterEndpoint stopped!
16/07/18 19:07:25 INFO MemoryStore: MemoryStore cleared
16/07/18 19:07:25 INFO BlockManager: BlockManager stopped
16/07/18 19:07:25 INFO BlockManagerMaster: BlockManagerMaster stopped
16/07/18 19:07:25 INFO OutputCommitCoordinator$OutputCommitCoordinatorEndpoint: OutputCommitCoordinator stopped!
16/07/18 19:07:25 INFO SparkContext: Successfully stopped SparkContext
16/07/18 19:07:25 INFO ShutdownHookManager: Shutdown hook called
16/07/18 19:07:25 INFO RemoteActorRefProvider$RemotingTerminator: Shutting down remote daemon.
16/07/18 19:07:25 INFO ShutdownHookManager: Deleting directory C:\Users\qinchaofeng\AppData\Local\Temp\spark-c54f4cb1-7351-47d1-9fd4-fbdac0fae228

```
