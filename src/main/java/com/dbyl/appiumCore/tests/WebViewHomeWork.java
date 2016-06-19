package main.java.com.dbyl.appiumCore.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

public class WebViewHomeWork
{
    private AndroidDriver<MobileElement> driver;
    private String                       keyword = "appium";

    @BeforeClass
    public void startAppiumServer() throws IOException, InterruptedException
    {
        AppiumServerUtils.startServer("127.0.0.1", 4723);
        Thread.sleep(1000);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception
    {
        // set up appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        // if no need install don't add this

        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "webview.apk");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.testerhome.webview");
        // // support Chinese
        // capabilities.setCapability("unicodeKeyboard", "True");
        // capabilities.setCapability("resetKeyboard", "True");
        // no need sign
        capabilities.setCapability("noSign", "True");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @Test(groups = { "webView" })
    public void webViewTest()
    {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Set<String> windows = driver.getContextHandles();
        for (String window : windows)
        {
            // 输出context
            System.out.println(window);
        }
        driver.context("WEBVIEW_com.testerhome.webview");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElementByXPath("//textarea[@id='index-kw']").sendKeys(keyword);
        // driver.tap(1,
        // driver.findElement(By.xpath("//button[@id='index-bn']")), 100);
        driver.findElement(By.xpath("//button[@id='index-bn']")).click();

        // 返回native app context
        driver.context("NATIVE_APP");
        WebElement title = driver.findElementById("android:id/action_bar_title");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(title.getText().contains(keyword));

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception
    {
        driver.quit();
        AppiumServerUtils.stopAppiumServer("4723");
    }

}