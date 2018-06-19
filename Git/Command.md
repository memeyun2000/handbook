> 新建仓库

```
根据Git网站上的方法即可拉取一个版本库
这里不赘述

```

> 其他命令

```
//添加所有文件
git add .

//添加某一个文件
git add {FileName}

//提交
git commit -m '备注'

//查看git的文件状态
git status -s

//推送到版本库
git push origin master

```

> 拷贝版本库到本地

```shell
G:\note\handbook>git clone https://github.com/memeyun2000/handbook.git
```

```
//config global user.email and user.name 
 git config --global user.email "you@example.com"
 git config --global user.name "Your Name"
```

> git 跟踪不区分文件大小写问题 （修改文件的大小写没有响应）

```shell
git mv --force TARGET_FILENAME NEW_FILENAME
```

> git 忽略已跟踪文件的问题
```
只能使用命令针对文件一个一个忽略
git update-index --assume-unchanged FILENAME

恢复忽略 与上面的相反
git update-index --no-assume-unchanged FILENAME

查看忽略(已跟踪)的文件
git ls-files -v
```
