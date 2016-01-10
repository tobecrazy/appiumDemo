package main.java.com.dbyl.appiumCore.utils;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

public class DriverFactory
{
    public DriverFactory(AppiumDriver<WebElement> driver)
    {
        new InitDriver(driver);

    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
        AppiumServerUtils.startServer();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "5.1");
        // if no need install don't add this
        // capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.jingdong.app.mall");
        // support Chinese
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        // no need sign
        capabilities.setCapability("noSign", "True");
        capabilities.setCapability("appActivity", ".MainActivity");
        AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);
        new InitDriver(driver);
    }
}
