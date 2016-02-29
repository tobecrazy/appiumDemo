package main.java.com.dbyl.appiumCore.utils;

import io.appium.java_client.MobileElement;

public interface AppiumAPI {
	public void click(MobileElement e);
	public void type(MobileElement e,String message);
	public String getText(MobileElement e);
	
}
