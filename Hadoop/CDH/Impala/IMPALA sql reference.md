> 收集统计信息

```
compute stats {TableName}

```

> 更改表名

```

alter table {TableName} rename to {newTableName}

```

> 刷新元数据

```

//刷新所有的元数据？
invalidate metadata

或
//刷新一个表的元数据
invalidate metadata [TableName]

```

> 尽量不使用 invalidate metadata 而是使用refresh

```
refresh TableName
```
