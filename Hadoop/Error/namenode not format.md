
原因:每次namenode format会重新创建一个namenodeId,而dfs.data.dir参数配置的目录中包含的是上次format创建的id,和dfs.name.dir参数配置的目录中的id不一致。namenode format清空了namenode下的数据,但是没有清空datanode下的数据,导致启动时失败,所要做的就是每次fotmat前,清空dfs.data.dir参数配置的目录.
格式化hdfs的命令
Shell代码  收藏代码

```
hadoop namenode -format  
```
