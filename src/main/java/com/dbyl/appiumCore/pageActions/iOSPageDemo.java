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
	@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")
	private MobileElement inputbox1;
	
	/** The inputbox 2. */
	@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[2]")
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
	@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]")
	private MobileElement calcButton;
	
	/** The result. */
	@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]")
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
	 * @param e the e
	 * @param msg the msg
	 */
	public void typeInputBox(MobileElement e, String msg) {
		if (e.isDisplayed()) {
			System.out.print("eeeeeeeeeeeeeeee");
			e.click();

		} else {
			System.out.print("nnnnnnnnnnnnnnnnn");
		}
		e.setValue(msg);
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
