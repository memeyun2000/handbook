# 下载依赖包


> 新建一个requirement.txt 

```txt
certifi==2019.3.9
chardet==3.0.4
get==2019.3.22
idna==2.8
Pillow==6.0.0
pip==9.0.1
post==2019.3.22
public==2019.3.22
query-string==2019.3.22
request==2019.3.22
requests==2.21.0
requests-toolbelt==0.9.1
setuptools==28.8.0
urllib3==1.24.1

```


```shell
pip3 download  -d /apps/soft/python/package -r requirement.txt -i http://pypi.douban.com/simple/ --trusted-host pypi.douban.com 
```