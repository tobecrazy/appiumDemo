package main.java.com.dbyl.appiumCore.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MobileFindJavaTest
{

    private AppiumDriver<?> driver;
    private static String   url;

    @Test
    public void apiDemo() throws Exception
    {
        driver.scrollTo("about phone");
        driver.scrollTo("Sound");
    }

    @Before
    public void setUp() throws Exception
    {
        url = AppiumServerUtils.startServer("127.0.0.1", 4723);
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("appPackage", "com.android.settings");
        capabilities.setCapability("appActivity", ".Settings");
        driver = new AndroidDriver<>(new URL(url), capabilities);
    }

    @After
    public void tearDown() throws Exception
    {
        driver.quit();
    }
}