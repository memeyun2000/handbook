
```sql
create index idx_epos_taizhang_01 on table  test_epos_taizhang (host_cust_id)
as 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler'
with deferred rebuild
in table idx_epos_taizhang_table;

alter index idx_epos_taizhang_01 on test_epos_taizhang rebuild

```
