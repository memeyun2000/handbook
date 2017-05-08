1.下载zip版的Tomcat 7，并解压。下载地址
2.在IDEA中配置Tomcat 7
   在idea中的Settings(Ctrl+Alt+s)(或者点击图标)
   弹出窗口左上过滤栏中输入“Application”，选择结果中的 Application Servers。(或在IDE Settings中点击Application Servers)
   显示界面如下：

   点击Add，填入server名字，选择Tomcat 7所在路径，然后点击Apply、OK。
3.部署web application
   按下 Ctrl+Alt+Shift+S，在弹出的 Project structure 中点击 Artifacts。
   在中间列中点击 + ， 新建一个 Web Application Exploded，显示界面如下：

   选中 Show content of elements，点击Apply、OK。
4.发布
   选择菜单中的 Run ，在弹出的菜单中选择 Edit Configurations，如下图所示：
   


   弹出窗口如下：



   点击 + 新建配置，选择 Tomcat Server - Local，显示页面如下：



   输入Name，选择Server，勾选 Build artifact，之后选择 Deployment 标签，如下图所示：



   如上图配置好后，点击Apply、OK。
 5.在IDEA工具栏中点击中的

绿色箭头，如下图所示:



    大功告成~
    如果stop server后，再次start server时，报8080端口冲突问题。可以为IDEA专门配置一个Tomcat使用，并修改端口，删除webapps下的其他应用。端口冲突问题解决。

http://www.cnblogs.com/sweetie/archive/2011/05/17/2049078.html
