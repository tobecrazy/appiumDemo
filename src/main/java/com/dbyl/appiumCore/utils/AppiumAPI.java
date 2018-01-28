package main.java.com.dbyl.appiumCore.utils;

import io.appium.java_client.MobileElement;

/**
 * @version 1.2.1
 * @author young
 *
 */
public interface AppiumAPI {
	public void click(MobileElement e);

	public void type(MobileElement e, String message);

	public String getText(MobileElement e);

	public void scrollToElement(MobileElement e);

	public boolean isElementPresent(MobileElement e);

}
