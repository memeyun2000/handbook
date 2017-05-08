向Buffer中写数据
写数据到Buffer有两种方式：
 ● 从Channel写到Buffer。
 ● 通过Buffer的put()方法写到Buffer里。
从Channel写到Buffer的例子
```
intbytesRead = inChannel.read(buf);//read into buffer.
```
通过put方法写Buffer的例子：
```
buf.put(127);
```
put方法有很多版本，允许你以不同的方式把数据写入到Buffer中。例如， 写到一个指定的位置，或者把一个字节数组写入到Buffer。 更多Buffer实现的细节参考JavaDoc。
