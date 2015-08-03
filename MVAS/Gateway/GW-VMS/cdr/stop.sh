/cdr
/bin/ps -ef |grep "GW-VMS/cdr" |grep -v grep |awk '{print$2}' |xargs kill -9
