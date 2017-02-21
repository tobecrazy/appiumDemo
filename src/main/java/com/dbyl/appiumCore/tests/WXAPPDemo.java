package main.java.com.dbyl.appiumCore.tests;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @since 2015-6
 * @author Young
 */
public class WXAPPDemo {
	public static AndroidDriver<MobileElement> driver;
	public URL url;

	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() {
		url = AppiumServerUtils.getInstance().startAppiumServerByDefault();

	}

	@BeforeClass(alwaysRun = true, dependsOnMethods = { "startAppiumServer" })
	public void setUp() throws Exception {
		// set up appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.tencent.mm");
		// // support Chinese
		capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, "True");
		capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, "True");
		// no need sign
		capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, "True");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ui.LauncherUI");

		driver = new AndroidDriver<MobileElement>(url, capabilities);

	}

	@Test(groups = { "WXAPP" })
	public void DemoTest() throws InterruptedException {
		String version = (String) driver.getCapabilities().getCapability(MobileCapabilityType.PLATFORM_VERSION);
		System.out.println(version);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		AppiumServerUtils.getInstance().stopServer();
	}

}