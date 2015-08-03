#!/bin/sh
/bin/ps -ef |grep "GW-ADSVIETEL/Gateway" |grep -v grep |awk '{print$2}' |xargs kill 
