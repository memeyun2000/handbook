FreeMarker概述
```
         FreeMarker是一个模板引擎，一个基于模板生成文本输出的通用工具，使用纯Java编写
         FreeMarker被设计用来生成HTML Web页面，特别是基于MVC模式的应用程序
         虽然FreeMarker具有一些编程的能力，但通常由Java程序准备要显示的数据，由FreeMarker生成页面，通过模板显示准备的数据（如下图）
         FreeMarker不是一个Web应用框架，而适合作为Web应用框架一个组件
         FreeMarker与容器无关，因为它并不知道HTTP或Servlet；FreeMarker同样可以应用于非Web应用程序环境
         FreeMarker更适合作为Model2框架（如Struts）的视图组件，你也可以在模板中使用JSP标记库
         FreeMarker是免费的
```

1、通用目标

         能够生成各种文本：HTML、XML、RTF、Java源代码等等
         易于嵌入到你的产品中：轻量级；不需要Servlet环境
         插件式模板载入器：可以从任何源载入模板，如本地文件、数据库等等
         你可以按你所需生成文本：保存到本地文件；作为Email发送；从Web应用程序发送它返回给Web浏览器

2、强大的模板语言
         所有常用的指令：include、if/elseif/else、循环结构
         在模板中创建和改变变量
         几乎在任何地方都可以使用复杂表达式来指定值
         命名的宏，可以具有位置参数和嵌套内容
         名字空间有助于建立和维护可重用的宏库，或者将一个大工程分成模块，而不必担心名字冲突
         输出转换块：在嵌套模板片段生成输出时，转换HTML转义、压缩、语法高亮等等；你可以定义自己的转换

3、通用数据模型
         FreeMarker不是直接反射到Java对象，Java对象通过插件式对象封装，以变量方式在模板中显示
         你可以使用抽象（接口）方式表示对象（JavaBean、XML文档、SQL查询结果集等等），告诉模板开发者使用方法，使其不受技术细节的打扰

4、为Web准备
         在模板语言中内建处理典型Web相关任务（如HTML转义）的结构
         能够集成到Model2 Web应用框架中作为JSP的替代
         支持JSP标记库
         为MVC模式设计：分离可视化设计和应用程序逻辑；分离页面设计员和程序员

5、智能的国际化和本地化
         字符集智能化（内部使用UNICODE）
         数字格式本地化敏感
         日期和时间格式本地化敏感
         非US字符集可以用作标识（如变量名）
         多种不同语言的相同模板

6、强大的XML处理能力
         <#recurse> 和<#visit>指令（2.3版本）用于递归遍历XML树
         在模板中清楚和直觉的访问XML对象模型




FreeMarker设计指南(1)

1、快速入门
（1）模板 + 数据模型 = 输出
         FreeMarker基于设计者和程序员是具有不同专业技能的不同个体的观念
         他们是分工劳动的：设计者专注于表示——创建HTML文件、图片、Web页面的其它可视化方面；程序员创建系统，生成设计页面要显示的数据
         经常会遇到的问题是：在Web页面（或其它类型的文档）中显示的信息在设计页面时是无效的，是基于动态数据的
         在这里，你可以在HTML（或其它要输出的文本）中加入一些特定指令，FreeMarker会在输出页面给最终用户时，用适当的数据替代这些代码
         下面是一个例子：
<html>
<head>
  <title>Welcome!</title>
</head>
<body>
  <h1>Welcome ${user}!</h1>
  <p>Our latest product:
  <a href="${latestProduct.url}">${latestProduct.name}</a>!
</body>
</html>
         这个例子是在简单的HTML中加入了一些由${…}包围的特定代码，这些特定代码是FreeMarker的指令，而包含FreeMarker的指令的文件就称为模板（Template）
         至于user、latestProduct.url和latestProduct.name来自于数据模型（data model）
         数据模型由程序员编程来创建，向模板提供变化的信息，这些信息来自于数据库、文件，甚至于在程序中直接生成
         模板设计者不关心数据从那儿来，只知道使用已经建立的数据模型
         下面是一个可能的数据模型：
(root)
  |
  +- user = "Big Joe"
  |
  +- latestProduct
      |
      +- url = "products/greenmouse.html"
      |
      +- name = "green mouse"
         数据模型类似于计算机的文件系统，latestProduct可以看作是目录，而user、url和name看作是文件，url和name文件位于latestProduct目录中（这只是一个比喻，实际并不存在）
         当FreeMarker将上面的数据模型合并到模板中，就创建了下面的输出：
<html>
<head>
  <title>Welcome!</title>
</head>
<body>
  <h1>Welcome Big Joe!</h1>
  <p>Our latest product:
 <a href="products/greenmouse.html">green mouse</a>!
</body>
</html>
（2）数据模型
         典型的数据模型是树型结构，可以任意复杂和深层次，如下面的例子：
(root)
  |
  +- animals
  |   |
  |   +- mouse
  |   |   |  
  |   |   +- size = "small"
  |   |   |  
  |   |   +- price = 50
  |   |
  |   +- elephant
  |   |   |  
  |   |   +- size = "large"
  |   |   |  
  |   |   +- price = 5000
  |   |
  |   +- python
  |       |  
  |       +- size = "medium"
  |       |  
  |       +- price = 4999
  |
  +- test = "It is a test"
  |
  +- whatnot
      |
      +- because = "don't know"
         类似于目录的变量称为hashes，包含保存下级变量的唯一的查询名字
         类似于文件的变量称为scalars，保存单值
         scalars保存的值有两种类型：字符串（用引号括起，可以是单引号或双引号）和数字（不要用引号将数字括起，这会作为字符串处理）
         对scalars的访问从root开始，各部分用“.”分隔，如animals.mouse.price
         另外一种变量是sequences，和hashes类似，只是不使用变量名字，而使用数字索引，如下面的例子：
(root)
  |
  +- animals
  |   |
  |   +- (1st)
  |   |   |
  |   |   +- name = "mouse"
  |   |   |
  |   |   +- size = "small"
  |   |   |
  |   |   +- price = 50
  |   |
  |   +- (2nd)
  |   |   |
  |   |   +- name = "elephant"
  |   |   |
  |   |   +- size = "large"
  |   |   |
  |   |   +- price = 5000
  |   |
  |   +- (3rd)
  |       |
  |       +- name = "python"
  |       |
  |       +- size = "medium"
  |       |
  |       +- price = 4999
  |
  +- whatnot
      |
      +- fruits
          |
          +- (1st) = "orange"
          |
          +- (2nd) = "banana"
         这种对scalars的访问使用索引，如animals[0].name
（3）模板
         在FreeMarker模板中可以包括下面三种特定部分：
?         ${…}：称为interpolations，FreeMarker会在输出时用实际值进行替代
?         FTL标记（FreeMarker模板语言标记）：类似于HTML标记，为了与HTML标记区分，用#开始（有些以@开始，在后面叙述）
?         注释：包含在<#--和-->（而不是<!--和-->）之间
         下面是一些使用指令的例子：
?         if指令
<#if animals.python.price < animals.elephant.price>
  Pythons are cheaper than elephants today.
<#else>
  Pythons are not cheaper than elephants today.
</#if>
?         list指令
<p>We have these animals:
<table border=1>
  <tr><th>Name<th>Price
  <#list animals as being>
  <tr><td>${being.name}<td>${being.price} Euros
  </#list>
</table>
输出为：
<p>We have these animals:
<table border=1>
  <tr><th>Name<th>Price
  <tr><td>mouse<td>50 Euros
  <tr><td>elephant<td>5000 Euros
  <tr><td>python<td>4999 Euros
</table>
?         include指令
<html>
<head>
  <title>Test page</title>
</head>
<body>
  <h1>Test page</h1>
  <p>Blah blah...
<#include "/copyright_footer.html">
</body>
</html>
?         一起使用指令
<p>We have these animals:
<table border=1>
  <tr><th>Name<th>Price
  <#list animals as being>
  <tr>
    <td>
      <#if being.size = "large"><b></#if>
      ${being.name}
      <#if being.size = "large"></b></#if>
    <td>${being.price} Euros
  </#list>
</table>

-------------------------------------------------------------

FreeMarker设计指南(3)

3、模板
（1）整体结构
         模板使用FTL（FreeMarker模板语言）编写，是下面各部分的一个组合：
?         文本：直接输出
?         Interpolation：由${和}，或#{和}来限定，计算值替代输出
?         FTL标记：FreeMarker指令，和HTML标记类似，名字前加#予以区分，不会输出
?         注释：由<#--和-->限定，不会输出
         下面是以一个具体模板例子：
<html>[BR]
<head>[BR]
  <title>Welcome!</title>[BR]
</head>[BR]
<body>[BR]
  <#-- Greet the user with his/her name -->[BR]
  <h1>Welcome ${user}!</h1>[BR]
  <p>We have these animals:[BR]
  <ul>[BR]
  <#list animals as being>[BR]
    <li>${being.name} for ${being.price} Euros[BR]
  </#list>[BR]
  </ul>[BR]
</body>[BR]
</html>
         [BR]是用于换行的特殊字符序列
         注意事项：
?         FTL区分大小写，所以list是正确的FTL指令，而List不是；${name}和${NAME}是不同的
?         Interpolation只能在文本中使用
?         FTL标记不能位于另一个FTL标记内部，例如：
<#if <#include 'foo'>='bar'>...</if>
?         注释可以位于FTL标记和Interpolation内部，如下面的例子：
<h1>Welcome ${user <#-- The name of user -->}!</h1>[BR]
<p>We have these animals:[BR]
<ul>[BR]
<#list <#-- some comment... --> animals as <#-- again... --> being>[BR]
...
?         多余的空白字符会在模板输出时移除
（2）指令
         在FreeMarker中，使用FTL标记引用指令
         有三种FTL标记，这和HTML标记是类似的：
?         开始标记：<#directivename parameters>
?         结束标记：</#directivename>
?         空内容指令标记：<#directivename parameters/>
         有两种类型的指令：预定义指令和用户定义指令
         用户定义指令要使用@替换#，如<@mydirective>...</@mydirective>（会在后面讲述）
         FTL标记不能够交叉，而应该正确的嵌套，如下面的代码是错误的：
<ul>
<#list animals as being>
  <li>${being.name} for ${being.price} Euros
  <#if use = "Big Joe">
     (except for you)
</#list>
</#if> <#-- WRONG! -->
</ul>
         如果使用不存在的指令，FreeMarker不会使用模板输出，而是产生一个错误消息
         FreeMarker会忽略FTL标记中的空白字符，如下面的例子：
<#list[BR]
  animals       as[BR]
     being[BR]
>[BR]
${being.name} for ${being.price} Euros[BR]
</#list    >
         但是，<、</和指令之间不允许有空白字符
（3）表达式
         直接指定值
?         字符串
n         使用单引号或双引号限定
n         如果包含特殊字符需要转义，如下面的例子：
${"It's \"quoted\" and
this is a backslash: \\"}

${'It\'s "quoted" and
this is a backslash: \\'}
输出结果是：
It's "quoted" and
this is a backslash: \

It's "quoted" and
this is a backslash: \
n         下面是支持的转义序列：
转义序列

含义

\"

双引号(u0022)

\'

单引号(u0027)

\\

反斜杠(u005C)

\n

换行(u000A)

\r

Return (u000D)

\t

Tab (u0009)

\b

Backspace (u0008)

\f

Form feed (u000C)

\l

<

\g

>

\a

&

\{

{

\xCode

4位16进制Unicode代码


n         有一类特殊的字符串称为raw字符串，被认为是纯文本，其中的\和{等不具有特殊含义，该类字符串在引号前面加r，下面是一个例子：
${r"${foo}"}
${r"C:\foo\bar"}
输出的结果是：
${foo}
C:\foo\bar
?         数字
n         直接输入，不需要引号
n         精度数字使用“.”分隔，不能使用分组符号
n         目前版本不支持科学计数法，所以“1E3”是错误的
n         不能省略小数点前面的0，所以“.5”是错误的
n         数字8、+8、08和8.00都是相同的
?         布尔值
n         true和false，不使用引号
?         序列
n         由逗号分隔的子变量列表，由方括号限定，下面是一个例子：
<#list ["winter", "spring", "summer", "autumn"] as x>
${x}
</#list>
输出的结果是：
winter
spring
summer
autumn
n         列表的项目是表达式，所以可以有下面的例子：
[2 + 2, [1, 2, 3, 4], "whatnot"]
n         可以使用数字范围定义数字序列，例如2..5等同于[2, 3, 4, 5]，但是更有效率，注意数字范围没有方括号
n         可以定义反递增的数字范围，如5..2
?         散列（hash）
n         由逗号分隔的键/值列表，由大括号限定，键和值之间用冒号分隔，下面是一个例子：
{"name":"green mouse", "price":150}
n         键和值都是表达式，但是键必须是字符串
         获取变量
?         顶层变量： ${variable}，变量名只能是字母、数字、下划线、$、@和#的组合，且不能以数字开头
?         从散列中获取数据
n         可以使用点语法或方括号语法，假设有下面的数据模型：
(root)
|
+- book
|   |
|   +- title = "Breeding green mouses"
|   |
|   +- author
|       |
|       +- name = "Julia Smith"
|       |
|       +- info = "Biologist, 1923-1985, Canada"
|
+- test = "title"
下面都是等价的：
book.author.name
book["author"].name
book.author.["name"]
book["author"]["name"]
n         使用点语法，变量名字有顶层变量一样的限制，但方括号语法没有该限制，因为名字是任意表达式的结果
?         从序列获得数据：和散列的方括号语法语法一样，只是方括号中的表达式值必须是数字；注意：第一个项目的索引是0
?         序列片断：使用[startIndex..endIndex]语法，从序列中获得序列片断（也是序列）；startIndex和endIndex是结果为数字的表达式
?         特殊变量：FreeMarker内定义变量，使用.variablename语法访问
         字符串操作
?         Interpolation（或连接操作）
n         可以使用${..}（或#{..}）在文本部分插入表达式的值，例如：
${"Hello ${user}!"}
${"${user}${user}${user}${user}"}
n         可以使用+操作符获得同样的结果
${"Hello " + user + "!"}
${user + user + user + user}
n         ${..}只能用于文本部分，下面的代码是错误的：
<#if ${isBig}>Wow!</#if>
<#if "${isBig}">Wow!</#if>
应该写成：
<#if isBig>Wow!</#if>
?         子串
n         例子（假设user的值为“Big Joe”）：
${user[0]}${user[4]}
${user[1..4]}
结果是（注意第一个字符的索引是0）：
BJ
ig J
         序列操作
?         连接操作：和字符串一样，使用+，下面是一个例子：
<#list ["Joe", "Fred"] + ["Julia", "Kate"] as user>
- ${user}
</#list>
输出结果是：
- Joe
- Fred
- Julia
- Kate
         散列操作
?         连接操作：和字符串一样，使用+，如果具有相同的key，右边的值替代左边的值，例如：
<#assign ages = {"Joe":23, "Fred":25} + {"Joe":30, "Julia":18}>
- Joe is ${ages.Joe}
- Fred is ${ages.Fred}
- Julia is ${ages.Julia}
输出结果是：
- Joe is 30
- Fred is 25
- Julia is 18
         算术运算
?         ＋、－、×、／、％，下面是一个例子：
${x * x - 100}
${x / 2}
${12 % 10}
输出结果是（假设x为5）：
-75
2.5
2
?         操作符两边必须是数字，因此下面的代码是错误的：
${3 * "5"} <#-- WRONG! -->
?         使用+操作符时，如果一边是数字，一边是字符串，就会自动将数字转换为字符串，例如：
${3 + "5"}
输出结果是：
35
?         使用内建的int（后面讲述）获得整数部分，例如：
${(x/2)?int}
${1.1?int}
${1.999?int}
${-1.1?int}
${-1.999?int}
输出结果是（假设x为5）：
2
1
1
-1
-1
         比较操作符
?         使用=（或==，完全相等）测试两个值是否相等，使用!= 测试两个值是否不相等
?         =和!=两边必须是相同类型的值，否则会产生错误，例如<#if 1 = "1">会引起错误
?         Freemarker是精确比较，所以对"x"、"x  "和"X"是不相等的
?         对数字和日期可以使用<、<=、>和>=，但不能用于字符串
?         由于Freemarker会将>解释成FTL标记的结束字符，所以对于>和>=可以使用括号来避免这种情况，例如<#if (x > y)>
?         另一种替代的方法是，使用lt、lte、gt和gte来替代<、<=、>和>=
         逻辑操作符
?         &&（and）、||（or）、!（not），只能用于布尔值，否则会产生错误
?         例子：
<#if x < 12 && color = "green">
  We have less than 12 things, and they are green.
</#if>
<#if !hot> <#-- here hot must be a boolean -->
  It's not hot.
</#if>
         内建函数
?         内建函数的用法类似访问散列的子变量，只是使用“?”替代“.”，下面列出常用的一些函数
?         字符串使用的：
n         html：对字符串进行HTML编码
n         cap_first：使字符串第一个字母大写
n         lower_case：将字符串转换成小写
n         upper_case：将字符串转换成大写
n         trim：去掉字符串前后的空白字符
?         序列使用的：
n         size：获得序列中元素的数目
?         数字使用的：
n         int：取得数字的整数部分（如-1.9?int的结果是-1）
?         例子（假设test保存字符串"Tom & Jerry"）：
${test?html}
${test?upper_case?html}
输出结果是：
Tom &amp; Jerry
TOM &amp; JERRY
         操作符优先顺序
操作符组

操作符

后缀

[subvarName] [subStringRange] . (methodParams)

一元

+expr、-expr、!

内建

?

乘法

*、 / 、%

加法

+、-

关系

<、>、<=、>=（lt、lte、gt、gte）

相等

==（=）、!=

逻辑and

&&

逻辑or

||

数字范围

..


（4）Interpolation
         Interpolation有两种类型：
?         通用Interpolation：${expr}
?         数字Interpolation：#{expr}或#{expr; format}
         注意：Interpolation只能用于文本部分
         通用Interpolation
?         插入字符串值：直接输出表达式结果
?         插入数字值：根据缺省格式（由#setting指令设置）将表达式结果转换成文本输出；可以使用内建函数string格式化单个Interpolation，下面是一个例子：
<#setting number_format="currency"/>
<#assign answer=42/>
${answer}
${answer?string}  <#-- the same as ${answer} -->
${answer?string.number}
${answer?string.currency}
${answer?string.percent}
输出结果是：
$42.00
$42.00
42
$42.00
4,200%
?         插入日期值：根据缺省格式（由#setting指令设置）将表达式结果转换成文本输出；可以使用内建函数string格式化单个Interpolation，下面是一个使用格式模式的例子：
${lastUpdated?string("yyyy-MM-dd HH:mm:ss zzzz")}
${lastUpdated?string("EEE, MMM d, ''yy")}
${lastUpdated?string("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'")}
输出的结果类似下面的格式：
2003-04-08 21:24:44 Pacific Daylight Time
Tue, Apr 8, '03
Tuesday, April 08, 2003, 09:24:44 PM (PDT)
?         插入布尔值：根据缺省格式（由#setting指令设置）将表达式结果转换成文本输出；可以使用内建函数string格式化单个Interpolation，下面是一个例子：
<#assign foo=true/>
${foo?string("yes", "no")}
输出结果是：
yes
         数字Interpolation的#{expr; format}形式可以用来格式化数字，format可以是：
?         mX：小数部分最小X位
?         MX：小数部分最大X位
?         例子：
           <#-- If the language is US English the output is: -->
<#assign x=2.582/>
<#assign y=4/>
#{x; M2}   <#-- 2.58 -->
#{y; M2}   <#-- 4    -->
#{x; m1}   <#-- 2.6 -->
#{y; m1}   <#-- 4.0 -->
#{x; m1M2} <#-- 2.58 -->
#{y; m1M2} <#-- 4.0  -->

-----------------------------------------------------------

FreeMarker设计指南(4)
4、杂项
（1）用户定义指令
         宏和变换器变量是两种不同类型的用户定义指令，它们之间的区别是宏是在模板中使用macro指令定义，而变换器是在模板外由程序定义，这里只介绍宏
         基本用法
?         宏是和某个变量关联的模板片断，以便在模板中通过用户定义指令使用该变量，下面是一个例子：
<#macro greet>
  <font size="+2">Hello Joe!</font>
</#macro>
?         作为用户定义指令使用宏变量时，使用@替代FTL标记中的#
<@greet></@greet>
?         如果没有体内容，也可以使用：
<@greet/>
         参数
?         在macro指令中可以在宏变量之后定义参数，如：
<#macro greet person>
  <font size="+2">Hello ${person}!</font>
</#macro>
?         可以这样使用这个宏变量：
<@greet person="Fred"/> and <@greet person="Batman"/>
输出结果是：
  <font size="+2">Hello Fred!</font>
and   <font size="+2">Hello Batman!</font>

?         宏的参数是FTL表达式，所以下面的代码具有不同的意思：
<@greet person=Fred/>
?         这意味着将Fred变量的值传给person参数，该值不仅是字符串，还可以是其它类型，甚至是复杂的表达式
?         宏可以有多参数，下面是一个例子：
<#macro greet person color>
  <font size="+2" color="${color}">Hello ${person}!</font>
</#macro>
?         可以这样使用该宏变量：
<@greet person="Fred" color="black"/>
?         其中参数的次序是无关的，因此下面是等价的：
<@greet color="black" person="Fred"/>
?         只能使用在macro指令中定义的参数，并且对所有参数赋值，所以下面的代码是错误的：
<@greet person="Fred" color="black" background="green"/>
<@greet person="Fred"/>
?         可以在定义参数时指定缺省值，如：
<#macro greet person color="black">
  <font size="+2" color="${color}">Hello ${person}!</font>
</#macro>
?         这样<@greet person="Fred"/>就正确了
?         宏的参数是局部变量，只能在宏定义中有效
         嵌套内容
?         用户定义指令可以有嵌套内容，使用<#nested>指令执行指令开始和结束标记之间的模板片断
?         例子：
<#macro border>
  <table border=4 cellspacing=0 cellpadding=4><tr><td>
    <#nested>
  </tr></td></table>
</#macro>
这样使用该宏变量：
<@border>The bordered text</@border>
输出结果：
  <table border=4 cellspacing=0 cellpadding=4><tr><td>
    The bordered text
  </tr></td></table>

?         <#nested>指令可以被多次调用，例如：
<#macro do_thrice>
  <#nested>
  <#nested>
  <#nested>
</#macro>
<@do_thrice>
  Anything.
</@do_thrice>
输出结果：
  Anything.
  Anything.
  Anything.
?         嵌套内容可以是有效的FTL，下面是一个有些复杂的例子：
<@border>
  <ul>
  <@do_thrice>
    <li><@greet person="Joe"/>
  </@do_thrice>
  </ul>
</@border>
输出结果：
  <table border=4 cellspacing=0 cellpadding=4><tr><td>
      <ul>
    <li><font size="+2">Hello Joe!</font>

    <li><font size="+2">Hello Joe!</font>

    <li><font size="+2">Hello Joe!</font>

  </ul>

  </tr></td></table>
?         宏定义中的局部变量对嵌套内容是不可见的，例如：
<#macro repeat count>
  <#local y = "test">
  <#list 1..count as x>
    ${y} ${count}/${x}: <#nested>
  </#list>
</#macro>
<@repeat count=3>${y?default("?")} ${x?default("?")} ${count?default("?")}</@repeat>
输出结果：
    test 3/1: ? ? ?
    test 3/2: ? ? ?
    test 3/3: ? ? ?
?         
         在宏定义中使用循环变量
?         用户定义指令可以有循环变量，通常用于重复嵌套内容，基本用法是：作为nested指令的参数传递循环变量的实际值，而在调用用户定义指令时，在<@…>开始标记的参数后面指定循环变量的名字
?         例子：
<#macro repeat count>
  <#list 1..count as x>
    <#nested x, x/2, x==count>
  </#list>
</#macro>
<@repeat count=4 ; c, halfc, last>
  ${c}. ${halfc}<#if last> Last!</#if>
</@repeat>
输出结果：
  1. 0.5
  2. 1
  3. 1.5
  4. 2 Last!

?         指定的循环变量的数目和用户定义指令开始标记指定的不同不会有问题
n         调用时少指定循环变量，则多指定的值不可见
n         调用时多指定循环变量，多余的循环变量不会被创建
（2）在模板中定义变量
         在模板中定义的变量有三种类型：
?         plain变量：可以在模板的任何地方访问，包括使用include指令插入的模板，使用assign指令创建和替换
?         局部变量：在宏定义体中有效，使用local指令创建和替换
?         循环变量：只能存在于指令的嵌套内容，由指令（如list）自动创建；宏的参数是局部变量，而不是循环变量
         局部变量隐藏（而不是覆盖）同名的plain变量；循环变量隐藏同名的局部变量和plain变量，下面是一个例子：
<#assign x = "plain">
1. ${x}  <#-- we see the plain var. here -->
<@test/>
6. ${x}  <#-- the value of plain var. was not changed -->
<#list ["loop"] as x>
    7. ${x}  <#-- now the loop var. hides the plain var. -->
    <#assign x = "plain2"> <#-- replace the plain var, hiding does not mater here -->
    8. ${x}  <#-- it still hides the plain var. -->
</#list>
9. ${x}  <#-- the new value of plain var. -->

<#macro test>
  2. ${x}  <#-- we still see the plain var. here -->
  <#local x = "local">
  3. ${x}  <#-- now the local var. hides it -->
  <#list ["loop"] as x>
    4. ${x}  <#-- now the loop var. hides the local var. -->
  </#list>
  5. ${x}  <#-- now we see the local var. again -->
</#macro>
输出结果：
1. plain
  2. plain
  3. local
    4. loop
  5. local
6. plain
    7. loop
    8. loop
9. plain2

         内部循环变量隐藏同名的外部循环变量，如：
<#list ["loop 1"] as x>
  ${x}
  <#list ["loop 2"] as x>
    ${x}
    <#list ["loop 3"] as x>
      ${x}
    </#list>
    ${x}
  </#list>
  ${x}
</#list>
输出结果：
  loop 1
    loop 2
      loop 3
    loop 2
  loop 1
         模板中的变量会隐藏（而不是覆盖）数据模型中同名变量，如果需要访问数据模型中的同名变量，使用特殊变量global，下面的例子假设数据模型中的user的值是Big Joe：
<#assign user = "Joe Hider">
${user}          <#-- prints: Joe Hider -->
${.globals.user} <#-- prints: Big Joe -->
（3）名字空间
         通常情况，只使用一个名字空间，称为主名字空间
         为了创建可重用的宏、变换器或其它变量的集合（通常称库），必须使用多名字空间，其目的是防止同名冲突
         创建库
?         下面是一个创建库的例子（假设保存在lib/my_test.ftl中）：
<#macro copyright date>
  <p>Copyright (C) ${date} Julia Smith. All rights reserved.
  <br>Email: ${mail}</p>
</#macro>
<#assign mail = "jsmith@acme.com">
?         使用import指令导入库到模板中，Freemarker会为导入的库创建新的名字空间，并可以通过import指令中指定的散列变量访问库中的变量：
<#import "/lib/my_test.ftl" as my>
<#assign mail="fred@acme.com">
<@my.copyright date="1999-2002"/>
${my.mail}
${mail}
输出结果：
  <p>Copyright (C) 1999-2002 Julia Smith. All rights reserved.
  <br>Email: jsmith@acme.com</p>
jsmith@acme.com
fred@acme.com
可以看到例子中使用的两个同名变量并没有冲突，因为它们位于不同的名字空间
         可以使用assign指令在导入的名字空间中创建或替代变量，下面是一个例子：
<#import "/lib/my_test.ftl" as my>
${my.mail}
<#assign mail="jsmith@other.com" in my>
${my.mail}
         输出结果：
jsmith@acme.com
jsmith@other.com
         数据模型中的变量任何地方都可见，也包括不同的名字空间，下面是修改的库：
<#macro copyright date>
  <p>Copyright (C) ${date} ${user}. All rights reserved.</p>
</#macro>
<#assign mail = "${user}@acme.com">  
         假设数据模型中的user变量的值是Fred，则下面的代码：
<#import "/lib/my_test.ftl" as my>
<@my.copyright date="1999-2002"/>
${my.mail}  
         输出结果：
  <p>Copyright (C) 1999-2002 Fred. All rights reserved.</p>
