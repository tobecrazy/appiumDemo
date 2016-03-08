package main.java.com.dbyl.appiumCore.tests;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

import java.io.File;

import java.net.URL;

import java.util.concurrent.TimeUnit;

public class iOSTest {
	private IOSDriver<WebElement> driver;
	private boolean isInstall = true;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
		capabilities.setCapability("platformName", "Mac");
		capabilities.setCapability("deviceName", "iPhone 6");
		capabilities.setCapability("platformVersion", "9.2");
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

		driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

	}

	@Test
	public void calc() {

		// wait for 20s
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

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