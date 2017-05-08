
```sql
--从hdfs上导入数据到hive表中
load data inpath '/user/fxjh/pub5/shenjitmpdata/guo_rls_loan_std.txt'  into table guo_rls_loan_lsy_std_orc

--OVERWRITE 关键字会覆盖原有表的数据
LOAD DATA INPATH '/tmp/maria_dev/data/trucks.csv' OVERWRITE INTO TABLE trucks_stage;
```

注意：加载之后会清除hfds上的源文件
