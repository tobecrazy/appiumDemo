package main.java.com.dbyl.appiumCore.pageActions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.java.com.dbyl.appiumCore.page.BaiduPage;

public class BaiduPageAction {
	private static AppiumDriver<?> driver;
	public static String url="http://www.baidu.com";

	public static AppiumDriver<?> getDriver() {
		return driver;
	}

	public static void setDriver(AppiumDriver<?> driver) {
		BaiduPageAction.driver = driver;
		
	}

	/**
	 * @author young
	 * @param keyword
	 */
	public static void search(String keyword) {
		BaiduPage baiduPage = new BaiduPage(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver,60, TimeUnit.SECONDS), baiduPage);
		baiduPage.open(url);
		baiduPage.waitForPageLoad(60);
		baiduPage.typeInputBox(keyword);
		baiduPage.clickOnSearchButton();
		baiduPage.waitForPageLoad(60);
		Assert.assertTrue(baiduPage.getTitle().contains(keyword));
	}
	

}
