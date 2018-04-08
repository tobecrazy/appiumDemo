package main.java.com.dbyl.appiumCore.pageActions;

/**
 * @author Young
 */
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

/**
 * The Class iOSPageDemo.
 */
public class iOSPageDemo {
	
	/** The driver. */
	IOSDriver<?> driver;

	/** The inputbox 1. */
	@iOSFindBy(accessibility = "IntegerA")
	private MobileElement inputbox1;
	
	/** The inputbox 2. */
	@iOSFindBy(xpath = "//XCUIElementTypeTextField[@name=\"IntegerB\"]")
	private MobileElement inputbox2;

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	public IOSDriver<?> getDriver() {
		return driver;
	}

	/**
	 * Sets the driver.
	 *
	 * @param driver the new driver
	 */
	public void setDriver(IOSDriver<?> driver) {
		this.driver = driver;
	}

	/** The calc button. */
	@iOSFindBy(id = "ComputeSumButton")
	private MobileElement calcButton;
	
	/** The result. */
	@iOSFindBy(id= "Answer")
	private MobileElement result;

	/**
	 * Instantiates a new i OS page demo.
	 *
	 * @param driver the driver
	 */
	public iOSPageDemo(AppiumDriver<?> driver) {
		setDriver((IOSDriver<?>) driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Type input box.
	 *
	 * @param element the e
	 * @param msg the msg
	 */
	public void typeInputBox(MobileElement element, String msg) {
		if (element.isDisplayed()) {
			System.out.print("eeeeeeeeeeeeeeee");
			element.click();

		} else {
			System.out.print("nnnnnnnnnnnnnnnnn");
		}
		element.setValue(msg);
	}

	/**
	 * Type text field 1.
	 *
	 * @param msg the msg
	 */
	public void typeTextField1(String msg) {
		typeInputBox(inputbox1, msg);
	}

	/**
	 * Type text field 2.
	 *
	 * @param msg the msg
	 */
	public void typeTextField2(String msg) {
		typeInputBox(inputbox2, msg);
	}

	/**
	 * Click calc button.
	 */
	public void clickCalcButton() {
		calcButton.click();
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() {
		return result.getAttribute("value");
	}
}
