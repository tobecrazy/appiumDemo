package main.java.com.dbyl.appiumCore.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumLogger;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.io.File;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class iOSTest   {

	private IOSDriver<MobileElement> driver;
	private boolean isInstall = true;
	private URL url;
	AppiumLogger logger = new AppiumLogger(iOSTest.class);

	@BeforeClass
	public void beforeClass() throws Exception {
		url = AppiumServerUtils.getInstance().startServer("127.0.0.1",4723);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "10.3");
		capabilities.setCapability("deviceName", "iPhone 7 Plus");
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

		driver = new IOSDriver<MobileElement>(url, capabilities);

	}

	@Test(groups = { "webView" })
	public void calc() {

		// wait for 60s
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// find login userName and password editText
		iOSPageDemo iOPage = new iOSPageDemo(driver);
		iOPage.typeTextField1("12");
		iOPage.typeTextField2("65");
		iOPage.clickCalcButton();
		this.takeScreenShot(driver);
		Assert.assertEquals(iOPage.getResult(), "77");
		this.takeScreenShot(driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		AppiumServerUtils.getInstance().stopServer();
	}
	/**
	 * @author young
	 * @param driver
	 */
	public void takeScreenShot(AppiumDriver<?> driver) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String dateStr = sf.format(date);
		logger.info(driver.getTitle() + "\n");
		String path = this.getClass().getSimpleName() + "_" + dateStr + ".png";
		takeScreenShot((TakesScreenshot) driver, path);
	}

	/**
	 * @author Young
	 * @param drivername
	 * @param path
	 */
	public void takeScreenShot(TakesScreenshot drivername, String path) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name
		String currentPath = System.getProperty("user.dir"); // get current work
		logger.info(currentPath);
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		try {
			logger.info("save snapshot path is:" + currentPath + path);
			FileUtils.copyFile(scrFile, new File(currentPath + "\\" + path));
		} catch (Exception e) {
			logger.error("Can't save screenshot");
			e.printStackTrace();
		} finally {
			logger.info("screen shot finished");
		}
	}

}