# pandas 读取 csv 文件
#%%
import pandas as pd 
df = pd.read_csv("/share/handbook/Python/study/resources/test.csv",header=None)
print(df.head())


# 设置字段名  
#%%
import pandas as pd 
df = pd.read_csv("/share/handbook/Python/study/resources/test.csv"
        ,header=None
        ,names=["col1","col2","col3","col4","col5"])

print(df.head())


# 分组统计
#%%
import pandas as pd 
df = pd.read_csv("/share/handbook/Python/study/resources/test.csv"
        ,header=None
        ,names=["col1","col2","col3","col4","col5"])
df.groupby(["col1","col2"]).count()


# 使用 pandas 创建数据表
#%%
import pandas as pd 
import numpy as np 
df = pd.DataFrame({"id":[1001,1002,1003,1004,1005,1006], 
 "date":pd.date_range('20130102', periods=6),
  "city":['Beijing ', 'SH', ' guangzhou ', 'Shenzhen', 'shanghai', 'BEIJING '],
 "age":[23,44,54,32,34,32],
 "category":['100-A','100-B','110-A','110-C','210-A','130-F'],
  "price":[1200,np.nan,2133,5433,np.nan,4432]},
  columns =['id','date','city','category','age','price'])
print(df.head())
# 维度查看
print(df.shape)
# 数据表基本信息
print(df.info())
# 查看每一列的数据类型
print(df.dtypes)
# 查看某一列的数据类型
print(df.dtypes["id"])
print(df["price"].dtype)
# 空值
print(df.isnull())
# 某一列空值
print(df["price"].isnull())
# 某一列的唯一值
print(df["id"].unique())
# 查看列名
print(df.columns)
# 清除city字段的空格
df['city'] = df['city'].map(str.strip)
print(df.head())


# df使用 2
#%%
import pandas as pd 
import numpy as np 
df = pd.DataFrame({"id":[1001,1002,1003,1004,1005,1006], 
 "date":pd.date_range('20130102', periods=6),
  "city":['Beijing ', 'SH', ' guangzhou ', 'Shenzhen', 'shanghai', 'BEIJING '],
 "age":[23,44,54,32,34,32],
 "category":['100-A','100-B','110-A','110-C','210-A','130-F'],
  "price":[1200,np.nan,2133,5433,np.nan,4432]},
  columns =['id','date','city','category','age','price'])
# 大小写转换
df['city'] = df['city'].map(str.lower)
df['city'] = df['city'].str.lower()
print(df.head())
# 更改数据格式
df['age'].astype('int')
print(df.head())
# 更改列名
df2 = df.rename(columns={'category':'cate-gory'})
print(df2.head())
# 更改列名的另一种方法
df3 = df['category'].rename('cate-goryaaa')
print(df3.head())

#%%
import pandas as pd 
import numpy as np 
df = pd.DataFrame({"id":[1001,1002,1003,1004,1005,1006], 
 "date":pd.date_range('20130102', periods=6),
  "city":['Beijing', 'SH', ' guangzhou ', 'Shenzhen', 'shanghai', 'Beijing'],
 "age":[23,44,54,32,34,32],
 "category":['100-A','100-B','110-A','110-C','210-A','130-F'],
  "price":[1200,np.nan,2133,5433,np.nan,4432]},
  columns =['id','date','city','category','age','price'])
# 删除先出现的重复值
print(df)
df2 = df['city'].drop_duplicates()
print(df2)
# 删除后出现的重复值
# df['city'].drop_duplicates(keep='last')


# https://blog.csdn.net/liufang0001/article/details/77856255




