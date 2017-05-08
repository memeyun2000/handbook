apt-get update：在修改/etc/apt/sources.list或者/etc/apt/preferences之后运行该命令。此外您需要定期运行这一命令以确保您的软件包列表是最新的。
apt-get install packagename：安装一个新软件包
apt-get remove packagename：卸载一个已安装的软件包（保留配置文件）
apt-get remove package - - purge 删除包，包括删除配置文件等
apt-get clean：这个命令会把安装的软件的备份也删除，不过这样不会影响软件的使用的。
apt-get upgrade：更新所有已安装的软件包
apt-get dist-upgrade：将系统升级到新版本
apt-cache search package 搜索包
apt-cache show package 获取包的相关信息，如说明、大小、版本等
apt-get install package - - reinstall 重新安装包  
apt-get -f install 修复安装"-f = ——fix-missing"
apt-get install build-essential 配置c/c++ compile环境  
apt-cache depends package 了解使用依赖
apt-cache rdepends package 是查看该包被哪些包依赖
apt-get build-dep package 安装相关的编译环境
apt-get source package 下载该包的源代码
apt-get check 检查是否有损坏的依赖
apt-get autoclean：定期运行这个命令来清除那些已经卸载的软件包的.deb文件。
apt-get autoremove :自动清除不需要的程序包。
