package main.java.com.dbyl.appiumCore.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.TouchableElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
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
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "015d4bdf31202013");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        driver = new AndroidDriver<WebElement>(new URL(url), capabilities);
    }

    @Test(groups = { "taobao" })
    public void webDriver()
    {
        driver.get("http://m.taobao.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        MobileElement tmall = (MobileElement) driver.findElementByXPath("//div[@id='a6636-1']");
        tmall.tap(1, 1000);
        HashMap<String, Integer> tapObject = new HashMap<String, Integer>();
        tapObject.put("x", 120);
        tapObject.put("y", 120);
        tapObject.put("touchCount", 1);
        tapObject.put("duration", 1200);
        driver.executeScript("mobile: tap", tapObject);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> flickObject = new HashMap<String, Double>();
        flickObject.put("startX", 200.0);
        flickObject.put("startY", 700.5);
        flickObject.put("endX", 200.2);
        flickObject.put("endY", 100.5);
        js.executeScript("mobile: flick", flickObject);

        TouchAction action = new TouchAction(driver);

        action.press(tmall).waitAction(400).perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        List<WebElement> elements = driver.findElementsByXPath("//ul/li/a[@class='card-item card-style-chn']");
        for (WebElement e : elements)
        {
            System.out.println(e.getAttribute("href"));
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(driver.getPageSource());

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception
    {
        driver.quit();
        AppiumServerUtils.stopServer();
    }
}