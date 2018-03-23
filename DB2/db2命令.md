> 查看表空间统计

```shell

db2pd -db <database_name> -tablespace

```

> 查看数据库下表空间

```shell

db2 list tablespaces

```

> 查看表空间下容器

```shell

db2 list tablespace containers for <tablespace id> show detail

```

> 查看db2数据库信息
```shell

db2 list db directory

```

> 查看db2实例配置

```shell

db2 get dbm cfg 

db2 get dmb cfg show detail

```
