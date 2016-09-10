#!/bin/bash -w
print "java -jar selenium-server-standalone-2.53.1.jar -role hub "
ps -ef |grep -w selenium|grep -v grep|awk  '{print $2}'|xargs kill -9 
java -jar $PWD/selenium-server-standalone-2.53.1.jar -role hub -port 4444 -Hubhost localhost &1 > $PWD/../logs/hub.log 
echo "sleep 20 "
sleep 30
echo "sleep 20 done "
ps -ef |grep -w appium|grep -v grep|awk  '{print $2}'|xargs kill -9 
echo  "appium --nodeconfig node4723.json -p 4723"
appium --nodeconfig  node4723.json -p 4723 &1> $PWD/../logs/node4723.log
echo  "appium --nodeconfig node4724 -p 4724"
appium --nodeconfig  node4724.json -p 4724 &1>$PWD/../logs/node4724.log
echo  "appium --nodeconfig node4725 -p 4725"
appium --nodeconfig  node4724.json -p 4725 &1>$PWD/../logs/node4725.log
