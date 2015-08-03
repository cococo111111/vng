main_home=`dirname $0`/
MAIN_CONF=$main_home/conf
main_bin=$main_home/bin
CLASSPATH=$MAIN_CONF:$main_bin

for jar in $main_home/lib/*.jar; do
    CLASSPATH=$CLASSPATH:$jar
done

JVM_OPTS=" \
        -ea \
        -Xdebug \
        -Xms128M \
        -Xmx1G \
        -XX:SurvivorRatio=8 \
        -XX:TargetSurvivorRatio=90 \
        -XX:+AggressiveOpts \
        -XX:+UseParNewGC \
        -XX:+UseConcMarkSweepGC \
        -XX:+CMSParallelRemarkEnabled \
        -XX:+HeapDumpOnOutOfMemoryError \
        -XX:SurvivorRatio=128 \
        -XX:MaxTenuringThreshold=0 "
