#!/bin/sh
/bin/ps -ef |grep "GW-ADSVIETEL/cdr" |grep -v grep |awk '{print$2}' |xargs kill -9
