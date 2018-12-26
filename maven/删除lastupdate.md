> linux 下 删除lastupdate文件

```
find ./  -name "*.lastUpdated" -exec grep -q "Could not transfer" {} \; -print -exec rm {} \;
```

> windows 下 删除lastupdate 文件

```
for /r %i in (*.lastUpdated) do del %i
```
