#!/bin/sh
/usr/java/jdk1.5.0_16/bin/java -Xms128M -Xmx512M -cp /home/vngame/GW-GPC/Gateway/trx/Sendman.jar:./smpp.jar:./primrose.jar:./mysql-connector-java-5.0.5-bin.jar com.vmg.smpp.gateway.Gateway
