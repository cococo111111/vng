#!/bin/sh
/bin/ps -ef |grep "GW-VIETEL/Gateway" |grep -v grep |awk '{print$2}' |xargs kill 
