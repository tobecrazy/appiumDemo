package main.java.com.dbyl.appiumCore.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import main.java.com.dbyl.appiumServer.AppLogger;

public class AppWebView {
	private AndroidDriver<MobileElement> driver;
	private String keyword = "appium";
	AppLogger logger= new AppLogger(AppWebView.class);

	@BeforeClass
	public void startAppiumServer() throws IOException, InterruptedException {
		// AppiumServerUtils.startServer("127.0.0.1", 4723);
		Thread.sleep(1000);
		logger.debug("sleep 1 s");
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
		// support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		// no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
		logger.info("connet to Appium server :127.0.0.1:4723");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
	}

	@Test(groups = { "webView" })
	public void webViewTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Set<String> contexts = driver.getContextHandles();
		for (String context : contexts) {
			// 输出context
			logger.info(context);
			if(context.contains("WEBVIEW"))
			{
				logger.info("swich_to_content "+context);
				driver.context(context);
			}
		}
		Thread.sleep(5000);
		logger.info(driver.getContext());
	 
		driver.findElementByXPath("//input[@id='index-kw']").sendKeys(keyword);
		driver.findElement(By.xpath("//button[@id='index-bn']")).click();

		// 返回native app context
		driver.context("NATIVE_APP");
		MobileElement title = driver.findElementById("android:id/action_bar_title");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assert.assertTrue(title.getText().contains(keyword));
		
		MobileElement back=driver.findElementById("com.testerhome.webview:id/action_back");
		back.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assert.assertTrue(title.getText().equals("百度一下"));

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		logger.info("quit");
		driver.quit();
	}
}