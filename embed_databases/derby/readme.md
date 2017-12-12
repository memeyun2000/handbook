> 设置环境变量

```
CLASSPATH 加入 derby.jar derbytools.jar

进入derby cmd
java org.apache.derby.tools.ij


创建数据库
connect 'jdbc:derby:mydbname;create=true';

断开连接
disconnect

```