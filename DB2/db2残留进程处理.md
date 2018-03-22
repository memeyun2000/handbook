1. 实例停止后发现 db2wdog 进程仍然存在，端口依然占用

```
db2_kill

ipclean

db2start

```

ipclean 后发现db2进程已经不存在，端口也释放了。
