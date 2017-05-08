从Buffer中读取数据
从Buffer中读取数据有两种方式：
  1. 从Buffer读取数据到Channel。
  2. 使用get()方法从Buffer中读取数据。
从Buffer读取数据到Channel的例子：
```
//read from buffer into channel.
int bytesWritten = inChannel.write(buf);
```
使用get()方法从Buffer中读取数据的例子
```
byte aByte = buf.get();
```
get方法有很多版本，允许你以不同的方式从Buffer中读取数据。例如，从指定position读取，或者从Buffer中读取数据到字节数组。更多Buffer实现的细节参考JavaDoc。
```
rewind()方法
```
Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）。
