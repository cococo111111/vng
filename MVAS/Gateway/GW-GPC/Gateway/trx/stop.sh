#!/bin/sh
/bin/ps -ef |grep "GW-GPC/Gateway" |grep -v grep |awk '{print$2}' |xargs kill 
