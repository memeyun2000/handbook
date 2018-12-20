> 增加环境变量

```shell
export XIM="ibus"
export XIM_PROGRAM="ibus"
export XMODIFIERS="@im=ibus"
export GTK_IM_MODULE="ibus"
export QT_IM_MODULE="ibus"
```
> 添加两个包的安装

续:经过上述操作后虽然当时能解决输入的问题，但是当机器重启后。wps文档又不能写入文字。

经过查询网上发现都没有写过我这种问题的解决办法后在wiki上看了介绍资料有了些思路：

查看系统有没有安装依赖包：rpm -qa  libpng12

没有就安装：yum install libpng12 -y

因为WPS 默认的 UI 为 IBUS-Qt Centos7默认没有安装，安装这个模块：

yum -y  install  ibus-qt

经过设置环境变量和添加安装模块后，系统重启后，wps也能使用了。
---------------------
作者：longzhizhui926
来源：CSDN
原文：https://blog.csdn.net/longzhizhui926/article/details/81178853
版权声明：本文为博主原创文章，转载请附上博文链接！
