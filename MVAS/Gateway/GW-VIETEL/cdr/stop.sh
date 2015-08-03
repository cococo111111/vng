#!/bin/sh
/bin/ps -ef |grep "GW-VIETEL/cdr" |grep -v grep |awk '{print$2}' |xargs kill -9
