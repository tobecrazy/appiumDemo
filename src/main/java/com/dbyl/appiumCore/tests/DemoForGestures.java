package main.java.com.dbyl.appiumCore.tests;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @since 2016-1
 * @author Young
 */
public class DemoForGestures {
	private URL ServerUrl;
	private AndroidDriver<MobileElement> driver;
	int width;
	int height;

	@BeforeClass
	public void startAppiumServer() throws IOException, InterruptedException {
		ServerUrl = AppiumServerUtils.getInstance().startServer("127.0.0.1", 4723);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
		// if no need install don't add this

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "map.apk");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.appiumGirls");
		// set no sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".Luohe");
		driver = new AndroidDriver<MobileElement>(ServerUrl, capabilities);

	}

	@Test
	public void GustureLockerTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		width = driver.manage().window().getSize().width;
		height = driver.manage().window().getSize().height;
		Thread.sleep(5000);
		swipeToRight();
		swipeToLeft();
		swipeToUp();
		swipeToDown();
		System.out.println(driver.currentActivity());

	}

	/**
	 * @author young
	 */
	private void swipeToDown() {
		TouchAction swipe = new TouchAction(driver).press(width / 2, height / 2).waitAction(Duration.ofSeconds(2))
				.moveTo(0, height / 4).waitAction(Duration.ofSeconds(2)).release();
		swipe.perform();

	}

	/**
	 * @author young
	 */
	private void swipeToUp() {
		TouchAction swipe = new TouchAction(driver).press(width / 2, height / 2).waitAction(Duration.ofSeconds(2))
				.moveTo(0, -height / 4).waitAction(Duration.ofSeconds(2)).release();
		swipe.perform();

	}

	/**
	 * @author young
	 */
	private void swipeToLeft() {
		TouchAction swipe = new TouchAction(driver).press(width / 2, height / 2).waitAction(Duration.ofSeconds(2))
				.moveTo(-width / 4, 0).waitAction(Duration.ofSeconds(2)).release();
		swipe.perform();

	}

	/**
	 * @author young
	 */
	private void swipeToRight() {
		TouchAction swipe = new TouchAction(driver).press(width / 2, height / 2).waitAction(Duration.ofSeconds(2))
				.moveTo(width / 4, 0).waitAction(Duration.ofSeconds(2)).release();
		swipe.perform();

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		AppiumServerUtils.getInstance().stopServer();
	}

}