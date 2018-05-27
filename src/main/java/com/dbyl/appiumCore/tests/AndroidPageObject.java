package main.java.com.dbyl.appiumCore.tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
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

public class AndroidPageObject {
	AndroidDriver<MobileElement> driver;
	public URL url;

	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() {
		AppiumServerUtils.getInstance().stopServer();
		url = AppiumServerUtils.getInstance().startAppiumServerByDefault();

	}

	@BeforeMethod
	public void setUpDriver() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
		// if no need install don't add this
		driver = new AndroidDriver<MobileElement>(url, capabilities);

	}

	@Test
	public void AndroidTest() {
		BaiduPageAction.setDriver(driver);
		BaiduPageAction.search("Appium");
 
	}

	@AfterClass
	public void afterTest() {
		driver.quit();
	}

}
