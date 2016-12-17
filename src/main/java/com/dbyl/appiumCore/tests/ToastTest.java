package main.java.com.dbyl.appiumCore.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import main.java.com.dbyl.appiumServer.AppLogger;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

public class ToastTest {
	AndroidDriver<MobileElement> driver;
	String keyword = "appium";
	URL url;
	AppLogger logger = new AppLogger(ToastTest.class);

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
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
		// if no need install don't add this
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "webview.apk");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.testerhome.webview");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		// support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		// no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
		logger.info("connet to Appium server  " + url.toString());
		driver = new AndroidDriver<MobileElement>(url, capabilities);

	}

	@Test(groups = { "Toast" })
	public void webViewTest() throws InterruptedException {
		final WebDriverWait wait = new WebDriverWait(driver, 10);
	    driver.startActivity("com.testerhome.webview", ".ToastActivity");
		MobileElement toastButton = driver.findElementById("com.testerhome.webview:id/toast");
		toastButton.click();
		Assert.assertNotNull(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(@text,'Toast Test')]"))));
 
	}

 
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		logger.info("quit");
		driver.quit();
		AppiumServerUtils.getInstance().stopServer();
	}
}