# zeppelin 学习

## 编译

编译环境：Oracle JDK7.x、maven3.x+、Node.js(npm)

Git源码地址：https://github.com/apache/zeppelin.git(注意不要再从如下旧的git地址更新了https://github.com/apache/incubator-zeppelin.git）

以下将${ZEPPELIN_HOME}指代git clone之后源码的根目录。使用如下maven命令编译并生成zeppelin的发布包：


```
mvn install -Pbuild-distr -Pspark-1.6 -Dspark.version=1.6.2 -Phadoop-2.7 -Dhadoop.version=2.7.2  -Psparkr -Ppyspark -Dmaven.findbugs.enable=false -Drat.skip=true -Dcheckstyle.skip=true -Denforcer.skip=true -Dcobertura.skip=true -DskipTests -X
```

解释：

1)      执行install是为了将artifact安装到maven本地仓库，便于之后import到IDE进行调试。

2)      -Pbuild-distr，表示启用build-distr这个maven profile是为了在zeppelin-distribution这个module中生成可分发包。

3)      [-Pr|-Psparkr]需要注意，表示这2个profile只能启用一个，这2个profile编译出来的Interpreter会bind到相同的名字%r，造成冲突（参见zeppelin的mailinglist）。–Pr，表示启用r这个mavenprofile，会将${ZEPPELIN_HOME}/r作为一个module参与编译，编译完成得到sparkR Interpreter。运行zeppelin自带的R Tutorial这个Note，需要改Interpreter。-Pspark-1.6这个profile并不会使得sparkr这个Interpreter被编译打包，因为该Interpreter的编译在${ZEPPELIN_HOME}/zeppelin-spark/pom.xml中采用id=exclude-sparkr，activeByDefault=true的profile默认禁用掉了。如下：
