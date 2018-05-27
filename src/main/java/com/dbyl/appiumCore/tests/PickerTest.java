package main.java.com.dbyl.appiumCore.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppLogger;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

public class PickerTest {
	AndroidDriver<MobileElement> driver;
	String keyword = "appium";
	URL url;
	AppLogger logger = new AppLogger(PickerTest.class);

	@BeforeClass
	public void startAppiumServer() throws IOException, InterruptedException {
		url = AppiumServerUtils.getInstance().startServer("127.0.0.1", 4723);
		logger.debug("start Appium Server");
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
		// if no need install don't add this
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "webview.apk");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.testerhome.webview");
		// support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		// no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
		logger.info("connet to Appium server  " + url.toString());
		driver = new AndroidDriver<MobileElement>(url, capabilities);

	}

	@Test(groups = { "picker" })
	public void webViewTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		MobileElement clickNextPage = driver.findElementById("com.testerhome.webview:id/next_page");
		clickNextPage.click();
	 
	 

		// 方法二
		MobileElement pickLeft1 = driver.findElementByAndroidUIAutomator(
				"new UiSelector().className(\"android.widget.TextView\").text(\"09\").index(1)");
		 
		MobileElement pickLeft2 = driver.findElementByAndroidUIAutomator(
				"new UiSelector().className(\"android.widget.TextView\").text(\"10\").index(2)");
		 
		MobileElement pickRight1 = driver.findElementByAndroidUIAutomator(
				"new UiSelector().className(\"android.widget.TextView\").text(\"00\").index(1)"); 
		MobileElement confirmButton = driver.findElementByXPath("//android.widget.Button[contains(@text,'确定')]");
		confirmButton.click();
		 

	}

	/**
	 * @author young
	 * @param by
	 * @return
	 */
	private boolean isElementPresent(By by) {
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		logger.info("quit");
		driver.quit();
	}
}