> 复制本地文件到hdfs

```
[hadoop@hadoop1 input]$ hadoop dfs -copyFromLocal /home/hadoop/input/regression_data.txt /in
DEPRECATED: Use of this script to execute hdfs command is deprecated.
Instead use the hdfs command for it.

```

> 和put参数的效果是一样的

```
 hadoop dfs -put /home/hadoop/input/regression_data.txt /in
```
