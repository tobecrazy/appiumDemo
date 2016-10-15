package main.java.com.dbyl.appiumCore.tests;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.io.File;

import java.net.URL;

import java.util.concurrent.TimeUnit;

public class iOSTest {
	private IOSDriver<MobileElement> driver;
	private boolean isInstall = true;
	private static AppiumDriverLocalService service;
	private static String URL;

	@BeforeClass
	public static void beforeClass() throws Exception {
		// service = AppiumDriverLocalService.buildDefaultService();
		// service.start();
		URL ="http://localhost:4723/wd/hub";// AppiumServerUtils.startServer("127.0.0.1", 4723);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "10.0");
		capabilities.setCapability("deviceName", "iPhone 7 Plus");
		// if no need install don't add this
		if (isInstall) {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "TestApp.app");
			System.out.println("---->" + app.getAbsolutePath());
			capabilities.setCapability("app", app.getAbsolutePath());
		}

		// support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");

		driver = new IOSDriver<MobileElement>(new URL(URL), capabilities);

	}

	@Test
	public void calc() {

		// wait for 60s
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// find login userName and password editText
		iOSPageDemo iOPage = new iOSPageDemo(driver);
		iOPage.typeTextField1("12");
		iOPage.typeTextField2("65");
		iOPage.clickCalcButton();

		Assert.assertEquals(iOPage.getResult(), "77");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}

}