> Spring MVC 基于Method的映射规则（注解版）

```

在Restful风格的web开发中，根据不同的请求方法使用相应的控制器处理逻辑成为核心需求，下面就看看如何在Spring MVC中识别不同的请求方法。

```

> 请求方法


在Http中，请求的方法有很多种，最常见的就是GET、POST，他们的差异就不过多赘述了。由于Restful概念的兴起，即使用Url的不同请求方法来控制业务方法，很多请求方法都开始流行起来，比如PUT、DELETE等等。

那么就先介绍下各个请求方法的使用场景吧!

> GET

平时网页的一些基本的URL都是GET请求的，用于执行查询操作。
但是由于GET中URL是有长度的限制的，而GET会把所有的参数都放在URL中，比如


```

xxx?name=xingoo
```

因此就会有下面的问题：

1. 数据都明文暴露，用户可以直接看到
2. 数据长度有限制


>POST

由于上面GET的缺点，POST正好弥补了这些问题。POST方法把数据都存放在body里面，这样即突破了长度的限制；又保证用户无法直接看到。在使用表单时，比较常用

> HEAD

HEAD请求只会返回首部的信息，不会返回相应体。通常用于测试数据是否存在、当做心跳检测等等。

> PUT

与GET相反，用于改变某些内容。

> DELETE

删除某些资源

> TRACE

可以理解成，我们为了看看一条请求在到达服务前数据发生了什么变化。可以使用这个命令，它会在最后一站返回原始信息，这样就可以观察到中间是否修改过请求。(经常会用于跨站攻击，所以有一定的安全隐患)

> OPTIONS

询问服务器支持的方法。

> PATCH

这个方法不太常见，是servlet 3.0提供的方法，主要用于更新部分字段。与PUT方法相比，PUT提交的相当于全部数据的更新，类似于update；而PATCH则相当于更新部分字段，如果数据不存在则新建，有点类似于neworupdate。

> Spring中的使用方法

在Spring MVC中，RequestMethod提供了方法的集合：

```java

public enum RequestMethod {
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
}

```
在控制器中，我们可以通过设置RequestMapping的method方法，改变接收数据controller：

```java

@Controller
public class HelloController {
    @RequestMapping(value="/test",method=RequestMethod.GET)
    public @ResponseBody String get(){
        return "from get";
    }
    @RequestMapping(value="/test",method=RequestMethod.POST)
    public @ResponseBody String post(){
        return "from post";
    }
}

```
