
错误描述

```

本地单节点 启动spark-shell

spark-shell --master yarn 时


在yarn的监控中报错
error .... RECEIVED SIGNAL TERM

```



> 可能是因为java8 的内存分配问题


[以下是stackoverflow中查找的解决办法：](http://stackoverflow.com/questions/29512565/spark-pi-example-in-cluster-mode-with-yarn-association-lost)

The association may be lost due to the Java 8 excessive memory allocation issue: https://issues.apache.org/jira/browse/YARN-4714

You can force YARN to ignore this by setting up the following properties in yarn-site.xml

```
<property>
    <name>yarn.nodemanager.pmem-check-enabled</name>
    <value>false</value>
</property>

<property>
    <name>yarn.nodemanager.vmem-check-enabled</name>
    <value>false</value>
</property>

```
