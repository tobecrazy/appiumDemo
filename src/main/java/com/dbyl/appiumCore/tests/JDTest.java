package main.java.com.dbyl.appiumCore.tests;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumLogger;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JDTest {
	AppiumLogger logger = new AppiumLogger(JDTest.class);
	private AndroidDriver<MobileElement> driver;
	boolean isInstall = true;
	private URL url;

	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() throws IOException, InterruptedException {
		url = AppiumServerUtils.getInstance().startServer("127.0.0.1", 4723);
		logger.info("get url" + url);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");

		// if no need install don't add this
		if (isInstall) {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "Jd.apk");
			System.out.println("---->" + app.getAbsolutePath());
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		}

		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.jingdong.app.mall");
		// support Chinese
		capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, "True");
		capabilities.setCapability("resetKeyboard", "True");
		// no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability("appActivity", ".MainActivity");
		driver = new AndroidDriver<MobileElement>(url, capabilities);
	}

	@Test(groups = { "JDTest" })
	public void JDAPPTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		MobileElement promotionCard = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"优惠券\")");
		 
		// promotionCard.click();

		MobileElement returnToMainPageButton = driver.findElementById("com.jingdong.app.mall:id/afq");

		returnToMainPageButton.click();
		List<MobileElement> bottomElements = driver
				.findElementsByXPath("//android.widget.FrameLayout//android.widget.RadioButton");
		for (MobileElement e : bottomElements) {
			e.click();
		}
		bottomElements.get(4).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		MobileElement loginButton = driver.findElementById("com.jingdong.app.mall:id/dit");
		loginButton.click();
		List<MobileElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
		textFieldsList.get(0).sendKeys("中文测试");
		textFieldsList.get(1).sendKeys("啊啊");
		Assert.assertTrue(StringUtils.equals(textFieldsList.get(0).getAttribute("text"), "中文测试"));

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		AppiumServerUtils.getInstance().stopServer();
	}
}