找到文件：/usr/lib/hue/tools/start_scripts/start_deps.mf，Hortonworks HDP启动所有服务和组件的命令都在这个文件中，之所以把这些服务的启动命令写在了一个makefile中而不是一个shell文件，其实就是想利用make的依赖管理来轻松解决各组件、服务之间的依赖问题，这一点倒是很值得我们学习和借鉴，特别是在需要提供一个启动系统的脚本，而系统各组件/服务又有依赖关系时。
回到正题，首先我们要做一个备份：
cp /usr/lib/hue/tools/start_scripts/start_deps.mf /usr/lib/hue/tools/start_scripts/start_deps.mf.bak
然后：
vim /usr/lib/hue/tools/start_scripts/start_deps.mf
找到“all: Startup Ambari Others”这一行，在前面追加自定义的启动项，假如我们只需要Hive，则可以这样写：

#Added By Laurence: Customized Startup
Customized: HDFS YARN Zookeeper Hive_Metastore WebHCat

保存并退出，然后打开这个文件：/usr/lib/hue/tools/start_scripts/startup_script，同样先做一个备份：
cp /usr/lib/hue/tools/start_scripts/startup_script /usr/lib/hue/tools/start_scripts/startup_script.bak
然后：
vim /usr/lib/hue/tools/start_scripts/startup_script
查找关键字"Startup" 所在的行，找到后注释并复制一行，在新行上把Startup改为Customized
#make --makefile $SCRIPTS_PATH/start_deps.mf -B Startup -j -i
make --makefile $SCRIPTS_PATH/start_deps.mf -B Customized -j -i
这样，开机启动时就是完全按照我们在Customized 中列出的服务进行启动了。
来源： http://blog.csdn.net/bluishglc/article/details/42109253
