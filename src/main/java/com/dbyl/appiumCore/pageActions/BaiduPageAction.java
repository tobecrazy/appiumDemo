package main.java.com.dbyl.appiumCore.pageActions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.java.com.dbyl.appiumCore.page.BaiduPage;

public class BaiduPageAction {
	private static AppiumDriver<?> driver;

	public static AppiumDriver<?> getDriver() {
		return driver;
	}

	public static void setDriver(AppiumDriver<?> driver) {
		BaiduPageAction.driver = driver;
		
	}

	public static void search(String keyword) {
		BaiduPage baiduPage = new BaiduPage(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver,60, TimeUnit.SECONDS), baiduPage);
		baiduPage.waitForPageLoad(60);
		baiduPage.typeInputBox(keyword);
		baiduPage.clickOnSearchButton();
	}
	

}
