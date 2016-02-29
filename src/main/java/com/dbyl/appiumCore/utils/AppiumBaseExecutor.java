package main.java.com.dbyl.appiumCore.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * This class is for executor appium command
 * 
 * @see IOSDriver and Android Driver
 * @author Young
 */
public class AppiumBaseExecutor implements AppiumAPI {
	private AppiumDriver<?> driver;

	public AppiumBaseExecutor(AppiumDriver<?> driver) {
		this.driver = driver;
	}

	/**
	 * This Method is for type
	 * 
	 * @author Young
	 * @param locator
	 * @param message
	 */
	public void type(Locator locator, String message) {
		MobileElement e = (MobileElement) findElement(locator);
		e.sendKeys(message);

	}

	public void click(Locator locator) {
		MobileElement e = (MobileElement) findElement(locator);
		e.click();

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
	@Override
	public void click(MobileElement e) {
		e.click();		
	}

	@Override
	public void type(MobileElement e, String message) {
		e.sendKeys(message);
		
	}

	@Override
	public String getText(MobileElement e) {
		return e.getText();
	}

}
