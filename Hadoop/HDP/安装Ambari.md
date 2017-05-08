1. 配置Ambari server 至 clusters的免密码登录
    ssh-keygen -t rsa
    scp ~/.ssh/authorized_keys hdp3:/root/.ssh/


--------------------------------------------------------------------------------
2. 设置hostname

--------------------------------------------------------------------------------
3. 关闭防火墙
   systemctl disable firewalld
   service firewalld stop


--------------------------------------------------------------------------------
4. 关闭SELinux 和 packagekit 服务
  1. You must disable SELinux for the Ambari setup to function. On each host in your cluster,
暂时关闭： setenforce 0
永久关闭：
NoteTo permanently disable SELinux set SELINUX=disabled in /etc/selinux/config This ensures that SELinux does not turn itself on after you reboot the machine .

  2. On an installation host running RHEL/CentOS with PackageKit installed, open /etc/yum/pluginconf.d/refresh-packagekit.conf using a text editor. Make the following change:
enabled=0
NotePackageKit is not enabled by default on Debian, SLES, or Ubuntu systems. Unless you have specifically enabled PackageKit, you may skip this step for a Debian, SLES, or Ubuntu installation host.

  3. umask 0022
   umask检查是否为 0022
   不是则设置当前 umask 0022
   并且echo umask 0022 >> /etc/profile
--------------------------------------------------------------------------------
5. 安装apache httpd
        sudo yum install httpd
         查看80端口是否被占用 netstat -nltp | grep 80

    创建web服务  目录
        On your mirror server, create a directory for your web server.
  ● For example, from a shell window, type:
      ○ For RHEL/CentOS/Oracle Linux: 
mkdir -p /var/www/html/
      ○ For SLES:
mkdir -p /srv/www/htdocs/rpms
      ○ For Debian/Ubuntu:
mkdir -p /var/www/html/

来源： http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_getting_started_setting_up_a_local_repository.html


6. 复制三个tarball到 /var/www/html

--------------------------------------------------------------------------------
7. 创建三个repo文件
如下是网上帖子上的参考文件
hdp.repo
[HDP-2.3.0.0] name=HDP Version - HDP-2.3.0.0 baseurl=http://10.0.71.15/hdp/HDP/centos6/2.x/updates/2.3.0.0 gpgcheck=1 gpgkey=http://public-repo-1.hortonworks.com/HDP/centos6/2.x/updates/2.3.0.0/RPM-GPG-KEY/RPM-GPG-KEY-Jenkins enabled=1 priority=1
ambari.repo
[Updates-ambari-2.2.1.0] name=ambari-2.2.1.0 - Updates baseurl=http://10.0.71.15/hdp/ambari/centos6/2.2.1.0-161 gpgcheck=1 gpgkey=http://public-repo-1.hortonworks.com/ambari/centos6/RPM-GPG-KEY/RPM-GPG-KEY-Jenkins enabled=1 priority=1
hdp-util.repo
[HDP-UTILS-1.1.0.20] name=HDP Utils Version - HDP-UTILS-1.1.0.20 baseurl=http://10.0.71.15/hdp/HDP-UTILS-1.1.0.20/repos/centos6 gpgcheck=1 gpgkey=http://public-repo-1.hortonworks.com/HDP/centos6/2.x/updates/2.3.0.0/RPM-GPG-KEY/RPM-GPG-KEY-Jenkins enabled=1 priority=1
注意： baseurl 为 安装在apache httpd 中的web服务目录
修改后HDP2.4的库文件
[HDP-2.4.2.0]
name=HDP Version - HDP-2.4.2.0
baseurl=http://hdp1/hdp/HDP/centos7/2.x/updates/2.4.2.0
gpgcheck=1
gpgkey=http://public-repo-1.hortonworks.com/HDP/centos7/2.x/updates/2.4.2.0/RPM-GPG-KEY/RPM-GPG-KEY-Jenkins
enabled=1
priority=1


[Updates-ambari-2.2.2.0]
name=ambari-2.2.2.0 - Updates
baseurl=http://hdp1/hdp/AMBARI-2.2.2.0/centos7/2.2.2.0-460
gpgcheck=1
gpgkey=http://public-repo-1.hortonworks.com/ambari/centos7/RPM-GPG-KEY/RPM-GPG-KEY-Jenkins
enabled=1
priority=1


[HDP-UTILS-1.1.0.20]
name=HDP Utils Version - HDP-UTILS-1.1.0.20
baseurl=http://hdp1/hdp/HDP-UTILS-1.1.0.20/repos/centos7
gpgcheck=1
gpgkey=http://public-repo-1.hortonworks.com/HDP/centos7/2.x/updates/2.4.2.0/RPM-GPG-KEY/RPM-GPG-KEY-Jenkins
enabled=1
priority=1


将这三个文件复制到/etc/yum.repos.d
--------------------------------------------------------------------------------

8. 安装yum插件
yum install yum-plugin-priorities
vi /etc/yum/pluginconf.d/priorities.conf
#设置为以下内容
[main]
enabled=1 gpgcheck=0
--------------------------------------------------------------------------------

9. 查看yum的资源库清单
    yum repolist

以下是本地看到yum清单信息：
[root@hdp1 yum.repos.d]# yum repolist
Loaded plugins: fastestmirror, langpacks, priorities
HDP-2.4.2.0                                                                                | 2.9 kB  00:00:00     
HDP-UTILS-1.1.0.20                                                                         | 2.9 kB  00:00:00     
Updates-ambari-2.2.2.0                                                                     | 2.9 kB  00:00:00     
extras                                                                                     | 3.4 kB  00:00:00     
updates                                                                                    | 3.4 kB  00:00:00     
(1/3): HDP-UTILS-1.1.0.20/primary_db                                                       |  28 kB  00:00:00     
(2/3): HDP-2.4.2.0/primary_db                                                              |  62 kB  00:00:00     
(3/3): Updates-ambari-2.2.2.0/primary_db                                                   | 6.3 kB  00:00:00     
Loading mirror speeds from cached hostfile
 * base: mirrors.cug.edu.cn
 * extras: mirrors.nwsuaf.edu.cn
 * updates: mirrors.nwsuaf.edu.cn
18 packages excluded due to repository priority protections
repo id                                      repo name                                                    status
HDP-2.4.2.0                                  HDP Version - HDP-2.4.2.0                                         181
HDP-UTILS-1.1.0.20                           HDP Utils Version - HDP-UTILS-1.1.0.20                             44
Updates-ambari-2.2.2.0                       ambari-2.2.2.0 - Updates                                            8
base/7/x86_64                                CentOS-7 - Base                                              8,991+16
extras/7/x86_64                              CentOS-7 - Extras                                               354+2
updates/7/x86_64                             CentOS-7 - Updates                                              2,026
repolist: 11,604


这个是官网上给的信息，不太一样
yum repolist
You should see values similar to the following for Ambari repositories in the list.
Version values vary, depending on the installation.
repo idrepo namestatusAMBARI.2.2.2.0-2.xAmbari 2.x5baseCentOS-7 - Base6,518extrasCentOS-7 - Extras15updatesCentOS-7 - Updates209
来源： http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_download_the_ambari_repo_lnx7.html
--------------------------------------------------------------------------------

10. 安装ambrai-server

日志信息如下:
[root@hdp1 yum.repos.d]# yum install ambari-server
Loaded plugins: fastestmirror, langpacks, priorities
Loading mirror speeds from cached hostfile
 * base: mirrors.cug.edu.cn
 * extras: mirrors.nwsuaf.edu.cn
 * updates: mirrors.nwsuaf.edu.cn
18 packages excluded due to repository priority protections
Resolving Dependencies
--> Running transaction check
---> Package ambari-server.x86_64 0:2.2.2.0-460 will be installed
--> Processing Dependency: postgresql-server >= 8.1 for package: ambari-server-2.2.2.0-460.x86_64
--> Running transaction check
---> Package postgresql-server.x86_64 0:9.2.15-1.el7_2 will be installed
--> Processing Dependency: postgresql-libs(x86-64) = 9.2.15-1.el7_2 for package: postgresql-server-9.2.15-1.el7_2.x86_64
--> Processing Dependency: postgresql(x86-64) = 9.2.15-1.el7_2 for package: postgresql-server-9.2.15-1.el7_2.x86_64
--> Processing Dependency: libpq.so.5()(64bit) for package: postgresql-server-9.2.15-1.el7_2.x86_64
--> Running transaction check
---> Package postgresql.x86_64 0:9.2.15-1.el7_2 will be installed
---> Package postgresql-libs.x86_64 0:9.2.15-1.el7_2 will be installed
--> Finished Dependency Resolution

Dependencies Resolved

==================================================================================================================
 Package                      Arch              Version                   Repository                         Size
==================================================================================================================
Installing:
 ambari-server                x86_64            2.2.2.0-460               Updates-ambari-2.2.2.0            409 M
Installing for dependencies:
 postgresql                   x86_64            9.2.15-1.el7_2            updates                           3.0 M
 postgresql-libs              x86_64            9.2.15-1.el7_2            updates                           231 k
 postgresql-server            x86_64            9.2.15-1.el7_2            updates                           3.8 M

Transaction Summary
==================================================================================================================
Install  1 Package (+3 Dependent packages)

Total download size: 416 M
Installed size: 477 M
Is this ok [y/d/N]:



注意： 安装ambari的同时它会安装postgressql

--------------------------------------------------------------------------------
11. ambari-server setup
1. 询问是否暂时关闭了SElinux 选择 y
2. 是否设置其他的账户管理ambari 选择n 默认使用root用户
3. 是否关闭了防火墙
4. 配置JAVA_HOME 虚拟机上yum自动下载到了/var/lib/ambari-server/resources/jdk-8u60-linux-x64.tar.gz 安装到了如下目录：/usr/jdk64/
5. 配置数据库，这里先选择n 使用默认的posgressql数据库，以后再尝试配置mysql数据库

logs：
[root@hdp1 packages]# ambari-server setup
Using python  /usr/bin/python
Setup ambari-server
Checking SELinux...
SELinux status is 'enabled'
SELinux mode is 'permissive'
WARNING: SELinux is set to 'permissive' mode and temporarily disabled.
OK to continue [y/n] (y)? y
Customize user account for ambari-server daemon [y/n] (n)? n
Adjusting ambari-server permissions and ownership...
Checking firewall status...
Redirecting to /bin/systemctl status  iptables.service

Checking JDK...
[1] Oracle JDK 1.8 + Java Cryptography Extension (JCE) Policy Files 8
[2] Oracle JDK 1.7 + Java Cryptography Extension (JCE) Policy Files 7
[3] Custom JDK
==============================================================================
Enter choice (1): 3
WARNING: JDK must be installed on all hosts and JAVA_HOME must be valid on all hosts.
WARNING: JCE Policy files are required for configuring Kerberos security. If you plan to use Kerberos,please make sure JCE Unlimited Strength Jurisdiction Policy Files are valid on all hosts.
Path to JAVA_HOME: ^C
Aborting ... Keyboard Interrupt.
[root@hdp1 packages]# ambari-server setup
Using python  /usr/bin/python
Setup ambari-server
Checking SELinux...
SELinux status is 'enabled'
SELinux mode is 'permissive'
WARNING: SELinux is set to 'permissive' mode and temporarily disabled.
OK to continue [y/n] (y)? y
Customize user account for ambari-server daemon [y/n] (n)? n
Adjusting ambari-server permissions and ownership...
Checking firewall status...
Redirecting to /bin/systemctl status  iptables.service

Checking JDK...
[1] Oracle JDK 1.8 + Java Cryptography Extension (JCE) Policy Files 8
[2] Oracle JDK 1.7 + Java Cryptography Extension (JCE) Policy Files 7
[3] Custom JDK
==============================================================================
Enter choice (1): 1
To download the Oracle JDK and the Java Cryptography Extension (JCE) Policy Files you must accept the license terms found at http://www.oracle.com/technetwork/java/javase/terms/license/index.html and not accepting will cancel the Ambari Server setup and you must install the JDK and JCE files manually.
Do you accept the Oracle Binary Code License Agreement [y/n] (y)? y
Downloading JDK from http://public-repo-1.hortonworks.com/ARTIFACTS/jdk-8u60-linux-x64.tar.gz to /var/lib/ambari-server/resources/jdk-8u60-linux-x64.tar.gz
jdk-8u60-linux-x64.tar.gz... 100% (172.8 MB of 172.8 MB)
Successfully downloaded JDK distribution to /var/lib/ambari-server/resources/jdk-8u60-linux-x64.tar.gz
Installing JDK to /usr/jdk64/
Successfully installed JDK to /usr/jdk64/
Downloading JCE Policy archive from http://public-repo-1.hortonworks.com/ARTIFACTS/jce_policy-8.zip to /var/lib/ambari-server/resources/jce_policy-8.zip

Successfully downloaded JCE Policy archive to /var/lib/ambari-server/resources/jce_policy-8.zip
Installing JCE policy...
Completing setup...
Configuring database...
Enter advanced database configuration [y/n] (n)? n
Configuring database...
Default properties detected. Using built-in database.
Configuring ambari database...
Checking PostgreSQL...
Running initdb: This may take upto a minute.
Initializing database ... OK


About to start PostgreSQL
Configuring local database...
Connecting to local database...done.
Configuring PostgreSQL...
Restarting PostgreSQL
Extracting system views...
ambari-admin-2.2.2.0.460.jar
......
Adjusting ambari-server permissions and ownership...
Ambari Server 'setup' completed successfully.
[root@hdp1 packages]#
