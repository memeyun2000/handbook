> 查询表

```

//limit
//查询前200行数据
scan TableName ,{LIMIT => 200}

//前缀查询
scan TableName , {FILTER => org.apache.hadoop.hbase.filter.PrefixFilter.new(org.apache.hadoop.hbase.util.Bytes.toBytes('Prefix'))}

scan TableName , {FILTER=>"PrefixFilter('helloworld')"}


//取rowkey 记录
get 'TableName','Rowkey'

//取某一Rowkey 记录的某一字段
get 'TableName','rowkey','columnFamily:column'


//清空表
truncate 'TableName'

//删除表
disable 'TableName'
drop 'TableName'

```
