package main.java.com.dbyl.appiumCore.tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumCore.pageActions.BaiduPageAction;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class iOSPageObject {
	IOSDriver<MobileElement> driver;
	public URL url;

	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() {
		AppiumServerUtils.getInstance().stopServer();
		url = AppiumServerUtils.getInstance().startAppiumServerByDefault();

	}

	@BeforeMethod
	public void setUpDriver() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("browserName", "safari");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "10.1");
		capabilities.setCapability("deviceName", "iPhone SE");
		driver = new IOSDriver<MobileElement>(url, capabilities);

	}

	@Test
	public void iOSTest() {
		BaiduPageAction.setDriver(driver);
		BaiduPageAction.search("Appium");
		
	 
	}

	@AfterClass
	public void afterTest() {
		driver.quit();
	}

}
