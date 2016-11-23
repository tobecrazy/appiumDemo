package main.java.com.dbyl.appiumCore.tests;


/**
 * @author Young
 */
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOSPageDemo {
	IOSDriver<?> driver;

	@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")
	private MobileElement inputbox1;
	@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[2]")
	private MobileElement inputbox2;

	public IOSDriver<?> getDriver() {
		return driver;
	}

	public void setDriver(IOSDriver<?> driver) {
		this.driver = driver;
	}

	@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]")
	private MobileElement calcButton;
	@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]")
	private MobileElement result;

	iOSPageDemo(AppiumDriver<?> driver) {
		setDriver((IOSDriver<?>) driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void typeInputBox(MobileElement e, String msg) {
		e.sendKeys(msg);

	}

	public void typeTextField1(String msg) {
		typeInputBox(inputbox1, msg);
	}

	public void typeTextField2(String msg) {
		typeInputBox(inputbox2, msg);
	}

	public void clickCalcButton() {
		calcButton.click();
	}

	public String getResult() {
		return result.getAttribute("value");
	}
}
