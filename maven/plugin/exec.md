## 使用maven exec 插件单独执行java

> 配置pom.xml 申明使用 exec 插件

```xml
<build>
    <plugins>
      <!-- maven exec:exec -->
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.2.1</version>
      </plugin>
    </plugins>
  </build>
```

> 执行mvn 命令 运行 java

```bash
mvn exec:exec -Dexec.executable="java" -Dexec.args="-DsystemProperty1=value1 -DsystemProperty2=value2 -XX:MaxPermSize=256m -classpath %classpath com.yourcompany.app.Main arg1 arg2"
```

> 简化后也可以使用

```bash
mvn exec:exec -Dexec.executable="java" -Dexec.args="-classpath %classpath com.miner.Test1"
```

> 运行java
```
mvn exec:java -Dexec.mainClass="com.vineetmanohar.module.Main" -Dexec.args="arg0 arg1 arg2"
```
