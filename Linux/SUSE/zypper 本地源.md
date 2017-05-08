# mount -o loop SLES-11-SP3-DVD-x86_64-GM-DVD1.iso /cdrom
# zypper ar file:///cdrom local-sles
Adding repository 'local-sles' [done]
Repository 'local-sles' successfully added
Enabled: Yes
Autorefresh: No
GPG check: Yes
URI: file:/cdrom/
或者
linux-pgo6:/etc/zypp/repos.d # zypper addrepo iso:/?iso=/root/openSUSE-11.4-GNOME-LiveCD-x86_64.iso  dvdrepo
Adding repository 'dvdrepo' [done]
Repository 'dvdrepo' successfully added
Enabled: Yes
Autorefresh: No
URI: iso:///?iso=/root/openSUSE-11.4-GNOME-LiveCD-x86_64.iso



查看本地源：
```
linux-pgo6:/etc/zypp/repos.d # zypper repos --name
# | Alias       | Name        | Enabled | Refresh
--+-------------+-------------+---------+--------
1 | suse-zypper | suse-zypper | Yes     | No     
```
本地源目录：
```
/etc/zypp/repos.d
```
