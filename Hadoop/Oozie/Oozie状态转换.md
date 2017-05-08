
在Oozie中，工作流的状态可能存在如下几种：
| 状态  | 含义说明  |
|---|---|
|  PREP |  一个工作流Job第一次创建将处于PREP状态，表示工作流Job已经定义，但是没有运行。 |
|RUNNING|当一个已经被创建的工作流Job开始执行的时候，就处于RUNNING状态。它不会达到结束状态，只能因为出错而结束，或者被挂起。|
|SUSPENDED|一个RUNNING状态的工作流Job会变成SUSPENDED状态，而且它会一直处于该状态，除非这个工作流Job被重新开始执行或者被杀死。|
|SUCCEEDED|当一个RUNNING状态的工作流Job到达了end节点，它就变成了SUCCEEDED最终完成状态。|
|KILLED|当一个工作流Job处于被创建后的状态，或者处于RUNNING、SUSPENDED状态时，被杀死，则工作流Job的状态变为KILLED状态。|
|FAILED|当一个工作流Job不可预期的错误失败而终止，就会变成FAILED状态。|


上述各种状态存在相应的转移（工作流程因为某些事件，可能从一个状态跳转到另一个状态），其中合法的状态转移有如下几种，如下表所示：

| 转移前状态  |  转移后状态集合 |
|---|---|
| 未启动  |  PREP |
|PREP|RUNNING、KILLED|
|RUNNING|SUSPENDED、SUCCEEDED、KILLED、FAILED|
|SUSPENDED|RUNNING、KILLED|
