
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.sec</groupId>
    <artifactId>taskserver</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>taskserver</name>
    <url>http://maven.cmbc.com.cn:38080/content/repositories/mirror-central</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <scala.version>2.10.4</scala.version>
        <scala.maven.version>2.10.4</scala.maven.version>
    </properties>

    <profiles>
        <profile>
            <!--开发环境-->
            <id>dev</id>
            <properties>
                <package.target>dev</package.target>
            </properties>
            <!--默认配置-->
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>
        <profile>
            <!--开发环境-->
            <id>prd</id>
            <properties>
                <package.target>prd</package.target>
            </properties>
            <activation>
                <activeByDefault>false</activeByDefault>
            </activation>
        </profile>
    </profiles>

    <repositories>
        <repository>
            <id>central</id>
            <name>Maven Central</name>
            <url>http://maven.cmbc.com.cn:38080/content/repositories/mirror-central</url>
            <layout>default</layout>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
        <repository>
            <id>cmbc2</id>
            <name>cmbc2</name>
            <url>http://maven.cmbc.com.cn:38080/content/repositories/central</url>
        </repository>
    </repositories>

    <build>
        <!--scala 打包 的 配置信息 配置的有问题， 打包的时候外部jar包没有识别-->
        <!--<plugins>-->
            <!--<plugin>-->
                <!--<groupId>net.alchim31.maven</groupId>-->
                <!--<artifactId>scala-maven-plugin</artifactId>-->
                <!--<version>3.1.0</version>-->
                <!--<executions>-->
                    <!--<execution>-->
                        <!--<id>compile-scala</id>-->
                        <!--<phase>compile</phase>-->
                        <!--<goals>-->
                            <!--<goal>add-source</goal>-->
                            <!--<goal>compile</goal>-->
                        <!--</goals>-->
                    <!--</execution>-->
                    <!--<execution>-->
                        <!--<id>test-compile-scala</id>-->
                        <!--<phase>test-compile</phase>-->
                        <!--<goals>-->
                            <!--<goal>add-source</goal>-->
                            <!--<goal>testCompile</goal>-->
                        <!--</goals>-->
                    <!--</execution>-->
                <!--</executions>-->
                <!--<configuration>-->
                    <!--<scalaVersion>${scala.version}</scalaVersion>-->
                <!--</configuration>-->
            <!--</plugin>-->
        <!--</plugins>-->

        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/java/resources</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/java/resources/${package.target}</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>log4j.properties</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/java/resources</directory>
                <includes>
                    <include>**/*.xsd</include>
                </includes>
            </resource>
        </resources>
    </build>

    <dependencies>

        <dependency>
            <groupId>org.scala-lang</groupId>
            <artifactId>scala-library</artifactId>
            <version>${scala.version}</version>
        </dependency>
        <dependency>
            <groupId>org.scala-lang</groupId>
            <artifactId>scala-compiler</artifactId>
            <version>${scala.version}</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>4.1.4.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>4.1.4.RELEASE</version>
        </dependency>

        <!-- Tag libs support for view layer -->

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>taglibs</groupId>
            <artifactId>standard</artifactId>
            <version>1.1.2</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.5</version>
        </dependency>

        <!-- cache -->
        <dependency>
            <groupId>net.sf.ehcache</groupId>
            <artifactId>ehcache-core</artifactId>
            <version>2.6.6</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>4.0.0.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>4.0.0.RELEASE</version>
            <scope>test</scope>
        </dependency>

        <!-- transaction support of spring cache -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>4.1.4.RELEASE</version>
        </dependency>


        <!-- spring for database -->
        <dependency>
            <groupId>commons-dbcp</groupId>
            <artifactId>commons-dbcp</artifactId>
            <version>1.4</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>4.1.4.RELEASE</version>
        </dependency>

        <!-- spring mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.3.1</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.2.2</version>
        </dependency>

        <!-- log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.16</version>
        </dependency>

        <!-- schedule task -->
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
            <version>2.2.1</version>
        </dependency>

        <!-- mybatis need slf4j.jar -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.5</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.5</version>
        </dependency>

        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>

        <dependency>
            <groupId>commons-net</groupId>
            <artifactId>commons-net</artifactId>
            <version>3.1</version>
        </dependency>

        <!--hbase-->
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-client</artifactId>
            <version>2.5.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.hbase</groupId>
            <artifactId>hbase-client</artifactId>
            <version>0.98.6-hadoop2</version>
        </dependency>
        <dependency>
            <groupId>com.jcraft</groupId>
            <artifactId>jsch</artifactId>
            <version>0.1.50</version>
        </dependency>

        <!--解析xml-->
        <dependency>
            <groupId>org.jdom</groupId>
            <artifactId>jdom</artifactId>
            <version>2.0.2</version>
        </dependency>
    </dependencies>
</project>
```
