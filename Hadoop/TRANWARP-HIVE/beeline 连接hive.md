```shell
beeline -u 'jdbc:hive2://BIGL1TMP:10000/fxjh;principal=hive/BIGL1TMP@TDH;kuser=fxjh@TDH;keytab=/home/fxjh/fxjh.keytab;authentication=kerberos;krb5conf=/etc/krb5.conf'

beeline -u 'jdbc:hive2://BDCLAP01:10000/fxjh;principal=hive/BDCLAP01@TDH;kuser=fxjh@TDH;keytab=/home/fxjh/fxjh.keytab;authentication=kerberos;krb5conf=/etc/krb5.conf'
```
