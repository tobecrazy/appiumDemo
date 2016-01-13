package main.java.com.dbyl.appiumCore.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PureWebDriver
{
    private AndroidDriver<WebElement> driver;
    private String                    url;

    @BeforeClass(alwaysRun = true)
    public void startAppiumServer() throws IOException, InterruptedException
    {
        url = AppiumServerUtils.startServer("127.0.0.1", 4723);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        driver = new AndroidDriver<WebElement>(new URL(url), capabilities);
    }

    @Test(groups = { "taobao" })
    public void webDriver()
    {
        driver.get("http://m.baidu.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement tmall = driver.findElementByLinkText("天猫");
        tmall.click();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception
    {
        driver.quit();
        AppiumServerUtils.stopServer();
    }
}