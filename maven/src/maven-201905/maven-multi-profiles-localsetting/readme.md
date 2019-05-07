# 多个profile的文件 打包命令：

```
# 本地.m2/setting.xml 可以配置
# 这里 property environment.type 变量名称可以是任意的这里仅作为示例而已
# 安全保障 ： 可以在本地 setting.xml 中添加属性 如database.password 保证生产与测试数据隔离
<profiles>
    <profile>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <environment.type>dev</environment.type>
        </properties>
    </profile>
</profiles>
```


# 或者 使用 命令 

```
mvn package -Denvironment.type=prod
```
