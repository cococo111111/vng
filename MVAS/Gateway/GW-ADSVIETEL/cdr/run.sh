#!/bin/sh
/usr/java/jdk1.5.0_16/bin/java -Xms128M -Xmx512M -cp /home/vngame/GW-ADSVIETEL/cdr/Bilsys.jar:lib/classes12.zip:lib/primrose.jar:lib/mysql-connector-java-5.1.6-bin.jar com.vasc.smpp.cdr.CDRServer  2>&1
