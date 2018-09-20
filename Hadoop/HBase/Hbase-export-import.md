> hbase export

```shell
$HBASE_HOME/bin/hbase org.apache.hadoop.hbase.mapreduce.Export TableName(导出的表名) hdfs://host:8020/user/hbase/TableName_HDFS(导出到HDFS目录)

#
$HBASE_HOME/bin/hbase org.apache.hadoop.hbase.mapreduce.Export
-Dhbase.client.scanner.caching=1000
-Dhbase.export.scanner.batch=10 TableName(导出的表名) hdfs://host:8020/user/hbase/TableName_HDFS(导出到HDFS目录)

```


> hbase import

```shell
$HBASE_HOME/bin/hbase org.apache.hadoop.hbase.mapreduce.Import TableName(表名) /user/hbase/TableName_HDFS
```
