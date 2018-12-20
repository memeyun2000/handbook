wps 报错：No necessary symbol fonts

将同级目录的附件解压后放到/usr/share/fonts

需要将目录一起放到该文件夹下

```
[root@localhost 下载]# cd /usr/share/fonts
[root@localhost fonts]# ll
总用量 164
drwxr-xr-x. 2 root root 4096 12月 19 2018 abattis-cantarell
drwxr-xr-x. 2 root root 4096 12月 19 2018 cjkuni-uming
drwxr-xr-x. 4 root root 4096 12月 19 2018 default
drwxr-xr-x. 2 root root 4096 12月 19 2018 dejavu
drwxr-xr-x. 2 root root 4096 12月 19 2018 gnu-free
drwxr-xr-x. 2 root root 4096 12月 19 2018 google-crosextra-caladea
drwxr-xr-x. 2 root root 4096 12月 19 2018 google-crosextra-carlito
drwxr-xr-x. 2 root root 4096 12月 19 2018 google-noto-emoji
drwxr-xr-x. 2 root root 4096 12月 19 2018 jomolhari
drwxr-xr-x. 2 root root 4096 12月 19 2018 khmeros
drwxr-xr-x. 2 root root 4096 12月 19 2018 liberation
drwxr-xr-x. 2 root root 4096 12月 19 2018 lklug
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-assamese
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-bengali
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-devanagari
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-gujarati
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-kannada
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-malayalam
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-marathi
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-nepali
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-oriya
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-punjabi
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-tamil
drwxr-xr-x. 2 root root 4096 12月 19 2018 lohit-telugu
drwxr-xr-x. 2 root root 4096 12月 19 2018 madan
drwxr-xr-x. 2 root root 4096 12月 19 2018 nhn-nanum
drwxr-xr-x. 2 root root 4096 12月 19 2018 open-sans
drwxr-xr-x. 2 root root 4096 12月 19 2018 overpass
drwxr-xr-x. 2 root root 4096 12月 19 2018 paktype-naskh-basic
drwxr-xr-x. 2 root root 4096 12月 19 2018 paratype-pt-sans
drwxr-xr-x. 2 root root 4096 12月 19 2018 sil-abyssinica
drwxr-xr-x. 2 root root 4096 12月 19 2018 sil-nuosu
drwxr-xr-x. 2 root root 4096 12月 19 2018 sil-padauk
drwxr-xr-x. 2 root root 4096 12月 19 2018 smc
drwxr-xr-x. 2 root root 4096 12月 19 2018 stix
drwxr-xr-x. 2 root root 4096 12月 19 2018 thai-scalable
drwxr-xr-x. 2 root root 4096 12月 19 2018 ucs-miscfixed
drwxr-xr-x. 2 root root 4096 12月 19 2018 vlgothic
drwxrwxrwx. 2 sec  sec  4096 5月  27 2016 wps_symbol_fonts
drwxr-xr-x. 2 root root 4096 12月 19 2018 wqy-microhei
drwxr-xr-x. 2 root root 4096 12月 19 2018 wqy-zenhei
[root@localhost fonts]# 

```
