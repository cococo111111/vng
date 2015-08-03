#!/bin/sh
/bin/ps -ef |grep "GW-SFONE/cdr" |grep -v grep |awk '{print$2}' |xargs kill -9
