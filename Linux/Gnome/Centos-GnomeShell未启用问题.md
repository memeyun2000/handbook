> 在gnome3的桌面环境下，要想在网页上通过点击（开/关）按钮就来自动安装组建则需要具备以下几个要素

***

1. 安装好gnome-tweak-tool
2. 安装chrome浏览器，并在chrome的“应用商店”里面安装gnome-shell-extensions插件
3. 在centos7上安装yum源里仅有的一些gnome-shell插件：sudo yum install -y gnome-shell*
4. （最关键的一步）安装“主机与浏览器之间的连接器”是一款名为chrome-gnome-shell的插件，这款插件在yum源里是没有的，但是github上有，从github上clone或者下载这个插件下来，依次执行以下命令：

```
# 进入chrome-gnome-shell目录中
# sudo yum install -y cmake
# sudo yum install -y jq
# mkdir build
# cd build
# cmake -DCMAKE_INSTALL_PREFIX=/usr -DBUILD_EXTENSION=OFF ../
# sudo make install
```


***

> centos 安装 jq

```
wget http://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm
rpm -ivh epel-release-latest-7.noarch.rpm
yum repolist

yum install jq


```
