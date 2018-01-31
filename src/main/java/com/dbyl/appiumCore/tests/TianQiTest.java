package main.java.com.dbyl.appiumCore.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @since 2015-6
 * @author Young
 */
public class TianQiTest {
	public static AndroidDriver<MobileElement> driver;
	public URL url;

	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() throws MalformedURLException {
		// url = AppiumServerUtils.getInstance().startAppiumServerByDefault();
		url = new URL("http://127.0.0.1:4444/wd/hub");

	}

	@BeforeClass(alwaysRun = true, dependsOnMethods = { "startAppiumServer" })
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
		File app = new File(appDir, "tianqi.apk");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cn.dbyl.young.tianqi");
		// // support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		// no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY,
				"cn.dbyl.young.tianqi.activity.MainActivity");
		// capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
		// ".MainActivity");

		driver = new AndroidDriver<MobileElement>(url, capabilities);

	}

	@Test(groups = { "tianQi" })
	public void TianQi() throws InterruptedException {
		String version = (String) driver.getCapabilities().getCapability(MobileCapabilityType.PLATFORM_VERSION);
		MobileElement tab = driver.findElementById("cn.dbyl.young.tianqi:id/tab_personal");
		tab.click();
		MobileElement tv1 = driver.findElementByAccessibilityId("TextView 1_AppCompatTextView");
		// Wait<AndroidDriver<MobileElement>> wait = new
		// FluentWait<AndroidDriver<MobileElement>>(driver)
		// .withTimeout(60, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
		// .ignoring(NoSuchElementException.class);
		// By by = new By.ById("");
		// Boolean e = wait.until(new ExpectedCondition<Boolean>() {
		//
		// @Override
		// public Boolean apply(WebDriver input) {
		// // TODO Auto-generated method stub
		// return null;
		// }
		// });

		Assert.assertEquals(tv1.getText(), "TextView 1");
		tv1.click();
		
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
//		AppiumServerUtils.getInstance().stopServer();
	}

}