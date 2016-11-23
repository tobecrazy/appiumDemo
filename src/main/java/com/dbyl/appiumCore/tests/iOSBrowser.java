package main.java.com.dbyl.appiumCore.tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class iOSBrowser {
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

		driver.get("http://www.baidu.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String status = (String) jse.executeScript("var status=document.readyState;return status");
		Assert.assertTrue(status.contains("complete"));
		System.out.println(driver.getTitle());
		jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('index-kw').value='appium'");
		driver.findElement(By.xpath("//button[@id='index-bn']")).click();
		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("appium"));
	}

	@AfterClass
	public void afterTest() {
		driver.quit();
	}

}
