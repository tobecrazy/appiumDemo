package main.java.com.dbyl.appiumCore.tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
//import main.java.com.dbyl.appiumServer.AppiumServerUtils;

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
	DesiredCapabilities capabilities = new DesiredCapabilities();

	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() throws MalformedURLException {
		capabilities.setCapability("platformName", "iOS");
		// 只需要在这里使用data provider控制一下版本，传入不同的版本号
		capabilities.setCapability("platformVersion", "11.2");
		// AppiumServerUtils.getInstance().stopServer();
		url = new URL("http://localhost:4444/wd/hub");
//		url = new URL("http://localhost:4726");
		// AppiumServerUtils.getInstance().startServer("127.0.0.1", 4723,
		// capabilities);

	}

	@BeforeMethod
	public void setUpDriver() throws MalformedURLException {

		capabilities.setCapability("browserName", "safari");
		if (capabilities.getCapability("platformVersion").toString().contains("11")) {
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			capabilities.setCapability("platformVersion", "11.2");
			capabilities.setCapability("deviceName", "iPhone 8");
		} else {
			capabilities.setCapability("platformVersion", "8.4");
			capabilities.setCapability("deviceName", "iPhone 5s");
		}

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
