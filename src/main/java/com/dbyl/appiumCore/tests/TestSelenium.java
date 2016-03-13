package main.java.com.dbyl.appiumCore.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestSelenium {
	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "9.2");
		capabilities.setCapability("deviceName", "iPhone 6");
		capabilities.setCapability("browserName", "safari");
		IOSDriver<MobileElement> driver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		
        driver.get("http://www.baidu.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println(driver.getTitle());
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('index-kw').value='appium'");
        // driver.findElementByXPath("//textarea[@id='index-kw']").sendKeys("appium");
        // driver.tap(1,
        // driver.findElement(By.xpath("//button[@id='index-bn']")), 100);
        driver.findElement(By.xpath("//button[@id='index-bn']")).click();
        System.out.println(driver.getTitle());
        // snapshot((TakesScreenshot) driver, "after_search.png");
        Assert.assertTrue(driver.getTitle().contains("appium"));
	}
}
