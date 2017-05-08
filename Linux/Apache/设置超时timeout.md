```xml
<IfModule ssl_module>
SSLRandomSeed startup builtin
SSLRandomSeed connect builtin
</IfModule>

loadModule weblogic_module modules/mod_wl_22.so
<VirtualHost *:8080>
  <IfModule mod_weblogic.c>
      WebLogicCluster localhost:7001
      CookieName CMBCAWPWD-CLUSTER-PORTAL-WDSESSIONID
      WLIOTimeoutSecs 30000
  </IfModule>

  <Location / >
      SetHandler weblogic-handler
  </Location>
</VirtualHost>
``
