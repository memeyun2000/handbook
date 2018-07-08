# pandas 读取 csv 文件
#%%
import pandas as pd 
df = pd.read_csv("/share/handbook/Python/study/resources/test.csv",header=None)
print(df.head())


# 设置字段名  
#%%
df = pd.read_csv("/share/handbook/Python/study/resources/test.csv"
        ,header=None
        ,names=["col1","col2","col3","col4","col5"])

print(df.head())


# 分组统计
#%%
df.groupby(["col1","col2"]).count()