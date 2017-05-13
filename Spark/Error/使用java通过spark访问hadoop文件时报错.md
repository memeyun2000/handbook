
报错信息：
```
15/05/30 09:51:57 INFO executor.CoarseGrainedExecutorBackend: Registered signal handlers for [TERM, HUP, INT]
15/05/30 09:51:57 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
15/05/30 09:51:58 INFO spark.SecurityManager: Changing view acls to: hadoop15/05/30 09:51:58 INFO spark.SecurityManager: Changing modify acls to: hadoop
15/05/30 09:51:58 INFO spark.SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users with view permissions: Set(hadoop); users with modify permissions: Set(hadoop)
15/05/30 09:51:58 INFO slf4j.Slf4jLogger: Slf4jLogger started
15/05/30 09:51:58 INFO Remoting: Starting remoting
15/05/30 09:51:58 INFO Remoting: Remoting started; listening on addresses :[akka.tcp://driverPropsFetcher@hadoop1:60396]
15/05/30 09:51:58 INFO util.Utils: Successfully started service 'driverPropsFetcher' on port 60396.
15/05/30 09:51:58 WARN remote.ReliableDeliverySupervisor: Association with remote system [akka.tcp://sparkDriver@192.168.23.2:53519] has failed, address is now gated for [5000] ms. Reason is: [Association failed with [akka.tcp://sparkDriver@192.168.23.2:53519]].
Exception in thread "main" java.lang.reflect.UndeclaredThrowableException
	at org.apache.hadoop.security.UserGroupInformation.doAs(UserGroupInformation.java:1563)
	at org.apache.spark.deploy.SparkHadoopUtil.runAsSparkUser(SparkHadoopUtil.scala:60)
	at org.apache.spark.executor.CoarseGrainedExecutorBackend$.run(CoarseGrainedExecutorBackend.scala:115)
	at org.apache.spark.executor.CoarseGrainedExecutorBackend$.main(CoarseGrainedExecutorBackend.scala:163)
	at org.apache.spark.executor.CoarseGrainedExecutorBackend.main(CoarseGrainedExecutorBackend.scala)
Caused by: java.util.concurrent.TimeoutException: Futures timed out after [30 seconds]
	at scala.concurrent.impl.Promise$DefaultPromise.ready(Promise.scala:219)
	at scala.concurrent.impl.Promise$DefaultPromise.result(Promise.scala:223)
	at scala.concurrent.Await$$anonfun$result$1.apply(package.scala:107)
	at scala.concurrent.BlockContext$DefaultBlockContext$.blockOn(BlockContext.scala:53)
	at scala.concurrent.Await$.result(package.scala:107)
	at org.apache.spark.executor.CoarseGrainedExecutorBackend$$anonfun$run$1.apply$mcV$sp(CoarseGrainedExecutorBackend.scala:127)
	at org.apache.spark.deploy.SparkHadoopUtil$

```

原因：
主要是本地搭建了虚拟机的网口，又联结了无线网络，导致服务器端无法识别信息来源。（推测）
服务器返回信息返回到客户端的ip地址有问题：

如图akka在找
sec-ThinkPad-T440s
这个字符串对应的ip地址

如果找不到地址就会报超时的错误

解决办法：
这里的解决办法，只是一个临时的方法：
1. 在客户端hosts文件设置 172.16.3.1      sec-ThinkPad-T440s
2. 在服务端hosts文件设置 172.16.3.1      sec-ThinkPad-T440s

ps：尝试了一下只在去除在客户端的设置，还是会报错。如图：
