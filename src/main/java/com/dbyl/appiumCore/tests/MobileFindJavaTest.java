package main.java.com.dbyl.appiumCore.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MobileFindJavaTest
{

    private AndroidDriver<MobileElement> driver;
    private static String   url;


    @Before
    public void setUp() throws Exception
    {
        url = AppiumServerUtils.startServer("127.0.0.1", 4723);
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("appPackage", "com.android.settings");
        capabilities.setCapability("appActivity", ".Settings");
        driver = new AndroidDriver<MobileElement>(new URL(url), capabilities);
    }

    @Test
    public void apiDemo() throws Exception
    {
    	//scrollTo() and scrollToExact() became deprecated. They are going to be removed in the next release.
//        driver.scrollTo("about phone");
//        driver.scrollTo("Sound");
    }
    @After
    public void tearDown() throws Exception
    {
        driver.quit();
    }
}