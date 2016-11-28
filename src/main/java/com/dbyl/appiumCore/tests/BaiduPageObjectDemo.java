package main.java.com.dbyl.appiumCore.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumCore.pageActions.BaiduPageAction;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class BaiduPageObjectDemo {
	AppiumDriver<MobileElement> driver;
	public URL url;
	boolean isAndroid = true;

	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() {
		AppiumServerUtils.getInstance().stopServer();
		url = AppiumServerUtils.getInstance().startAppiumServerByDefault();

	}

	@BeforeMethod
	public void setUpDriver() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (isAndroid) {
			capabilities.setCapability("browserName", "chrome");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
			capabilities.setCapability("clearSystemFiles", true);
			driver = new AndroidDriver<MobileElement>(url, capabilities);
		} else {
			capabilities.setCapability("browserName", "safari");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("platformVersion", "10.1");
			capabilities.setCapability("deviceName", "iPhone SE");
			driver = new IOSDriver<MobileElement>(url, capabilities);
		}

	}

	@Test
	public void POTest() {
		BaiduPageAction.setDriver(driver);
		BaiduPageAction.search("Appium");

	}

	@AfterClass
	public void afterTest() {
		driver.quit();
	}

}
