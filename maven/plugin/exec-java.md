## 使用maven exec 插件单独执行java

> 配置pom.xml 申明使用 exec:java 插件

```xml
<build>
    <plugins>
      <!-- maven exec:java -->
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.2.1</version>
        <executions>
            <execution>
                <goals>
                    <goal>java</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <mainClass>com.miner.Test1</mainClass>
        </configuration>
      </plugin>
    </plugins>
  </build>
```

> 执行mvn 命令 运行 java

```bash
mvn exec:java
```
