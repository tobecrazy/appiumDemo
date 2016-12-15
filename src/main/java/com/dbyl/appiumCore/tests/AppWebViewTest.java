package main.java.com.dbyl.appiumCore.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

public class AppWebViewTest {
	AndroidDriver<MobileElement> driver;
	String keyword = "appium";
	URL url;
	AppLogger logger = new AppLogger(AppWebViewTest.class);

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

	@Test(groups = { "webView" })
	public void webViewTest() throws InterruptedException {
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		MobileElement backButton = driver.findElementById("com.testerhome.webview:id/action_back");
		backButton.click();
		Assert.assertNotNull(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(@text,'You click on back forward')]"))));

		MobileElement clickToPresent = driver.findElementById("com.testerhome.webview:id/action_present");
		clickToPresent.click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		MobileElement imageButton = driver.findElementById("com.testerhome.webview:id/button");
		Assert.assertTrue(imageButton.isDisplayed());
		Set<String> contexts = driver.getContextHandles();
		for (String context : contexts) {
			// 输出context
			logger.info(context);
			if (context.contains("WEBVIEW")) {
				logger.info("swich_to_content " + context);
				driver.context(context);
			}
		}
		logger.info(driver.getContext());
		// 隐式等待，针对全局设置
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// pageLoadTimeout只能用于webview 或者 pure web
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// 这段代码返回浏览器加载页面状态，complete为加载完成
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String status = (String) jse.executeScript("var status=document.readyState;return status");
		Assert.assertTrue(status.contains("complete"));
	 

		WebElement input = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@id='index-kw']"));
			}
		});
		input.sendKeys(keyword);
		By by = By.xpath("//button[@id='index-bn']");
		if (isElementPresent(by)) {
			driver.findElement(by).click();
		}

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.navigate().refresh();
		// 返回native app context
		driver.context("NATIVE_APP");
		MobileElement title = driver.findElementById("android:id/action_bar_title");
		Assert.assertTrue(title.getText().contains(keyword));
		MobileElement back = driver.findElementById("com.testerhome.webview:id/action_back");
		back.click();
		Assert.assertTrue(title.getText().equals("百度一下"));

	}

	/**
	 * @author young
	 * @param by
	 * @return
	 */
	private boolean isElementPresent(By by) {
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		logger.info("quit");
		driver.quit();
		AppiumServerUtils.getInstance().stopServer();
	}
}