package main.java.com.dbyl.appiumCore.tests;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumCore.page.AppDemoPage;
import main.java.com.dbyl.appiumCore.utils.BaseTest;
import main.java.com.dbyl.appiumCore.utils.CaseId;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @since 2015-6
 * @author Young
 */
public class ZhihuTest extends BaseTest {
	public static AndroidDriver<MobileElement> driver;
	public URL url;

	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() throws MalformedURLException {
		url = AppiumServerUtils.getInstance().startAppiumServerByDefault();
		// url= new URL("http://127.0.0.1:4444/wd/hub");

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
		// if no need install don't add this

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "zhihu.apk");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cn.dbyl.appiumdemo");
		// // support Chinese
		capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, "True");
		capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, "True");
		// no need sign
		capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, "True");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");

		driver = new AndroidDriver<MobileElement>(url, capabilities);

	}

	@Test(groups = { "webView" })
	@CaseId(id = "1234675")
	public void DemoTest() throws InterruptedException {
		String version = (String) driver.getCapabilities().getCapability(MobileCapabilityType.PLATFORM_VERSION);
		System.out.println(version);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		AppDemoPage appdemo = new AppDemoPage(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), appdemo);
		Assert.assertEquals(appdemo.getText(), "appiumDemo");
		appdemo.clickButton();
		Thread.sleep(5000);
		Assert.assertEquals(appdemo.getText(), "You just click the button");

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		AppiumServerUtils.getInstance().stopServer();
	}

}