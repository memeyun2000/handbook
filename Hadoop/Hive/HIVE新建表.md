--textfile表
```sql
CREATE TABLE geolocation_stage (truckid string, driverid string, event string, latitude DOUBLE, longitude DOUBLE, city string, state string, velocity BIGINT, event_ind BIGINT, idling_ind BIGINT)
ROW FORMAT DELIMITED      --行分隔符
FIELDS TERMINATED BY ','  --字段分隔符
STORED AS TEXTFILE        -- 存储方式
TBLPROPERTIES ("skip.header.line.count"="1");  --自查未知
```
--关联hdfs表
```sql
CREATE TABLE geolocation_stage (truckid string, driverid string, event string, latitude DOUBLE, longitude DOUBLE, city string, state string, velocity BIGINT, event_ind BIGINT, idling_ind BIGINT)
ROW FORMAT DELIMITED      --行分隔符
FIELDS TERMINATED BY ','  --字段分隔符
LOCATION '/XXX/XXX'
```


--ORC表
```sql
CREATE TABLE … **STORED AS ORC**
```

--ORC表
```sql
CREATE TABLE geolocation STORED AS ORC AS SELECT * FROM geolocation_stage;
```

```sql
--show information of table
describe formatted TABLENAME;
# col_namedata_typecommentnullnullproduct_idstringproduct_namestringproduct_versionstringnullnull# Detailed Table InformationnullnullDatabase:defaultnullOwner:adminnullCreateTime:Wed Jul 20 15:56:12 UTC 2016nullLastAccessTime:UNKNOWNnullProtect Mode:NonenullRetention:0nullLocation:hdfs://sandbox.hortonworks.com:8020/apps/hive/warehouse/productnullTable Type:MANAGED_TABLEnullTable Parameters:nullnullCOLUMN_STATS_ACCURATEtruenumFiles1numRows3rawDataSize795totalSize468transient_lastDdlTime1469030174nullnull# Storage InformationnullnullSerDe Library:org.apache.hadoop.hive.ql.io.orc.OrcSerdenullInputFormat:org.apache.hadoop.hive.ql.io.orc.OrcInputFormatnullOutputFormat:org.apache.hadoop.hive.ql.io.orc.OrcOutputFormatnullCompressed:NonullNum Buckets:-1nullBucket Columns:[]nullSort Columns:[]nullStorage Desc Params:nullnullserialization.format1
```
