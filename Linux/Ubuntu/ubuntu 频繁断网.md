好长好长时间没来百度空间了，最近闲来无事，正好弥补之前的空缺了！
跟Ubuntu打交道已有很长一段时间了，期间遇到了很多问题，我把遇到的一些问题及找到的解决方案记录下来，我想这可能会对那些跟我有同样境遇的人有所帮助吧。
最近刚安装好Ubuntu10.04 Lucid beta1,就遇到了比较恼人的问题，就是网络经常掉线的问题。网上着好好的，突然间就打不开网页了，再等一会就又好了，让人十分的不爽。我在网上搜了一下，找到了原因，以下是网络掉线的原因及解决方法：
ppp 的很多选项都是默认的，其中lcp-echo-failure次数被设为4，而lcp-echo-interval设为30秒。也就是说，如果120秒钟 之内，ADSL服务器没有给回echo-reply信号，UBuntu便会认为网络已经出了问题，就会断开网络，搞得人非常不爽。症结找到了，问题就要解 决了，打开配置文件/etc/ppp/options，将lcp-echo-failure次数设为一个较大的数值就行了，我将该值修改为30，后面上网 就比较顺利了。
sudo gedit /etc/ppp/options
再说一下，Ubuntu下如何配置ADSL上网，我觉得最简单的方法就是使用命令，配置一次后就不用再管了，以后开机会自动启动的。所使用的命令是：
sudo pppoeconf
然后一步步的按上面的说明进行配置，输入你的帐号和密码，敲几次回车键就OK了。
