package main.java.com.dbyl.appiumCore.pageActions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.java.com.dbyl.appiumCore.page.BaiduPage;

/**
 * The Class BaiduPageAction.
 */
public class BaiduPageAction {
	
	/** The driver. */
	private static AppiumDriver<?> driver;
	
	/** The url. */
	public static String url="http://www.baidu.com";

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	public static AppiumDriver<?> getDriver() {
		return driver;
	}

	/**
	 * Sets the driver.
	 *
	 * @param driver the new driver
	 */
	public static void setDriver(AppiumDriver<?> driver) {
		BaiduPageAction.driver = driver;
		
	}

	/**
	 * Search.
	 *
	 * @author young
	 * @param keyword the keyword
	 */
	public static void search(String keyword) {
		BaiduPage baiduPage = new BaiduPage(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), baiduPage);
		baiduPage.open(url);
		baiduPage.waitForPageLoad(60);
		baiduPage.typeInputBox(keyword);
		baiduPage.clickOnSearchButton();
		baiduPage.waitForPageLoad(60);
		Assert.assertTrue(baiduPage.getTitle().contains(keyword));
		baiduPage.waitForPageLoad(60);
		baiduPage.scrollDown();
	}
	

}
