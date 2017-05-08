可能是因为访问的jobtrack端口不对。
可以使用netstat -netult | grep port 查看端口是否启动
```shell
[oozie@sandbox ~]$ netstat -netult | grep 8021
[oozie@sandbox ~]$ netstat -netult | grep 8020
tcp        0      0 192.168.74.128:8020         0.0.0.0:*                   LISTEN      507        18607      
[oozie@sandbox ~]$

```

查阅 "报错信息20160818" 笔记 详细描述了 jobtrack 端口指向错误的报错日期，以及处理方法
