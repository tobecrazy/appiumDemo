package main.java.com.dbyl.appiumCore.tests.sf;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SFTest {
	AppLogger logger = new AppLogger(SFTest.class);
	private AndroidDriver<MobileElement> driver;
	boolean isInstall = true;
	private URL url;

	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() throws IOException, InterruptedException {
		// url = AppiumServerUtils.getInstance().startServer("127.0.0.1", 4723);
		url = new URL("http://127.0.0.1:4723/wd/hub");
		logger.info("get url" + url);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");

		// if no need install don't add this
		if (isInstall) {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "sf.apk");
			System.out.println("---->" + app.getAbsolutePath());
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		}

		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.successfactors.android");
		// support Chinese
		capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, "True");
		capabilities.setCapability("resetKeyboard", "True");
		// no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability("appWaitActivity",
				".common.gui.PasswordActivity,.home.gui.HomeTileActivity,.multiprofile.gui.MultiProfileActivity,.common.gui.activation.CodeActivationActivity,.common.gui.activation.ActivationActivity,.common.gui.LicenseActivity,com.successfactors.android.common.gui.SFActivationActivity,com.successfactors.android.home.gui.SFHomeActivity");

		capabilities.setCapability("intentAction", "android.intent.action.MAIN");
		capabilities.setCapability("intentCategory", "android.intent.category.LAUNCHER");
		capabilities.setCapability("intentFlags", "0x10200000");
		driver = new AndroidDriver<MobileElement>(url, capabilities);
	}

	@Test(groups = { "SFTest" })
	public void SFAPPTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		AppiumServerUtils.getInstance().stopServer();
	}
}