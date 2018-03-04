package main.java.com.dbyl.appiumCore.tests;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
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
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @since 2017-02-22
 * @author Young
 */

public class CheckboxTest {
	private AndroidDriver<MobileElement> driver;
	URL url;
	AppLogger logger = new AppLogger(PickerTest.class);

	@BeforeClass
	public void startAppiumServer() throws IOException, InterruptedException {
		url = AppiumServerUtils.getInstance().startServer("127.0.0.1", 4723);
		logger.debug("start Appium Server");
	}

	@Test
	public void Demo() throws Exception {
		// set up appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		// for native app set null, for web test please set chrome or firefox
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");

		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		// simulator version 6.0
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
		// if no need install don't add this
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "checkbox.apk");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		// package name
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cn.dbyl.young.checkbox");
		// // support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		// no need sign
		capabilities.setCapability("noSign", "True");
		// launcher activity
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");

		driver = new AndroidDriver<MobileElement>(url, capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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

	}

	/**
	 * @author young
	 * @param element
	 */
	public void check(MobileElement element) {
		if (element.isEnabled() && element.isEnabled()) {
			element.click();
		}
	}

	/**
	 * @author young
	 * @param element
	 */
	public void uncheck(MobileElement element) {
		if (!element.isEnabled() && element.isEnabled()) {
			element.click();
		}
	}
	
	/**
	 * @author young
	 * @param element
	 * @param isChecked
	 */
	public void checkOrUncheckElement(MobileElement element ,boolean isChecked )
	{
		if(isChecked)
		{
			check(element);
			
		}else
		{
			uncheck(element);
		}
	}

}