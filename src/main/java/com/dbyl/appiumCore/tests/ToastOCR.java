package main.java.com.dbyl.appiumCore.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumCore.utils.ExecutorUtils;
import main.java.com.dbyl.appiumServer.AppLogger;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

public class ToastOCR {
	AndroidDriver<MobileElement> driver;
	String keyword = "appium";
	String fileName = "toast.png";
	URL url;
	String currentPath = System.getProperty("user.dir");
	AppLogger logger = new AppLogger(ToastOCR.class);

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
		File app = new File(appDir, "toast.apk");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cn.dbyl.young.tianqi");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		// support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		// no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activity.StartActivity");
		logger.info("connet to Appium server  " + url.toString());
		driver = new AndroidDriver<MobileElement>(url, capabilities);

	}

	@Test(groups = { "Toast" })
	public void webViewTest() throws Exception {
		Activity activity = new Activity("cn.dbyl.young.tianqi", ".activity.MainActivity");
		driver.startActivity(activity);
		MobileElement cartButton = driver.findElementById("cn.dbyl.young.tianqi:id/tab_shopping_cart");
		cartButton.click();

		MobileElement toastButton = driver.findElementById("cn.dbyl.young.tianqi:id/add_cart");
		toastButton.click();
		toastButton.click();
		snapshot((TakesScreenshot) driver, fileName);
		Thread.sleep(5000);
		String txt = pngToTxt(fileName, "toast");
		logger.info(txt);
	}

	/**
	 * @author young
	 * @param drivername
	 * @param filename
	 */
	public void snapshot(TakesScreenshot drivername, String filename) {
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		logger.info("save snapshot path is:" + scrFile);
		try {
			logger.info("save snapshot path is:" + currentPath + "/screenshot/" + filename);
			FileUtils.copyFile(scrFile, new File(currentPath + "/screenshot/" + filename));
		} catch (IOException e) {
			logger.info("Can't save screenshot");
			e.printStackTrace();
		} finally {
			logger.info("screen shot finished, it's in " + currentPath + "/screenshot/" + " folder");
		}
	}
	

	/**
	 * @author young
	 * @param input
	 * @param output
	 * @return
	 * @throws Exception
	 */
	public String pngToTxt(String input, String output) throws Exception {

		String pngToTXtCommand = "/usr/local/bin/tesseract " + currentPath + "/screenshot/" + input + " " + output;
		String out = ExecutorUtils.runWithoutWatchDog(pngToTXtCommand);
		logger.info(out);
		return readTXT(currentPath + "/screenshot/" + output + ".txt");
	}
	/**
	 * @author young
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String readTXT(String path) throws Exception {
		logger.info(path);
		File file = new File(path);
		InputStreamReader reader = null;
		StringBuffer sb = new StringBuffer();
		BufferedReader bf = null;
		String encoding = "GBK";
		if (file.isFile() && file.exists()) {

			try {
				reader = new InputStreamReader(new FileInputStream(file), encoding);
				bf = new BufferedReader(reader);
				String textLine = null;
				while ((textLine = bf.readLine()) != null) {
					sb.append(textLine + "\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				bf.close();
				reader.close();
			}
		} else {
			throw new Exception("No Such File " + path);
		}

		return sb.toString();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		logger.info("quit");
		driver.quit();
		AppiumServerUtils.getInstance().stopServer();
	}

	

}