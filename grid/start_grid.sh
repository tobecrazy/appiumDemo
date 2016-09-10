#!/bin/bash -w
echo "java -jar selenium-server-standalone-2.53.1.jar -role hub "
ps -ef |grep -w selenium|grep -v grep|awk  '{print $2}'|xargs kill -9 

java -jar $PWD/selenium-server-standalone-2.53.1.jar -role hub -port 4444 -Hubhost localhost &2 > $PWD/../logs/hub.log 
echo "sleep 20 "
sleep 30
echo "sleep 20 done "
ps -ef |grep -w appium|grep -v grep|awk  '{print $2}'|xargs kill -9 
echo "appium --nodeconfig node4723.json -p 4723"
appium --nodeconfig  node4723.json -p 4723 &2> $PWD/../logs/node4723.log
echo "appium --nodeconfig node4724 -p 4724"
appium --nodeconfig  node4724.json -p 4724 &2>$PWD/../logs/node4724.log
