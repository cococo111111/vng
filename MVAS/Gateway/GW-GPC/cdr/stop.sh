#!/bin/sh
/bin/ps -ef |grep "GW-GPC/cdr" |grep -v grep |awk '{print$2}' |xargs kill -9
