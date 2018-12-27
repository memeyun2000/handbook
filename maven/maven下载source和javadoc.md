> maven 下载 source 和 javadoc

```
mvn dependency:sources
mvn dependency:resolve -Dclassifier=javadoc
```


```mermaid
graph LR;
    A-->B;
    A-->C;
    B-->D;
    C-->D;

```