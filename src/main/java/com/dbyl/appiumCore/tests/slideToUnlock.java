package main.java.com.dbyl.appiumCore.tests;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class slideToUnlock {
	private MobileDriver driver;

	/**
	 * 
	 * @author Young
	 * @throws IOException
	 */
	// @BeforeClass(alwaysRun = true)
	// public void startRecord() throws IOException {
	//
	// Process p = Runtime
	// .getRuntime()
	// .exec("cmd.exe /C \"C:\\Program Files (x86)\\Appium\\node_modules\\.bin\\appium.cmd\"");
	// final InputStream is1 = p.getInputStream();
	// new Thread(new Runnable() {
	// public void run() {
	// BufferedReader br = new BufferedReader(new InputStreamReader(
	// is1));
	// try {
	// while (br.readLine() != null)
	// ;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// }).start(); // �����������߳������p.getInputStream()�Ļ�����
	// InputStream is2 = p.getErrorStream();
	// BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
	// StringBuilder buf = new StringBuilder(); // ������������
	// String line = null;
	// while ((line = br2.readLine()) != null)
	// buf.append(line); //
	// System.out.println("������Ϊ��" + buf);
	// // Runtime rt = Runtime.getRuntime();
	// // // this code for record the screen of your device
	// //
	// rt.exec("cmd.exe /C \"C:\\Program Files (x86)\\Appium\\node_modules\\.bin\\appium.cmd\"");
	// // rt.exec("cmd.exe /C adb shell screenrecord /sdcard/runCase.mp4");
	//
	// }

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "5.1");
		// if no need install don't add this
		capabilities.setCapability("appPackage", "cmb.pb");
		// no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability("appActivity", ".ui.PBInitActivity");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), capabilities);

	}

	@Test(groups = "swipeTest", priority = 1)
	public void swipeTest() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// swipe to right
		System.out.println(driver.getPageSource());
		// swipeToRight(driver, 2000);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("cmb.pb:id/item_funcIcon")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		List<WebElement> pic = driver
				.findElements(By
						.xpath("//android.widget.FrameLayout/android.widget.ImageView"));
		for (int i = 0; i < pic.size(); i++) {
			System.out.println(pic.size());
			pic.get(i).click();
		}

		final TouchAction touchAction = new TouchAction(driver);
		touchAction
				.press(pic.get(0).getLocation().getX(),
						pic.get(0).getLocation().getY())
				.waitAction(1500)
				.moveTo(pic.get(1).getLocation().getX()
						- pic.get(0).getLocation().getX(),
						pic.get(1).getLocation().getY()
								- pic.get(0).getLocation().getY())
				.moveTo(pic.get(2).getLocation().getX()
						- pic.get(1).getLocation().getX(),
						pic.get(2).getLocation().getY()
								- pic.get(1).getLocation().getY())
				.moveTo(pic.get(4).getLocation().getX()
						- pic.get(2).getLocation().getX(),
						pic.get(4).getLocation().getY()
								- pic.get(2).getLocation().getY())
				.moveTo(pic.get(6).getLocation().getX()
						- pic.get(4).getLocation().getX(),
						pic.get(6).getLocation().getY()
								- pic.get(4).getLocation().getY())
				.moveTo(pic.get(7).getLocation().getX()
						- pic.get(6).getLocation().getX(),
						pic.get(7).getLocation().getY()
								- pic.get(6).getLocation().getY())
				.moveTo(pic.get(8).getLocation().getX()
						- pic.get(7).getLocation().getX(),
						pic.get(8).getLocation().getY()
								- pic.get(7).getLocation().getY()).release();
		touchAction.perform();
		/*
		 * final TouchAction touchAction = new TouchAction(driver);
		 * touchAction.press(pic.get(0)).waitAction(1500).moveTo(pic.get(1))
		 * .moveTo(pic.get(2)).moveTo(pic.get(4)).moveTo(pic.get(6))
		 * .moveTo(pic.get(7)).moveTo(pic.get(8)).release();
		 * touchAction.perform();
		 */
		String username = driver.findElement(By.id("cmb.pb:id/gTvMenuTitle"))
				.getText();
		System.out.println(username);
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

	public void swipeToRight(MobileDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
//		driver.swipe(width / 4, height / 2, width * 5 / 6, height / 2, during);
		// wait for page loading
	}

	@AfterClass(alwaysRun = true)
	public void stopAppiumServer() {

	}
}