> 窗口跳转

```
1 光标向 上下左右 跳转
ctrl + h
ctrl + j
ctrl + k
ctrl + l

```

> 全局搜索

```

1. 只搜索当前文件 vim /main/ % | copen

2. 只搜索当前目录 vim /main/ * | copen

3.  搜索上级目录下，并递归 vim /main/ ../** | copen

4. 可以在多个路径中搜索  vim /main path1/** path2/** | copen

5. 搜索当前目录及其子目录 vim /main/ ** | copen

```

> 文件比较

```

1 在文件内使用
    1) 在第一个文件内输入
        :diffthis
    2) 在第二个文件内输入
        :diffthis

2 在命令行使用
    vimdiff [file1] [file2]
```
