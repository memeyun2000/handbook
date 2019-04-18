> 初始化项目 

```bash
mvn archetype:generate -DgroupId=com.yelbosh.sort -DartifactId=sortcode -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```


> 获取插件完整描述

```bash
$ mvn help:describe -Dplugin=exec -Dfull
```

> 已解决的依赖列表
 
```
mvn dependency:resolve
```

> 已解决的依赖列表 树

```
mvn dependency:tree
```


> 获取当前项目下的全局pom

```
mvn help:effective-pom
```