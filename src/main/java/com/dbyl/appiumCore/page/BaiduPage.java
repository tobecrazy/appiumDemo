package main.java.com.dbyl.appiumCore.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class BaiduPage {
	AppiumDriver<?> driver;
	@FindBy(id = "index-kw")
	MobileElement inputBox;
	@FindBy(id = "index-bn")
	MobileElement searchButton;

	public BaiduPage(AppiumDriver<?> driver) {
		super();
		this.driver = driver;
		if (driver instanceof AndroidDriver) {
			System.out.println("Android driver");
		} else if (driver instanceof IOSDriver) {
			System.out.println("IOS driver");
		}
		driver.get("http://www.baidu.com");
	}

	/**
	 * @author young
	 * @param keyword
	 */
	public void typeInputBox(String keyword) {
		inputBox.sendKeys(keyword);
	}

	/**
	 * @author young
	 */
	public void clickOnSearchButton() {
		searchButton.submit();
	}

	/**
	 * @author young
	 * @param timeout
	 */
	public void waitForPageLoad(int timeout) {
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
	}

}
