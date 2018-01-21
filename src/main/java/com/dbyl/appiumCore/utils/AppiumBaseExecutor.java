package main.java.com.dbyl.appiumCore.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * This class is for executor appium command.
 *
 * @author Young
 * @see IOSDriver and Android Driver
 */
public abstract class AppiumBaseExecutor implements AppiumAPI {

	/** The driver. */
	private AppiumDriver<?> driver;

	/**
	 * Instantiates a new appium base executor.
	 *
	 * @param driver
	 *            the driver
	 */
	public AppiumBaseExecutor(AppiumDriver<?> driver) {
		this.driver = driver;
	}

	/**
	 * This Method is for type.
	 *
	 * @author Young
	 * @param locator
	 *            the locator
	 * @param message
	 *            the message
	 */
	public void type(Locator locator, String message) {
		MobileElement e = (MobileElement) findElement(locator);
		e.sendKeys(message);

	}

	/**
	 * Click.
	 *
	 * @param locator
	 *            the locator
	 */
	public void click(Locator locator) {
		MobileElement e = (MobileElement) findElement(locator);
		e.click();

	}

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	public AppiumDriver<?> getDriver() {
		return driver;
	}

	/**
	 * Sets the driver.
	 *
	 * @param driver
	 *            the new driver
	 */
	public void setDriver(AppiumDriver<?> driver) {
		this.driver = driver;
	}

	/**
	 * Find element.
	 *
	 * @param locator
	 *            the locator
	 * @return the mobile element
	 */
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
			e = (MobileElement) driver.findElementByCssSelector(locator.getElement());
			break;
		case className:
			e = (MobileElement) driver.findElementByClassName(locator.getElement());
			break;
		case tagName:
			e = (MobileElement) driver.findElementByTagName(locator.getElement());
			break;
		case linkText:
			e = (MobileElement) driver.findElementByLinkText(locator.getElement());
			break;
		case partialLinkText:
			e = (MobileElement) driver.findElementByPartialLinkText(locator.getElement());
			break;
		case accessibilityId:
			e = (MobileElement) driver.findElementByAccessibilityId(locator.getElement());
		case androidUIAutomator:
			e = (MobileElement) ((AndroidDriver) driver).findElementByAndroidUIAutomator(locator.getElement());
			break;
		case iOSUIAutomation:
			break;
		case by:
			break;
		default:

		}
		return e;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * main.java.com.dbyl.appiumCore.utils.AppiumAPI#click(io.appium.java_client.
	 * MobileElement)
	 */
	@Override
	public void click(MobileElement e) {
		e.click();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * main.java.com.dbyl.appiumCore.utils.AppiumAPI#type(io.appium.java_client.
	 * MobileElement, java.lang.String)
	 */
	@Override
	public void type(MobileElement e, String message) {
		e.sendKeys(message);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * main.java.com.dbyl.appiumCore.utils.AppiumAPI#getText(io.appium.java_client.
	 * MobileElement)
	 */
	@Override
	public String getText(MobileElement e) {
		return e.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.java.com.dbyl.appiumCore.utils.AppiumAPI#scrollToElement(io.appium.
	 * java_client.MobileElement)
	 */
	@Override
	public void scrollToElement(MobileElement e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * main.java.com.dbyl.appiumCore.utils.AppiumAPI#isElementPresent(io.appium.
	 * java_client.MobileElement)
	 */
	@Override
	public boolean isElementPresent(MobileElement e) {
		return e.isDisplayed();
	}

}
