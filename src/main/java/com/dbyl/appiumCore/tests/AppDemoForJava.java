package main.java.com.dbyl.appiumCore.tests;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.URL;

/**
 * @since 2016-10-17
 * @author Young
 */

public class AppDemoForJava {
	private AndroidDriver<MobileElement> driver;

	@Test
	public void Demo() throws Exception {
		// set up appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		//for native app set null, for web test please set chrome or firefox 
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		//simulator version 4.4
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
		// if no need install don't add this
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "apppiumDemo.apk");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		//package name 
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cn.dbyl.appiumdemo");
		// // support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		// no need sign
		capabilities.setCapability("noSign", "True");
		//launcher activity
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
		String url = "http://localhost:4723/wd/hub";
		driver = new AndroidDriver<MobileElement>(new URL(url), capabilities);

		MobileElement text = driver.findElementById("cn.dbyl.appiumdemo:id/text1");
		Assert.assertEquals(text.getText(), "appiumDemo");
	

		MobileElement button = driver.findElementByXPath("//android.widget.Button[@text='button']");
		driver.closeApp();
		button.click();
		text = driver.findElementById("cn.dbyl.appiumdemo:id/text1");
		Assert.assertEquals(text.getText(), "You just click the button");
	

	}

}