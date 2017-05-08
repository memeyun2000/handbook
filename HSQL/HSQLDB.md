HSQLDB是一个轻量级的纯Java开发的开放源代码的关系数据库系统，其体积小，占用空间小，使用简单，支持内存运行方式等特点。可以在
http://sourceforge.net/projects/hsqldb/files/
下载最新的HSQLDB版本。我这里下载的是 HSQLDB 2.2.7 版本。下载完之后，把它解压到任一目录下，如E:\hsqldb 下，完成安装工作。
Hsqldb有四种运行模式：
一、内存（Memory-Only）模式：所有的数据都将在内存中完成，如果程序退出，则相应的数据也将同时被销毁。连接JDBC的实例为：jdbc:hsqldb:mem:dbname
二、进行（In-Process）模式：此模式从应用程序启动数据库，由于所有的数据都将写到文件中，所以，即使程序退出，数据也不会被销毁。In-Process 不需要另外启动，可以通过
```
DriverManager.getConnection("jdbcUriName","username","password");
```
方式即可启动数据库。连接 JDBC 的实例为：
```
jdbc:hsqldb:file:/E:/hsqldb/data/dbname
jdbc:hsqldb:file:/opt/db/dbname
jdbc:hsqldb:file:dbname
```
三、服务器模式：此模式下 HSQLDB 跟其它数据库服务器一样，需要通过服务器的形式来进行启动，可以通过
```
java -classpath ../lib/hsqldb.jar org.hsqldb.server.WebServer –database.0 testdb –dbname.0 testdbName
```
的命令启动一个守护进程。连接 JDBC 的实例为：
```
jdbc:hsqldb:hsql://localhost:port/dbname
```
四、Web服务器模式：此模式以WEB服务器的形式启动，并通过HTTP协议接受客户端命令。从1.7.2版本开始，Web服务器模式的 HSQLDB 开始支持事务处理。可以通过
```
java -classpath ../lib/hsqldb.jar org.hsqldb.WebServer –database.0 testdb –dbname.0 testdbname
```
的命令来启动。

重点讨论 hsqldb 服务器模式的启动方法和连接和实际java程序的JDBC连接情况。假设JDK已经安装到机器上，环境变量等都正确设置。
首先在 E:\hsqldbTest 目录下建立两个子目录，data和lib目录，data用来存放数据，lib用来管理jar包。将解压之后的hsqldb中的lib目录下的 hsqldb.jar 文件拷贝到 E:\hsqldbTest\lib 目录下。
1．在hsqldbTest目录下创建 runServer.bat 文件，其内容为：
```
cd ..\data
@java -classpath ../lib/hsqldb.jar org.hsqldb.server.Server
```
 2．保存 runServer.bat 文件，并双击运行该文件，此时回弹出命令行窗口，如下图，表示已启动 HSQLDB 数据库。

此时，在data目录下产生了三个文件，如：
test.lck ——标识数据库锁状态。
test.log ——运行数据库产生的log信息，它将记录每一个运行和用户操作环节。
test.properties——数据库的配置信息，包括分配的内存大小等，可更具需要修改。
注：如果在命令行窗口中按下[Ctrl] + [C]组合键，数据库将退出并关闭。

在做第三步时，千万不能关闭 runServer.bat
3．再创建 runManager.bat 文件，用来启动图形界面管理工具，其内容为：
```
cd ..\data
@java -classpath ..\lib\hsqldb.jar org.hsqldb.util.DatabaseManager
```
4．保存 runManager.bat 文件，并双击文件，此时回弹出图形界面（注意，在进行这一不之前，必须确保第二步已执行），如下图。
```
Recent：将会列出最近的数据库配置
Setting Name: 设置名称，这里不需要填写
Type：选择服务器模式（HSQL Database Engine Server）
Driver：驱动名称，不需要修改
URL：JDBC连接，无需修改
User：用户名,根据需要设置
Password：密码，根据需要设置
```

5．点击【OK】按钮，即可连接到 test 数据库。如下图所示。

此时，我们可以在窗口中创建表等操作，例如，我们创建一个TBL_USERS的表。在菜单栏中选择 COMMAND -> CREATE TABLE 命令，此时在右下文本框中显示创建表的命令，包括各式，支持的类型等。我们创建的 TBL_USERS 表结构如下图所示：
```

CREATE TABLE TBL_USERS(
         ID INTEGER NOT NULL PRIMARY KEY,
         FIRST_NAME VARCHAR(20),
         LAST_NAME VARCHAR (30),
         LOGIN_DATE DATE
 )

```
6．点击【Execute】按钮，如果无语法错误，SQL语句将正常执行，此时，选择菜单栏中的 View -> Refresh Tree 命令，左侧栏中将显示创建的 TBL_USERS 。如下图所示。并且查看 test.log 文件，创建表的过程都将全部记录。

7．另外还可以用 DatabaseManagerSwing 工具启动图形界面。创建 runManagerSwing.bat 文件，内容为：
```
cd ..\data
@java -classpath ..\lib\hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
```
保存文件，双击该文件，将启动 Swing 图形界面，如下图所示，具体的操作过程和 DatabaseManager 工具类似，不再重复。

以上就是启动hsqldb数据库和创建表的整个过程，其它的功能可以通过多次联系即可掌握。按照以上操作方式，hsqldb 将自动产生一个 test 的数据库。但是有时候我们需要自己指定数据库，数据库访问名，数据库访问端口等，该如何实现呢，很简单。只要在相对根目录下创建一个 server.properties 文件即可。
创建自己制定的数据库：
1.首先在 E:\hsqldbTest 目录下创建一个 server.properties 文件，文件内容为（注：在实际文件中，删除后面的注释内容）：
```
server.port = 9001 　　　　　　　　　　#指定端口号为9001
server.database.0 = file:data/mydb  #将在data目录下创建mydb数据库
server.dbname.0 = mydb           　 #指定数据库名，jdbc连接时就是用此名称
server.silent = true
```
2.在 E:\hsqldbTest 目录下创建 runServer2.bat 文件，文件内容为
```
cd ..\data
@java -classpath ../lib/hsqldb.jar org.hsqldb.server.Server -port 9001 -database.0 file:../data/mydb -dbname.0 mydb
```
3.双击运行 runServer2.bat 文件，将会在data目录下创建mydb数据库，并启动数据库。
4.双击运行已创建好的 runManagerSwing.bat 文件，打开图形管理界面，并设置连接到 mydb 数据库中，如下图所示：

5.配置好选项，点击 OK 按钮，即可连接到mydb数据库，在窗口中可以按照以前的步骤创建表，添加记录等操作。

好了，以上就是整个 hsqldb 的安装、启动、创建表等的全过程，在这里特别说明的是，hsqldb.2.2.7  是用 JDK.1.5 以上的版本的编译的，所以，如果你使用的是低于 jdk1.5 的版本时，可能出现异常情况，不能正常使用。另外，即使你安装的jdk版本是1.5以上的，可能还会出现异常，该如何办呢，此时，确保你的hsqldb是在官方网站下载的，需要检查你的java的环境变量，由于你的机器可能安装有oracle，很有可能出现问题，此时只需将%JAVA_HOME%\bin 路径放置在path路径的最开头，这样，就不会出现异常情况了。
 接下来，我们通过一个具体的java程序来连接建立好的mydb数据库，并且在mydb数据库中创建一个TBL_USERS的表。并插入一条记录。
1. 在 E:\hsqldbTest 目录下创建一个 HsqlDemo.java 文件，内容如下：
```
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.hsqldb.jdbcDriver;
public class HsqlDemo {
    public static void main(String[] args) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/mydb", "sa", "");
            if (c != null) {
                System.out.println("Connected db success!");
                String sql = "CREATE TABLE TBL_USERS(ID INTEGER, NAME VARCHAR, BIRTHDAY DATE);";
                Statement st = c.createStatement();
                st.execute(sql);
                sql = "INSERT INTO TBL_USERS(ID, NAME, BIRTHDAY) VALUES ('1', 'ADMIN', SYSDATE);";
                st.executeUpdate(sql);
                if (st != null) {
                    st.close();
                }
                c.close();
            }
        } catch(Exception e) {
            System.out.println("ERROR:failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return;
        }
    }   
}
```

2.在 E:\hsqldbTest 目录下建立一个 runJava.bat 文件，用来运行 HsqlDemo.java  内容如下：
```
@echo offset path = C:\Program Files\Java\jdk1.6.0_05\bin     #jdk的路径
javac -classpath ../lib/hsqldb.jar HsqlDemo.java
java  -classpath ../lib/hsqldb.jar;./ HsqlDemo    # 注意./ 后面的空格，./[空格] HsqlDemo
```
3.确定 mydb 数据库已经启动，如果没有，运行 runServer2.bat ，启动数据库。
4.运行 runJava.bat 文件，运行程序，如果无异常产生，说明已正确执行，此时，可以在 mydb.log 文件中看到创建的 TBL_USERS ，插入的记录，当然，可以用图形工具查看表。

OK，以上就是所有的hsqldb启动过程和连接jdbc的过程。假设我们需要创建/启动一个名为 mydb 的数据库。 为了操作方便等，我们在 c:/hsqldb 目录下创建 mydb 目录。
1，创建 runMydb.bat 文件，文件内容为：
```
java -classpath ../lib/hsqldb.jar org.hsqldb.server.Server -database mydb
或
java -classpath ../lib/hsqldb.jar org.hsqldb.server.Server -database.0 mydb -dbname.0 mydb
```
启动 runMydb.bat 命令即可

2，创建 manageMydb.bat 文件，内容：
```
java -classpath ../lib/hsqldb.jar org.hsqldb.util.DatabaseManager -url jdbc:hsqldb:hsql://localhost/mydb
```
启动 manageMydb.bat ，即可启动HSQLDB的图形界面管理工具

Hsqldb 的主要工具类：
```
org.hsqldb.util.DatabaseManager org.hsqldb.util.DatabaseManagerSwing org.hsqldb.util.Transfer org.hsqldb.util.QueryTool org.hsqldb.util.SqlTool
```
使用 JDBC 连接 HSQLDB 数据库

Java 语言通过 JDBC 使用 HSQLDB 数据库非常简单：
1，将 hsqldb.jar 加入到classpath
2，通过 Class.forName("org.hsqldb.jdbcDriver" ); 初始化 hsqldb 的 jdbc 驱动
3，通过 DriverManager.getConnection(hsqldb-url, user, password); 取得 HSQLDB 数据库的连接

使用例：
```
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (Exception e) {
            System.out.println("ERROR:failed to load jdbc driver");
            e.printStackTrace();
            return;
        }
        Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb");
```
其中，jdbc:hsqldb:hsql://localhost/xdb 需要替换为合适的 jdbc-url 。
有关JDBC-URL的详细描述请参考HSQLDB的四种运行模式

在Hibernate里使用HSQLDB
著名的ORM工具Hibernate也提供了对 HSQLDB 数据库的支持。需要在Hibernage里使用 HSQLDB ，只需在 hibernate.cfg.xml 里加入类似如下设置：
```xml
<hibernate-configuration><session-factory><property name="show_sql">true</property><property name="connection.driver_class">org.hsqldb.jdbcDriver</property><property name="connection.url">jdbc:hsqldb:hsql://localhost/mydb</property><property name="connection.username">SA</property><property name="connection.password"></property><property name="dialect">org.hibernate.dialect.HSQLDialect</property><mapping resource="Customer.hbm.xml" /></session-factory></hibernate-cofiguration>
```
其余就跟其他数据库的用法一样了，这里不再详述。

来源： <http://blog.csdn.net/luxideyao/article/details/19834959>
