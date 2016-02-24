package main.java.com.dbyl.appiumCore.page;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AppDemoPage {
	AppiumDriver<?> driver;
	public AppDemoPage(AppiumDriver<?> d){
		this.driver=d;
	}
	@AndroidFindBy(accessibility ="for appium test")
	MobileElement textView;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\")")
	MobileElement button;
	@AndroidFindBy(className="android.widget.Button")
	MobileElement button1;
	@AndroidFindBy(id="cn.dbyl.appiumdemo:id/text1")
	MobileElement text;
	
	public void clickButton()
	{
		button.click();
	}
	
	public String getText()
	{
		return text.getText();
	}
	
	
	
}
