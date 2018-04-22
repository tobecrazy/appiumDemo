package main.java.com.dbyl.appiumCore.tests.rubychina;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumCore.pageActions.HomePage;
import main.java.com.dbyl.appiumCore.pageActions.SignInPage;
import main.java.com.dbyl.appiumCore.tests.iOSTest;
import main.java.com.dbyl.appiumCore.utils.BaseTest;
import main.java.com.dbyl.appiumCore.utils.CaseId;
import main.java.com.dbyl.appiumServer.AppLogger;

public class SanityTest extends BaseTest {
	private IOSDriver<MobileElement> driver;
	private boolean isInstall = true;
	private URL url;
	AppLogger logger = new AppLogger(iOSTest.class);

	@BeforeClass
	public void beforeClass() throws Exception {
		// url = new URL("http://localhost:4444/wd/hub");
		url = new URL("http://localhost:4723/wd/hub");
		// url = AppiumServerUtils.getInstance().startServer("127.0.0.1", 4723);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.3");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone SE");
		// if no need install don't add this
		if (isInstall) {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "Ruby China.app");
			System.out.println("---->" + app.getAbsolutePath());
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		}

		// support Chinese
		capabilities.setCapability("resetKeyboard", "True");

		driver = new IOSDriver<MobileElement>(url, capabilities);

	}

	@Test(groups = { "ruby china" })
	@CaseId(id = "ID1234")
	public void verifyLaunchTest() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage = homePage.tapAllowButton();
		homePage.tapNaviMenu();
		SignInPage signInPage = homePage.naviToSignInPage();
		Assert.assertTrue(signInPage.isSignInPagePresent());
		signInPage.typeUserName("UserName");
		signInPage.typePassword("Test");
		signInPage.clickDone();
	}
}
