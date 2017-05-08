1.环境
OS: Ubuntu13.04 64bits
Git: 1.8.1.2
2.Git安装
执行如下命令安装Git：
```
sudo apt-get install git git-core git-gui git-doc git-svn git-cvs gitweb gitk git-email git-daemon-run git-el git-arch
```
3.Ubuntu下GitHub的使用
1.注册GitHub，创建版本库
注册网站无需多说。创建repository很多地方都有链接。
2.检查SSH
因为GitHub会用到SSH，因此需要在shell里检查是否可以连接到GitHub：
```
ssh -T git@github.com
```
如果看到：
```
Warning: Permanently added ‘github.com,204.232.175.90′ (RSA) to the list of known hosts.
Permission denied (publickey).
```
则说明可以连接。
3.创建本地SSH密钥
检查~/.ssh目录下是否有id_rsa（私钥）和id_rsa.pub（公钥）文件，如果有，则备份出来，删除原文件，再执行如下语句；否则直接执行如下语句：
```
ssh-keygen -t rsa -C "alioth310@gmail.com"
```
4.GitHub中设置公钥
在GitHub中，依次点击Account settings（右上角倒数第二个图标） -> SSH Keys -> Add SSH Key，将id_rsa.pub文件中的字符串复制进去，注意字符串中没有换行和空格。
再次检查SSH连接情况：
```
ssh -T git@github.com
```
如果看到如下所示，则表示添加成功：
```
Hi alioth310! You’ve successfully authenticated, but GitHub does not provide shell access.
```
5.clone来自GitHub的项目
可以用如下方式将GitHub远程版本库中的代码clone到本地：
```
git clone git@github.com:alioth310/test.git
git clone git://github.com/alioth310/test.git
```
6.其他常用的Git命令
```
git init  # 初始化本地Git版本库
git add# 暂存文件，如果使用.表示当前目录及其子目录
git commit -m “first commit”  # 提交，-m选项后跟内容为提交所用的注释
git remote -v  # 查看当前项目远程连接的是哪个版本库地址
git push origin master  # 将本地项目提交到远程版本库
git fetch origin  # 取得远程更新（到origin/master），但还没有合并
git merge origin/master  # 把更新的内容（origin/master）合并到本地分支（master）
git pull origin master  # 相当于fetch和merge的合并，但分步操作更保险
```
