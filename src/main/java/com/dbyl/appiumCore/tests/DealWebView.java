package main.java.com.dbyl.appiumCore.tests;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author young
 *
 */
public class DealWebView {
	private AndroidDriver<?> driver;
	private boolean isInstall = false;
	private String userName="youremail";
	private String password="yourpassword";
 

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
		capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.2");
		// if no need install don't add this
		if (isInstall) {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "zhihu.apk");
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		}
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.zhihu.android");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2");
		// support Chinese
		capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, "True");
		capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD , "True");
		// no need sign
		capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, "True");
		//capabilities.setCapability("autoWebview", "True");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ui.activity.GuideActivity");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public void loginWithMicroBlog() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// swipe to right
		driver.findElementById("com.zhihu.android:id/login_and_register")
				.click();
		driver.findElementById("com.zhihu.android:id/btn_social").click();
		driver.findElementById("com.zhihu.android:id/login_weibo").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(15000);
		Set<String> context = driver.getContextHandles();
		for (String contextName : context) {
			System.out.println(contextName);

		}
//		driver.context("WEBVIEW");
		System.out.println(driver.getPageSource());
		driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys(
				userName);
		driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys(
				password);
		driver.findElementByXPath("//android.view.View[contains(@content-desc,'test')]").click();
	}


	/**
	 * This Method create for take screenshot
	 * 
	 * @author Young
	 * @param drivername
	 * @param filename
	 */
	public static void snapshot(TakesScreenshot drivername, String filename) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name

		String currentPath = System.getProperty("user.dir"); // get current work
																// folder
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		try {
			System.out.println("save snapshot path is:" + currentPath + "/"
					+ filename);
			FileUtils
					.copyFile(scrFile, new File(currentPath + "\\" + filename));
		} catch (IOException e) {
			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} finally {
			System.out.println("screen shot finished, it's in " + currentPath
					+ " folder");
		}
	}	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}
}