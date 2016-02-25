package main.java.com.dbyl.appiumCore.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * This class is for executor appium command
 * 
 * @see IOSDriver and Android Driver
 * @author Young
 */
public class AppiumBaseExecutor {
	private AppiumDriver<?> driver;

	public AppiumBaseExecutor(AppiumDriver<?> driver) {
		this.driver = driver;
	}

	public void type(Locator locator, String message) {
		MobileElement e = (MobileElement) findElement(locator);
		e.sendKeys(message);

	}

	public AppiumDriver<?> getDriver() {
		return driver;
	}

	public void setDriver(AppiumDriver<?> driver) {
		this.driver = driver;
	}

	public MobileElement findElement(Locator locator) {
		MobileElement e = null;
		switch (locator.getBy()) {
		case xpath:
			e = (MobileElement) driver.findElementByXPath(locator.getElement());
			break;
		case id:
			e = (MobileElement) driver.findElementById(locator.getElement());
			break;
		case name:
			e = (MobileElement) driver.findElementByName(locator.getElement());
			break;
		case cssSelector:
			e = (MobileElement) driver.findElementByCssSelector(locator
					.getElement());
			break;
		case className:
			e = (MobileElement) driver.findElementByClassName(locator
					.getElement());
			break;
		case tagName:
			e = (MobileElement) driver.findElementByTagName(locator
					.getElement());
			break;
		case linkText:
			e = (MobileElement) driver.findElementByLinkText(locator
					.getElement());
			break;
		case partialLinkText:
			e = (MobileElement) driver.findElementByPartialLinkText(locator
					.getElement());
			break;
		case accessibilityId:
			e = (MobileElement) driver.findElementByAccessibilityId(locator
					.getElement());
		case androidUIAutomator:
			// e =
			// ((AndroidDriver)driver).findElementByAndroidUIAutomator(locator.getElement());
			break;
		case iOSUIAutomation:
			break;
		case by:
			break;
		default:

		}
		return e;

	}

}
