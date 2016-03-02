package main.java.com.dbyl.appiumCore.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import main.java.com.dbyl.appiumServer.AppiumLogger;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class uploadIcon {
	AppiumLogger logger = new AppiumLogger(JDTest.class);
	private AndroidDriver<MobileElement> driver;
	boolean isInstall = false;
	private String url;

	/**
	 * @author Young
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@BeforeClass(alwaysRun = true)
	public void startRecord() throws IOException, InterruptedException {

		Runtime rt = Runtime.getRuntime();
		// this code for record the screen of your device
		rt.exec("cmd.exe /C adb shell screenrecord /sdcard/runCase.mp4");

	}

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium
		url = AppiumServerUtils.startServer("127.0.0.1", 4723);
		logger.info("get url" + url);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "4.4");
		// if no need install don't add this
		if (isInstall) {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "zhihu.apk");
			capabilities.setCapability("app", app.getAbsolutePath());
		}
		capabilities.setCapability("appPackage", "com.zhihu.android");
		// support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		// no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability("appActivity",
				".app.ui.activity.MainActivity");
		driver = new AndroidDriver<MobileElement>(new URL(url), capabilities);

	}

	public void login() {

		WebElement loginButton;
		if (isLoginPresent(driver, 60)) {
			loginButton = driver.findElement(By
					.id("com.zhihu.android:id/login_btn"));
			loginButton.click();
		}

		else {
			Assert.fail("login failed");
		}

		// wait for 20s
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// find login userName and password editText
		WebElement userNameInputbox = driver
				.findElementById("com.zhihu.android:id/username");
		userNameInputbox.sendKeys("13282774643");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement passwordInputBox = driver
				.findElementById("com.zhihu.android:id/password");
		passwordInputBox.sendKeys("appium123");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement confirmButton = driver
				.findElementById("com.zhihu.android:id/btn_progress");
		if (confirmButton.isEnabled()) {
			confirmButton.click();
		} else {
			Assert.assertTrue(false, "Login failed");
		}

		// find ok button byName
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		// find keyword ��ҳ and verify it is display
		Assert.assertTrue(driver.findElement(By.name("首页")).isDisplayed());

	}

	public boolean isLoginPresent(AndroidDriver driver, int timeout) {
		boolean isPresent = new AndroidDriverWait(driver, timeout).until(
				new ExpectedCondition<WebElement>() {
					public WebElement apply(AndroidDriver d) {
						return d.findElement(By
								.id("com.zhihu.android:id/login_btn"));
					}

				}).isDisplayed();
		return isPresent;
	}



	@Test(groups = { "profileSetting" }, priority = 1)
	public void profileSetting() {
		if (isInstall) {
			login();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// find keyword ��ҳ and verify it is display
		Assert.assertTrue(driver.findElement(By.name("首页")).isDisplayed());
		MobileElement profileButton=driver.findElementByClassName("android.widget.ImageButton");
		profileButton.click();
		
		MobileElement avatar=driver.findElementByAndroidUIAutomator("new UiSelector().resoureid(\"com.zhihu.android:id/avatar\").index(0)");
		driver.tap(1, avatar, 1000);
		MobileElement edit=driver.findElementById("com.zhihu.android:id/action_edit");
		edit.click();
		
		MobileElement changeAvatar=driver.findElementByName("修改头像");//com.zhihu.android:id/btn_revise_header
		changeAvatar.click();
	}

	/**
	 * This method for delete text in textView
	 * 
	 * @author Young
	 * @param text
	 */

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
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

	/**
	 * @param by
	 * @param timeOut
	 * @return
	 */
	public boolean isElementPresent(final By by, int timeOut) {
		try {
			new AndroidDriverWait(driver, timeOut)
					.until(new ExpectedCondition<WebElement>() {
						public WebElement apply(AndroidDriver d) {
							return d.findElement(by);
						}

					});
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}