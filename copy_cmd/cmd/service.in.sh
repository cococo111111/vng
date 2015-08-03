home=`dirname $0`/..

CONF=$home/conf

bin=$home/bin

# JAVA_HOME can optionally be set here
#JAVA_HOME=/usr/local/jdk6

# The java classpath (required)
CLASSPATH=$CONF:$bin

for jar in $home/dist/lib/*.jar; do
    CLASSPATH=$CLASSPATH:$jar
done

for jar in $home/dist/*.jar; do
    CLASSPATH=$CLASSPATH:$jar
done

# Arguments to pass to the JVM
JVM_OPTS=" \
	-Dzname=$ZNAME \
	-Dzport=$ZPORT \
	-Xdebug -Xrunjdwp:transport=dt_socket,address=20117,server=y,suspend=n\
	-Xmx$XMX \
	-XX:SurvivorRatio=8 \
        -XX:TargetSurvivorRatio=90 \
        -XX:+AggressiveOpts \
        -XX:+UseParNewGC \
        -XX:+UseConcMarkSweepGC \
-DproxyHost=10.30.22.23 -DproxyPort=80
        -XX:+CMSParallelRemarkEnabled \
        -XX:MaxTenuringThreshold=2 \
		-Djava.rmi.server.hostname=$JMXHOST \
        -Dcom.sun.management.jmxremote.port=$JMXPORT \
        -Dcom.sun.management.jmxremote.ssl=false \
        -Dcom.sun.management.jmxremote.authenticate=false"
