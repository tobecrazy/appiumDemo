package main.java.com.dbyl.appiumCore.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

// TODO: Auto-generated Javadoc
/**
 * The Class BaiduPage.
 */
public class BaiduPage {
	
	/** The driver. */
	AppiumDriver<?> driver;
	
	/** The input box. */
	@FindBy(xpath = "//input[@id='index-kw']")
	MobileElement inputBox;
	
	/** The search button. */
	@FindBy(id = "index-bn")
	MobileElement searchButton;

	/**
	 * Instantiates a new baidu page.
	 *
	 * @param driver the driver
	 */
	public BaiduPage(AppiumDriver<?> driver) {
		super();
		this.driver = driver;
		if (driver instanceof AndroidDriver) {
			System.out.println("Android driver");
		} else if (driver instanceof IOSDriver) {
			System.out.println("IOS driver");
		}

	}

	/**
	 * Open.
	 *
	 * @author young
	 * @param url the url
	 */
	public void open(String url) {
		if (driver instanceof AndroidDriver) {
			System.out.println("Android driver");
		} else if (driver instanceof IOSDriver) {
			System.out.println("IOS driver");
		}
		driver.get(url);
	}

	/**
	 * Type input box.
	 *
	 * @author young
	 * @param keyword the keyword
	 */
	public void typeInputBox(String keyword) {
		inputBox.sendKeys(keyword);
	}

	/**
	 * Click on search button.
	 *
	 * @author young
	 */
	public void clickOnSearchButton() {
		searchButton.submit();
	}

	/**
	 * Wait for page load.
	 *
	 * @author young
	 * @param timeout the timeout
	 */
	public void waitForPageLoad(int timeout) {
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
	}

	/**
	 * Gets the title.
	 *
	 * @author young
	 * @return the title
	 */
	public String getTitle() {
		return driver.getTitle();
	}

}
