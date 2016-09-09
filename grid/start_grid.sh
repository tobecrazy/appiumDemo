echo "java -jar selenium-server-standalone-2.53.1.jar -role hub "
ps -ef |grep selenium|grep -v grep|awk  '{print $2}'|xargs kill -9 
java -jar selenium-server-standalone-2.53.1.jar -role hub & > $PWD/../logs/hub.log 
echo "sleep 20 "
sleep 30
echo "sleep 20 done "
ps -ef |grep appium|grep -v grep|awk  '{print $2}'|xargs kill -9 
appium --nodeconfig  node4723.json -p 4723 &> $PWD/../logs/node4723.log
echo "appium --nodeconfig node4723.json -p 4723"
appium --nodeconfig  node4724.json -p 4724 & >$PWD/../logs/node4724.log
echo "appium --nodeconfig node4724 -p 4724"
