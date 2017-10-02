> 查看镜像

```
docker images

sec@sec-ThinkPad-T440s:~/share/dockerProject/images/docker-ubuntu$ docker images
REPOSITORY               TAG                 IMAGE ID            CREATED             SIZE
memeyun2000/ubuntu       latest              52edbb27cbe3        6 months ago        228MB
mysql                    latest              7666f75adb6b        8 months ago        406MB
demo-standalone-hadoop   latest              b4bcec8a1ca9        8 months ago        830MB
redis                    alpine              9947c5a33865        9 months ago        21MB
ubuntu                   latest              4ca3a192ff2a        10 months ago       128MB
centos                   latest              0584b3d2cf6d        11 months ago       197MB
kiwenlau/hadoop          1.0                 a59a34125272        15 months ago       829MB
cloudera/quickstart      latest              4239cd2958c6        18 months ago       6.34GB

```

> docker ps 查看docker运行中容器状态

```

 列出所有运行中容器。

-a 列出所有容器（含沉睡镜像）；

--before="nginx" 列出在某一容器之前创建的容器，接受容器名称和ID作为参数；

--since="nginx" 列出在某一容器之后创建的容器，接受容器名称和ID作为参数；

-f [exited=<int>] 列出满足
exited=<int> 条件的容器；

-l 仅列出最新创建的一个容器；

--no-trunc 显示完整的容器ID；

-n=4 列出最近创建的4个容器；

-q 仅列出容器ID；

-s 显示容器大小。

```

> docker rmi 

```
docker rmi [options "o">] <image>  "o">[image...]
docker rmi nginx:latest postgres:latest python:latest

从本地移除一个或多个指定的镜像。

-f 强行移除该镜像，即使其正被使用；

--no-prune 不移除该镜像的过程镜像，默认移除。 
```

> docker info

```
显示 Docker 系统信息，包括镜像和容器数。
```


> docker search

```
docker search [options "o">] term
docker search -s  django

从 Docker Hub 中搜索符合条件的镜像。

--automated 只列出 automated build
类型的镜像；

--no-trunc 可显示完整的镜像描述；

-s 40 列出收藏数不小于40的镜像。

```

> docker pull

```
docker pull [-a "o">] [user/ "o">]name[:tag "o">]
docker pull laozhu/telescope:latest

从 Docker Hub 中拉取或者更新指定镜像。

-a 拉取所有 tagged 镜像 。 
```


> docker login

```
root@moon:~# docker login
Username: username
Password: ****
Email: user@domain.com
Login Succeeded

按步骤输入在 Docker Hub 注册的用户名、密码和邮箱即可完成登录。 
```

> docker logout
```
运行后从指定服务器登出，默认为官方服务器。
```

> docker history

```
docker history  "o">[options] <image>

查看指定镜像的创建历史。

--no-trunc 显示完整的提交记录；

-q 仅列出提交记录ID。 
```

> docker start|stop|restart

```
docker start|stop "p">|restart [options "o">] <container>  "o">[container...]

启动、停止和重启一个或多个指定容器。

-a 待完成

-i 启动一个容器并进入交互模式；

-t 10 停止或者重启容器的超时时间（秒），超时后系统将杀死进程。 
```

> docker kill 

```
docker kill  "o">[options "o">] <container>  "o">[container...]

杀死一个或多个指定容器进程。

-s "KILL" 自定义发送至容器的信号。
```

> docker save

```

docker save -i "debian.tar"
docker save > "debian.tar"
如上可能是版本问题，本地机器尝试没有成功，-o参数是可以使用的。


将指定镜像保存成 tar 归档文件， docker load 的逆操作。保存后再加载（saved-loaded）的镜像不会丢失提交历史和层，可以回滚。

-o "debian.tar" 指定保存的镜像归档。 


sec@sec-ThinkPad-T440s:~/share/dockerProject/docker-backup$ docker images
REPOSITORY               TAG                 IMAGE ID            CREATED             SIZE
memeyun2000/ubuntu       latest              52edbb27cbe3        6 months ago        228MB
mysql                    latest              7666f75adb6b        8 months ago        406MB
demo-standalone-hadoop   latest              b4bcec8a1ca9        8 months ago        830MB
redis                    alpine              9947c5a33865        9 months ago        21MB
ubuntu                   latest              4ca3a192ff2a        10 months ago       128MB
centos                   latest              0584b3d2cf6d        11 months ago       197MB
kiwenlau/hadoop          1.0                 a59a34125272        15 months ago       829MB
cloudera/quickstart      latest              4239cd2958c6        18 months ago       6.34GB
sec@sec-ThinkPad-T440s:~/share/dockerProject/docker-backup$ 


sec@sec-ThinkPad-T440s:~/share/dockerProject/docker-backup$ docker save -o "ubuntu.tar" ubuntu
sec@sec-ThinkPad-T440s:~/share/dockerProject/docker-backup$ ll
总用量 130792
drwxrwxr-x 2 sec sec      4096 10月  1 23:30 ./
drwxrwxr-x 5 sec sec      4096 10月  1 23:27 ../
-rw------- 1 sec sec 133921792 10月  1 23:30 ubuntu.tar
sec@sec-ThinkPad-T440s:~/share/dockerProject/docker-backup$ 


```


> docker load

```
docker load [options]
docker load < debian.tar
docker load -i "debian.tar"

从 tar 镜像归档中载入镜像， docker save 的逆操作。保存后再加载（saved-loaded）的镜像不会丢失提交历史和层，可以回滚。

-i "debian.tar" 指定载入的镜像归档。 

```

> docker export 
```
docker export <container>
docker export nginx-01 > export.tar

将指定的容器保存成 tar 归档文件， docker import 的逆操作。导出后导入（exported-imported)）的容器会丢失所有的提交历史，无法回滚。 
```

> docker import 
```
docker import url|-  "o">[repository[:tag "o">]]
cat export.tar  "p">| docker import - imported-nginx:latest
docker import http://example.com/export.tar

从归档文件（支持远程文件）创建一个镜像， export 的逆操作，可为导入镜像打上标签。导出后导入（exported-imported)）的容器会丢失所有的提交历史，无法回滚。 
```

> docker top
```
docker top <running_container>  "o">[ps options]

查看一个正在运行容器进程，支持 ps 命令参数。 
```

> docker inspect 
```
docker instpect nginx:latest
docker inspect nginx-container

检查镜像或者容器的参数，默认返回 JSON 格式。

-f 指定返回值的模板文件。 
```

> docker pause
```
暂停某一容器的所有进程。 
```

> docker unpause
```
docker unpause <container>

恢复某一容器的所有进程。 
```

> docker tag

```
docker tag [options "o">] <image>[:tag "o">] [repository/ "o">][username/]name "o">[:tag]

标记本地镜像，将其归入某一仓库。

-f 覆盖已有标记。 
```

> docker push

```
docker push name[:tag "o">]
docker push laozhu/nginx:latest

将镜像推送至远程仓库，默认为 Docker Hub 。 
```

> docker logs
```
docker logs [options "o">] <container>
docker logs -f -t --tail= "s2">"10" insane_babbage

获取容器运行时的输出日志。

-f 跟踪容器日志的最近更新；

-t 显示容器日志的时间戳；

--tail="10" 仅列出最新10条容器日志。 
```

> docker run

```
docker run [options "o">] <image> [ "nb">command]  "o">[arg...]

启动一个容器，在其中运行指定命令。

-a stdin 指定标准输入输出内容类型，可选 STDIN/
STDOUT / STDERR 三项；

-d 后台运行容器，并返回容器ID；

-i 以交互模式运行容器，通常与 -t 同时使用；

-t 为容器重新分配一个伪输入终端，通常与 -i 同时使用；

--name="nginx-lb" 为容器指定一个名称；

--dns 8.8.8.8 指定容器使用的DNS服务器，默认和宿主一致；

--dns-search example.com 指定容器DNS搜索域名，默认和宿主一致；

-h "mars" 指定容器的hostname；

-e username="ritchie" 设置环境变量；

--env-file=[] 从指定文件读入环境变量；

--cpuset="0-2" or --cpuset="0,1,2"
绑定容器到指定CPU运行；

-c 待完成

-m 待完成

--net="bridge" 指定容器的网络连接类型，支持 bridge /
host / none
container:<name|id> 四种类型；

--link=[] 待完成

--expose=[] 待完成 
```

> 问题1. 即使启动参数时 -itd 容器启动后仍然退出
>> 如何启动不会导致容器退出？

```bash
1. 启动脚本时 仍然需要加入参数 -itd
2. 这时候启动如果仍然退出，可能是因为启动时并没有执行/bin/bash,而是使用其他的脚本启动

3. 如果启动时使用了别的脚本则需要在脚本最开始加入/bin/bash 。 如下：(第二个/bin/bash是必须的)

----------------------------------------------

#!/bin/bash

/bin/bash

start-hbase.sh

----------------------------------------------

```