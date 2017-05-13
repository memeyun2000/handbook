SparkSQL这块儿从1.4开始支持了很多的窗口分析函数，像row_number这些，平时写程序加载数据后用SQLContext 能够很方便实现很多分析和查询,如下
```java
val sqlContext = new SQLContext(sc)
sqlContext.sql(“select ….”)
```
然而我看到Spark后续版本的DataFrame功能很强大，想试试使用这种方式来实现比如row_number这种功能，话不多说，快速用pyspark测试一下，记录一下遇到的问题.

```python
from pyspark.sql import Row, functions as F
from pyspark.sql.window import Window
from pyspark import SparkContext
sc = SparkContext("local[3]", "test data frame on 2.0")
testDF = sc.parallelize( (Row(c="class1", s=50), Row(c="class2", s=40), Row(c="class3", s=70), Row(c="class2", s=49), Row(c="class3", s=29), Row(c="class1", s=78) )).toDF()
(testDF.select("c", "s", F.rowNumber().over(Window.partitionBy("c").orderBy("s")).alias("rowNum") ).show())
```

spark-submit提交任务后直接报错如下

告诉我RDD没有toDF()属性，查阅spark官方文档得知还是需要用SQLContext或者sparkSession来初始化一下,先考虑用SQLContext吧，修改代码如下

```python
from pyspark.sql import Row, functions as F
from pyspark.sql.window import Window
from pyspark import SparkContext
from pyspark.sql import SQLContext
sc = SparkContext("local[3]", "test data frame on 2.0")
rddData = sc.parallelize( (Row(c="class1", s=50), Row(c="class2", s=40), Row(c="class3", s=70), Row(c="class2", s=49), Row(c="class3", s=29), Row(c="class1", s=78)))
sqlContext = SQLContext(sc)
testDF = rddData.toDF()
(testDF.select("c", "s", F.rowNumber().over(Window.partitionBy("c").orderBy("s")).alias("rowNum") ).show())
```

spark-submit提交任务后接着报另外一个错，如下

ok,错误很清楚,rowNumber这里我写错了，没有这个函数，查阅spark源码中的functions.py，会发现如下说明

这里说了，rowNumber从1.6开始，用row_number代替，直接修改py脚本如下
```python
from pyspark.sql import Row, functions as F
from pyspark.sql.window import Window
from pyspark import SparkContext
from pyspark.sql import SQLContext
sc = SparkContext("local[3]", "test data frame on 2.0")
rddData = sc.parallelize( (Row(c="class1", s=50), Row(c="class2", s=40), Row(c="class3", s=70), Row(c="class2", s=49), Row(c="class3", s=29), Row(c="class1", s=78)))
sqlContext = SQLContext(sc)
testDF = rddData.toDF()
(testDF.select("c", "s", F.row_number().over(Window.partitionBy("c").orderBy("s")).alias("rowNum") ).show())
```
这次运行没问题，结果如下

但是我只想取每组rowNum为1的那个，代码如下
```python
from pyspark.sql import Row, functions as F
from pyspark.sql.window import Window
from pyspark import SparkContext
from pyspark.sql import SQLContext
sc = SparkContext("local[3]", "test data frame on 2.0")
rddData = sc.parallelize( (Row(c="class1", s=50), Row(c="class2", s=40), Row(c="class3", s=70), Row(c="class2", s=49), Row(c="class3", s=29), Row(c="class1", s=78)))
sqlContext = SQLContext(sc)
testDF = rddData.toDF()
result = (testDF.select("c", "s", F.row_number().over(Window.partitionBy("c").orderBy("s")).alias("rowNum")))
finalResult = result.where(result.rowNum <= 1).show()
```

可以看到,sql能实现的DataFrame的函数都可以实现，毕竟DataFrame是基于row和column的，就是写起来麻烦点.
