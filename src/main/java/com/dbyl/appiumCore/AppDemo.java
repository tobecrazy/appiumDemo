package main.java.com.dbyl.appiumCore;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppDemo
{
    private AndroidDriver<WebElement> driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception
    {
        // set up appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
        // if no need install don't add this

        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "apppiumDemo.apk");
        capabilities.setCapability("app", app.getAbsolutePath());

        capabilities.setCapability("appPackage", "com.example.apppiumdemo");
        // support Chinese
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        // no need sign
        capabilities.setCapability("noSign", "True");
        capabilities.setCapability("appActivity", ".MainActivity");
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception
    {
        driver.quit();
    }

    @Test(groups = { "swipeTest" })
    public void swipeTest()
    {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElementByClassName("android.widget.Button").getText(), "Test");
        System.out.println(driver.findElementByXPath("//android.widget.Button").getText());

    }

}