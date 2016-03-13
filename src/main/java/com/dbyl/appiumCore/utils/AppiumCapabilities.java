package main.java.com.dbyl.appiumCore.utils;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumCapabilities {

	private DesiredCapabilities AndroidCapabilities = new DesiredCapabilities();
	private DesiredCapabilities IOSCapabilities = new DesiredCapabilities();

	public DesiredCapabilities getAndroidCapabilities() {
		return AndroidCapabilities;
	}

	/**
	 * Load from properties
	 * 
	 * @param androidCapabilities
	 */
	public void setAndroidCapabilities(DesiredCapabilities androidCapabilities) {
		AndroidCapabilities = androidCapabilities;
		AndroidCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
				AutomationName.APPIUM);
		AndroidCapabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		AndroidCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
				"Android");
		AndroidCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
				"Nexus 7");
		AndroidCapabilities.setCapability(
				MobileCapabilityType.PLATFORM_VERSION, "5.1");
		boolean isInstall = false;
		// if no need install don't add this
		if (isInstall) {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "zhihu.apk");
			AndroidCapabilities.setCapability(MobileCapabilityType.APP,
					app.getAbsolutePath());
		}
		AndroidCapabilities.setCapability(
				AndroidMobileCapabilityType.APP_PACKAGE, "com.zhihu.android");
		// support Chinese
		AndroidCapabilities.setCapability(
				AndroidMobileCapabilityType.UNICODE_KEYBOARD, "True");
		AndroidCapabilities.setCapability(
				AndroidMobileCapabilityType.RESET_KEYBOARD, "True");
		// no need sign
		AndroidCapabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN,
				"True");
		AndroidCapabilities.setCapability(
				AndroidMobileCapabilityType.APP_ACTIVITY,
				".app.ui.activity.MainActivity");
	}

	public DesiredCapabilities getIOSCapabilities() {
		return IOSCapabilities;
	}

	public void setIOSCapabilities(DesiredCapabilities iOSCapabilities) {
		IOSCapabilities = iOSCapabilities;
		IOSCapabilities.setCapability(MobileCapabilityType.PLATFORM, "iOS");
		IOSCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
				"9.2");
		IOSCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
				"iPhone 6");
		boolean isInstall = false;
		// if no need install don't add this
		if (isInstall) {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "TestApp.app");
			System.out.println("---->" + app.getAbsolutePath());
			IOSCapabilities.setCapability("app", app.getAbsolutePath());
		}

		// support Chinese
		IOSCapabilities.setCapability("unicodeKeyboard", "True");
		IOSCapabilities.setCapability("resetKeyboard", "True");
	}

}
