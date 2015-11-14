package com.dbyl.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class iOSTest {
	private IOSDriver driver;
	private boolean isInstall = true;

	 
	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "IOS");
		capabilities.setCapability("deviceName", "iPhone Simulator");
		capabilities.setCapability("platformVersion", "8.4");
		// if no need install don't add this
		if (isInstall) {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "zhihu.ipa");
			capabilities.setCapability("app", app.getAbsolutePath());
		}
		capabilities.setCapability("appPackage", "com.zhihu.android");
		// support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		// no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability("appActivity", ".ui.activity.GuideActivity");
		driver = new IOSDriver (new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		 
	}

	@Test
	public void login() {

	 
		// wait for 20s
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// find login userName and password editText
	    WebElement userNameInputbox =driver.findElementById("com.zhihu.android:id/email_or_phone");
	    userNameInputbox.sendKeys("seleniumcookies@126.com");
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    
	    WebElement passwordInputBox=driver.findElementById("com.zhihu.android:id/password");
	    passwordInputBox.sendKeys("cookies123");
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    WebElement confirmButton=driver.findElementById("com.zhihu.android:id/btn_confirm");
		if(confirmButton.isEnabled())
		{
			confirmButton.click();
		}
		else
		{
			Assert.assertTrue(false,"Login failed");
		}

		// find ok button byName
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		// find keyword ��ҳ and verify it is display
		Assert.assertTrue(driver.findElement(By.name("��ҳ")).isDisplayed());

	}
 

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}
 
}