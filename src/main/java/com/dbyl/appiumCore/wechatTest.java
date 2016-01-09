package main.java.com.dbyl.appiumCore;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class wechatTest {
	private AndroidDriver driver;
	private boolean isInstall = true;

	/**
	 * @author Young
	 * @throws IOException
	 */
	public void startRecord() throws IOException {
		Runtime rt = Runtime.getRuntime();
		// this code for record the screen of your device
		rt.exec("cmd.exe /C adb shell screenrecord /sdcard/runCase.mp4");

	}

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium

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
		capabilities.setCapability("appActivity", ".ui.activity.GuideActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		startRecord();
	}

	public void login() {

		WebElement loginButton;
		if (isLoginPresent(driver, 60)) {
			loginButton = driver.findElement(By
					.id("com.zhihu.android:id/login_and_register"));
			loginButton.click();
		}

		else {
			Assert.assertTrue(false);
		}

		// wait for 20s
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// find login userName and password editText
	    WebElement userNameInputbox =driver.findElementById("com.zhihu.android:id/email_or_phone");
	    userNameInputbox.sendKeys("seleniumcookies@126.com");
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    
	    WebElement passwordInputBox=driver.findElementById("com.zhihu.android:id/password");
	    passwordInputBox.sendKeys("cookies123");
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    WebElement confirmButton=driver.findElementById("com.zhihu.android:id/btn_confirm");
		if(confirmButton.isEnabled())
		{
			confirmButton.click();
		}
		else
		{
			Assert.assertTrue(false,"Login failed");
		}

		// find ok button byName
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		// find keyword 首页 and verify it is display
		Assert.assertTrue(driver.findElement(By.name("首页")).isDisplayed());

	}

	public boolean isLoginPresent(AndroidDriver driver, int timeout) {
		boolean isPresent = new AndroidDriverWait(driver, timeout).until(
				new ExpectedCondition<WebElement>() {
					public WebElement apply(AndroidDriver d) {
						return d.findElement(By
								.id("com.zhihu.android:id/login_and_register"));
					}

				}).isDisplayed();
		return isPresent;
	}

	@Test(groups = "swipeTest", priority = 1)
	public void swipe() {

		// find login button
		if (isInstall) {
			login();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// find keyword 首页 and verify it is display
		Assert.assertTrue(driver.findElement(By.name("首页")).isDisplayed());
		snapshot((TakesScreenshot) driver, "zhihu_before_swipe.png");
		swipeToUp(driver, 500);
		snapshot((TakesScreenshot) driver, "zhihu_after_swipe.png");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		swipeToDown(driver, 1000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		List<WebElement> titles = driver
				.findElementsById("com.zhihu.android:id/title");
		titles.get(0).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		//swipe to right
		swipeToRight(driver, 100);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// find keyword 首页 and verify it is display
		Assert.assertTrue(driver.findElement(By.name("首页")).isDisplayed());
	}

	/**
	 * This Method for swipe up
	 * 
	 * @author Young
	 * @param driver
	 * @param during
	 */
	public void swipeToUp(AndroidDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, during);
		// wait for page loading
	}

	/**
	 * This Method for swipe down
	 * 
	 * @author Young
	 * @param driver
	 * @param during
	 */
	public void swipeToDown(AndroidDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, during);
		// wait for page loading
	}

	/**
	 * This Method for swipe Left
	 * 
	 * @author Young
	 * @param driver
	 * @param during
	 */
	public void swipeToLeft(AndroidDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, during);
		// wait for page loading
	}

	/**
	 * This Method for swipe Right
	 * 
	 * @author Young
	 * @param driver
	 * @param during
	 */
	public void swipeToRight(AndroidDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, during);
		// wait for page loading
	}

	@Test(groups = { "profileSetting" }, priority = 2)
	public void profileSetting() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// find keyword 首页 and verify it is display
		Assert.assertTrue(driver.findElement(By.name("首页")).isDisplayed());

		driver.swipe(100, 400, 100, 200, 500);
		WebElement myButton = driver.findElement(By
				.className("android.widget.ImageButton"));
		myButton.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.swipe(700, 500, 100, 500, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		List<WebElement> textViews = driver
				.findElementsByClassName("android.widget.TextView");
		textViews.get(0).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElementById("com.zhihu.android:id/name").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// wait for 60s if WebElemnt show up less than 60s , then return , until
		// 60s

		By by = new By.ById("com.zhihu.android:id/showcase_close");

		snapshot((TakesScreenshot) driver, "zhihu_showClose.png");
		if (isElementPresent(by, 30)) {
			driver.findElement(by).click();
		}


		driver.findElementById("com.zhihu.android:id/menu_people_edit").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement intro = driver
				.findElementById("com.zhihu.android:id/introduction");
		intro.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement content = driver
				.findElementById("com.zhihu.android:id/content");
		String text = content.getAttribute("text");
		content.click();
	
		content.sendKeys("Appium Test. Create By Young");

		driver.findElementById("com.zhihu.android:id/menu_question_done")
				.click();

		WebElement explanation = driver
				.findElementById("com.zhihu.android:id/explanation");
		explanation.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		content = driver.findElementById("com.zhihu.android:id/content");
		text = content.getAttribute("text");
		content.click();
	
		content.sendKeys("Appium Test. Create By Young. This is an appium type hahahahah");

		driver.findElementById("com.zhihu.android:id/menu_question_done")
				.click();
		snapshot((TakesScreenshot) driver, "zhihu.png");

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
	 * 
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