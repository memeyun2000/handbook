
```sql
DROP PROCEDURE "PLName"
@
CREATE PROCEDURE "PLName"(--存储过程名字
IN IN_ID BIGINT ,                    --以下全是输入参数
IN IN_ENTNAME VARCHAR(200) ,
IN IN_REGNO VARCHAR(50),
IN IN_PASSWORD VARCHAR(20),
IN IN_LEREP VARCHAR(300),
IN IN_CERTYPE CHARACTER(1),
IN IN_CERNO VARCHAR(50),
IN IN_LINKMAN VARCHAR(50),
IN IN_SEX CHARACTER(1),
IN IN_MOBTEL VARCHAR(30),
IN IN_REQDATE TIMESTAMP,
IN IN_REMITEM VARCHAR(300),
IN IN_STATE CHARACTER(1),
IN IN_TIMESTAMP TIMESTAMP
)
BEGIN

   declare V_RESULT  BIGINT;     --声明变量
   DELETE FROM  TableNameA WHERE ID = IN_ID;

   SET V_RESULT = NULL;          --为变量赋值
  --检查用户输入的信息是否合法

  select b.id INTO V_RESULT  from TableNameB b,TableNameC c where 正常的判断条件  
  if(V_RESULT IS NOT NULL)  then ---如果合法，执行下面的insert语句
   INSERT INTO TableNameA(ID,ENTNAME,REGNO,PASSWORD,LEREP,CERTYPE,CERNO,LINKMAN,SEX,MOBTEL,REQDATE,REMITEM,STATE,TIMESTAMP)
   VALUES(IN_ID,IN_ENTNAME,IN_REGNO,IN_PASSWORD,IN_LEREP,IN_CERTYPE,IN_CERNO,IN_LINKMAN,IN_SEX,IN_MOBTEL,IN_REQDATE,IN_REMITEM,IN_STATE,IN_TIMESTAMP);
  end if;
    commit;
END
@
```

.拷贝到DB2客户端工具中直接执行
　　特别注意：执行时将 ";" 改成"@"，之前很多错误都和它有关，比如：“该命令被当作 SQL
语句来处理，因为它不是有效的命令行处理器命令”正是这个问题花费了很长时间，严重影响心情
    2.将上面的语句保存为test.db2文件放到任意目录下（比如D盘根目录），然后在cmd输入db2cmd 然后输入db2 -td@ -vf  D：\test.db2即可
