from com.android.monkeyrunner import MonkeyRunner,MonkeyDevice  
device=MonkeyRunner.waitForConnection()  
device.startActivity(component="cn.dbyl.young.tianqi/cn.dbyl.young.tianqi.activity.StartActivity") 
device.touch(300,300,'DOWN_AND_UP')
MonkeyRunner.alert("Monkey Runner","title name","OK")
device.press('KEYCODE_HOME',MonkeyDevice.DOWN_AND_UP)
device.type('Monkey Runner')
device.reboot()
