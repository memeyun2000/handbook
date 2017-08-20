
```shell
linux-jwo3:~/soft # ls
CDH-5.10.1-1.cdh5.10.1.p0.10-sles11.parcel  CDH-5.10.1-1.cdh5.10.1.p0.10-sles11.parcel.sha1  manifest.json  mysql57-community-release-sles11-8.noarch.rpm

```

> 从官网上下载 mysql57-community-release-sles11-8.noarch.rpm 安装后会自动添加 zypper 源


```shell

linux-jwo3:~/soft # rpm -ivh mysql57-community-release-sles11-8.noarch.rpm
warning: mysql57-community-release-sles11-8.noarch.rpm: Header V3 DSA signature: NOKEY, key ID 5072e1f5
Preparing...                ########################################### [100%]
   1:mysql57-community-relea########################################### [100%]

```

> Importing MySQL GnuPG Key

Import into the system the GnuPG key for MySQL products, which will be used for checking signatures of the downloaded packages from the MySQL SLES repository, with the following command:

```shell
shell> sudo rpm --import /etc/RPM-GPG-KEY-mysql
```


```
linux-jwo3:~/soft # zypper repos --name
#  | 别名                                             | 名称                                             | 已启用 | 刷新
---+--------------------------------------------------+--------------------------------------------------+--------+-----
 1 | SUSE-Linux-Enterprise-Server-11-SP4 11.4.4-1.109 | SUSE-Linux-Enterprise-Server-11-SP4 11.4.4-1.109 | 是     | 否  
 2 | mysql-connectors-community                       | MySQL Connectors Community                       | 是     | 否  
 3 | mysql-connectors-community-source                | MySQL Connectors Community - Source              | 否     | 否  
 4 | mysql-tools-community                            | MySQL Tools Community                            | 是     | 否  
 5 | mysql-tools-community-source                     | MySQL Tools Community - Source                   | 否     | 否  
 6 | mysql55-community                                | MySQL 5.5 Community Server                       | 否     | 否  
 7 | mysql55-community-source                         | MySQL 5.5 Community Server - Source              | 否     | 否  
 8 | mysql56-community                                | MySQL 5.6 Community Server                       | 否     | 否  
 9 | mysql56-community-source                         | MySQL 5.6 Community Server - Source              | 否     | 否  
10 | mysql57-community                                | MySQL 5.7 Community Server                       | 是     | 否  
11 | mysql57-community-source                         | MySQL 5.7 Community Server - Source              | 否     | 否  

linux-jwo3:~/soft # zypper refresh
软件源“SUSE-Linux-Enterprise-Server-11-SP4 11.4.4-1.109”是最新的。
软件源“MySQL Connectors Community”是最新的。
软件源“MySQL Tools Community”是最新的。
软件源“MySQL 5.7 Community Server”是最新的。
所有安装源均已刷新。
linux-jwo3:~/soft # zypper install mysql-community-server
正在装载安装源数据...
正在读取已安装的包...
正在解析包的依赖性...

将安装以下新包：
  mysql-community-client mysql-community-common mysql-community-libs mysql-community-server

下列包不受各自供应商的支持：
  mysql-community-client mysql-community-common mysql-community-libs mysql-community-server

4 要安装的新包.
总下载大小：192.5 MiB。 操作完成后，将使用额外的 836.3 MiB。
是否继续？ [是/否/? 显示所有选项] (是): 是
正在检索 包 mysql-community-common-5.7.18-1.sles11.x86_64 (1/4), 281.0 KiB （解压后 2.5 MiB）
正在检索： mysql-community-common-5.7.18-1.sles11.x86_64.rpm [已完成 (31.2 KiB/s)]
正在检索 包 mysql-community-libs-5.7.18-1.sles11.x86_64 (2/4), 2.2 MiB （解压后 9.4 MiB）
正在检索： mysql-community-libs-5.7.18-1.sles11.x86_64.rpm [已完成 (737.1 KiB/s)]
正在检索 包 mysql-community-client-5.7.18-1.sles11.x86_64 (3/4), 25.1 MiB （解压后 105.7 MiB）
正在检索： mysql-community-client-5.7.18-1.sles11.x86_64.rpm [已完成 (1.8 MiB/s)]
正在检索 包 mysql-community-server-5.7.18-1.sles11.x86_64 (4/4), 164.9 MiB （解压后 718.7 MiB）
正在检索： mysql-community-server-5.7.18-1.sles11.x86_64.rpm [已完成 (1.4 MiB/s)]
正在安装：mysql-community-common-5.7.18-1.sles11 [已完成]
附加的 rpm 输出:
warning: /var/cache/zypp/packages/mysql57-community/mysql-community-common-5.7.18-1.sles11.x86_64.rpm: Header V3 DSA signature: NOKEY, key ID 5072e1f5


正在安装：mysql-community-libs-5.7.18-1.sles11 [已完成]
附加的 rpm 输出:
warning: /var/cache/zypp/packages/mysql57-community/mysql-community-libs-5.7.18-1.sles11.x86_64.rpm: Header V3 DSA signature: NOKEY, key ID 5072e1f5


正在安装：mysql-community-client-5.7.18-1.sles11 [已完成]
附加的 rpm 输出:
warning: /var/cache/zypp/packages/mysql57-community/mysql-community-client-5.7.18-1.sles11.x86_64.rpm: Header V3 DSA signature: NOKEY, key ID 5072e1f5


正在安装：mysql-community-server-5.7.18-1.sles11 [已完成]
附加的 rpm 输出:
warning: /var/cache/zypp/packages/mysql57-community/mysql-community-server-5.7.18-1.sles11.x86_64.rpm: Header V3 DSA signature: NOKEY, key ID 5072e1f5

```

> 查看mysql root 的临时密码

```shell

linux-jwo3:/var/log/mysql # service mysql start

linux-jwo3:~/soft # grep 'temporary password' /var/log/mysql/mysqld.log
2017-06-11T16:34:42.146631Z 1 [Note] A temporary password is generated for root@localhost: Dieos7uL>Tu=
linux-jwo3:~/soft # mysql -uroot -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 6
Server version: 5.7.18

Copyright (c) 2000, 2017, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

```

> 更换root用户密码

```shell

mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass4!';
Query OK, 0 rows affected (0.00 sec)

mysql>

```

> 更新mysql  使其他机器可以访问mysql服务

```

mysql> GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'MyNewPass4!' WITH GRANT OPTION;
Query OK, 0 rows affected, 1 warning (0.00 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.01 sec)

mysql>


```
