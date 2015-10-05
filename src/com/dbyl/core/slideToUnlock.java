package com.dbyl.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class slideToUnlock {
	private AndroidDriver<WebElement> driver;

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
		capabilities.setCapability("platformVersion", "5.1");
		// if no need install don't add this
		capabilities.setCapability("appPackage",
				"mobi.w3studio.apps.android.shsmy.phone");
		// no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability("appActivity", ".ui.WelcomeActivity");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), capabilities);
		startRecord();
	}

	@Test(groups = "swipeTest", priority = 1)
	public void swipeTest() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// swipe to right
//		this.swipeToRight(driver, 2500);
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		final TouchAction touchAction = new TouchAction(driver);
		// appium converts press-wait-move to-release to a swipe action
		touchAction.press(width / 4, height / 2).waitAction(2500)
				.moveTo(width * 3 / 4, height / 2).release().perform();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

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

	public void swipeToRight(AndroidDriver<?> driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, during);
		// wait for page loading
	}
}