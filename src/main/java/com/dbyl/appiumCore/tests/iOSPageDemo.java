package main.java.com.dbyl.appiumCore.tests;

import java.util.HashMap;

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

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIATextField[1]")
	private MobileElement inputbox1;
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIATextField[2]")
	private MobileElement inputbox2;

	public IOSDriver<?> getDriver() {
		return driver;
	}

	public void setDriver(IOSDriver<?> driver) {
		this.driver = driver;
	}

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[1]")
	private MobileElement calcButton;
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAStaticText[1]")
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
