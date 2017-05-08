修改startWeblogic.sh脚本文件
```shell
if [ "${WLS_REDIRECT_LOG}" = "" ] ; then
	echo "Starting WLS with line:"
	echo "${JAVA_HOME}/bin/java ${JAVA_VM} ${MEM_ARGS} -Dweblogic.Name=${SERVER_NAME} -Djava.security.policy=${WL_HOME}/server/lib/weblogic.policy ${JAVA_OPTIONS} ${PROXY_SETTINGS} ${SERVER_CLASS}"
	${JAVA_HOME}/bin/java ${JAVA_VM} ${MEM_ARGS} -Djava.security.egd=file:/dev/./urandom -Dweblogic.Name=${SERVER_NAME} -Djava.security.policy=${WL_HOME}/server/lib/weblogic.policy ${JAVA_OPTIONS} ${PROXY_SETTINGS} ${SERVER_CLASS}
else
	echo "Redirecting output from WLS window to ${WLS_REDIRECT_LOG}"
	${JAVA_HOME}/bin/java ${JAVA_VM} ${MEM_ARGS} -Djava.security.egd=file:/dev/./urandom -Dweblogic.Name=${SERVER_NAME} -Djava.security.policy=${WL_HOME}/server/lib/weblogic.policy ${JAVA_OPTIONS} ${PROXY_SETTINGS} ${SERVER_CLASS}  >"${WLS_REDIRECT_LOG}" 2>&1
fi
```
