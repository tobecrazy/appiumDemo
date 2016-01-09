package main.java.com.dbyl.appiumCore;


/**
 * @author Young
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOSPageDemo {
	IOSDriver<?> driver;

	@iOSFindBy(name = "TextField1")
	private WebElement inputbox1;
	@iOSFindBy(name = "TextField2")
	private WebElement inputbox2;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[1]")
	private WebElement calcButton;
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAStaticText[1]")
	private WebElement result;

	iOSPageDemo(IOSDriver<?> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void typeInputBox(WebElement e, String msg) {
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
