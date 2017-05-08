
```python
"""SimpleApp.py"""
import sys
from pyspark import SparkContext

if len(sys.argv) != 2:
   print("args length error!")
   exit(-1)

logFile = sys.argv[1]  # Should be some file on your system
sc = SparkContext("local", "Simple App")
logData = sc.textFile(logFile).cache()

numAs = logData.filter(lambda s: '|' in s).count()
numBs = logData.filter(lambda s: '9' in s).count()

print("Lines with a: %i, lines with b: %i" % (numAs, numBs))


调用执行：
#其中第二个参数如果加"/"代表HDFS下绝对路径 不加"/"代表 "/home/user" 下的相对路径
spark-submit SimpleApp.py /demo/data/Customer/acct.txt
```
