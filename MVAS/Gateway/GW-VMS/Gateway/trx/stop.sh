#!/bin/sh
/bin/ps -ef |grep "GW-VMS/Gateway" |grep -v grep |awk '{print$2}' |xargs kill 
