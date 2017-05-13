
> 生成嵌套结构的数据

```sql
select
  t1.emplid,
  named_struct(
    'emplid',collect_list(t2.emplid),
    'jobcode',collect_list(t2.jobcode),
    'effdt',collect_list(t2.effdt)
  )
from ps_cmbc_hd_empl t1 ,ps_job t2
where t1.emplid = t2.emplid
group by t1.emplid
limit 1
```
