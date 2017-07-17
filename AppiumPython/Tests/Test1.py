#!/usr/bin/python
import unittest
from appium import webdriver
import os,sys
class AppiumDemoTestCase(unittest.TestCase):
	def setUp(self):
		current_folder=os.getcwd()
		desired_caps={}
		desired_caps['platformName']='Android'
		desired_caps['platformVersion']='6.0'
		desired_caps['deviceName']='emulator-5554'
		print(current_folder)
		desired_caps['app']=current_folder+'/../../apps/apppiumDemo.apk'
		self.driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)
	def testAppium(self):
		text=self.driver.find_element_by_id('cn.dbyl.appiumdemo:id/text1')
		self.assertEqual(text.get_attribute('text'),'appiumDemo')
		button=self.driver.find_element_by_xpath("//android.widget.Button[@text='button']")
		button.click()
		text=self.driver.find_element_by_id('cn.dbyl.appiumdemo:id/text1')
		self.assertEqual(text.get_attribute('text'),"You just click the button")
if __name__== '__main__':
	unittest.main(verbosity=2)
