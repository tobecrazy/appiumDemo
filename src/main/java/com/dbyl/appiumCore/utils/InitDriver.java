package main.java.com.dbyl.appiumCore.utils;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * This class if for init driver
 * 
 * @author Young
 */
public class InitDriver
{
    public InitDriver(AppiumDriver<WebElement> driver)
    {
        // if Android driver will execute init Android driver
        if (driver instanceof AndroidDriver)
        {
            initAndroidDriver();
        } else if (driver instanceof IOSDriver)
        {
            initIOSDriver();
        } else
        {
            System.out.println("wait for appium support WindowsPhone");
        }

    }

    private void initIOSDriver()
    {
        System.out.println("init Android");

    }

    private void initAndroidDriver()
    {
        System.out.println("init iOS");

    }
}
