package main.java.com.dbyl.appiumCore.utils;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

/**
 * This class is for executor appium command
 * 
 * @see IOSDriver and Android Driver
 * @author Young
 */
public class AppiumBaseExecutor
{
    private AppiumDriver<WebElement> driver;

    public AppiumBaseExecutor(AppiumDriver<WebElement> driver)
    {
        this.driver = driver;
    }

    public void type(Locator locator, String message)
    {
        WebElement e = findElement(locator);
        e.sendKeys(message);

    }

    public AppiumDriver<WebElement> getDriver()
    {
        return driver;
    }

    public void setDriver(AppiumDriver<WebElement> driver)
    {
        this.driver = driver;
    }

    public WebElement findElement(Locator locator)
    {
        WebElement e = null;
        switch (locator.getBy())
        {
            case xpath:
                e = driver.findElementByXPath(locator.getElement());
                break;
            case id:
                e = driver.findElementById(locator.getElement());
                break;
            case name:
                e = driver.findElementByName(locator.getElement());
                break;
            case cssSelector:
                e = driver.findElementByCssSelector(locator.getElement());
                break;
            case className:
                e = driver.findElementByClassName(locator.getElement());
                break;
            case tagName:
                e = driver.findElementByTagName(locator.getElement());
                break;
            case linkText:
                e = driver.findElementByLinkText(locator.getElement());
                break;
            case partialLinkText:
                e = driver.findElementByPartialLinkText(locator.getElement());
                break;
            case accessibilityId:
                e = driver.findElementByAccessibilityId(locator.getElement());
            case androidUIAutomator:
                // ((AndroidDriver)
                // driver).findElementByAndroidUIAutomator(locator.getElement());
                break;
            case iOSUIAutomation:
                break;
            case by:
                break;
            default:

        }
        return e;

    }

}
