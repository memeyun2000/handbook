Buffer的分配
要想获得一个Buffer对象首先要进行分配。 每一个Buffer类都有一个allocate方法。下面是一个分配48字节capacity的ByteBuffer的例子。
```
ByteBuffer buf = ByteBuffer.allocate(48);
```
这是分配一个可存储1024个字符的CharBuffer：
```
1CharBuffer buf = CharBuffer.allocate(1024);
```
