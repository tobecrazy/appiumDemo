package main.java.com.dbyl.appiumCore.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

public class HybridDemo
{
    private static AndroidDriver<MobileElement> driver;

    @BeforeClass
    public static void startAppiumServer() throws IOException, InterruptedException
    {
        AppiumServerUtils.startServer("127.0.0.1", 4723);
    }

    @Before
    public void setUp() throws Exception
    {
        // set up appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
        // if no need install don't add this

        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "hujiang.apk");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.hujiang.normandy");
        // // support Chinese
        // capabilities.setCapability("unicodeKeyboard", "True");
        // capabilities.setCapability("resetKeyboard", "True");
        // no need sign
        capabilities.setCapability("noSign", "True");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".SplashActivity");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @Test
    public void swipeTest()
    {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // MobileElement loginButton =
        // driver.findElementById("com.hujiang.normandy:id/login");
        MobileElement button = driver
                .findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").text(\"登录\")");
        Assert.assertEquals(driver.findElementByClassName("android.widget.Button").getAttribute("clickable"), "true");
        button.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Set<String> allContext = driver.getContextHandles();
        for (String contex : allContext)
        {
            System.out.println(contex);
        }

        driver.context("WEBVIEW_com.hujiang.normandy");
        WebElement userNameInputBox = driver.findElementByClassName("hp-username");
        userNameInputBox.sendKeys("Test");
        WebElement passwodInputBox = driver.findElementByXPath("//input[@type='password']");
        passwodInputBox.sendKeys("Test");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement loginSubmitButton = driver.findElementByXPath("//android.widget.Button");
        loginSubmitButton.submit();

        WebElement errorMessage = driver.findElementByClassName("hp-err-tips");
        Assert.assertEquals(errorMessage.getText(), "用户名密码不匹配");

    }

    @AfterClass
    public static void StopServer() throws Exception
    {
        driver.quit();
        AppiumServerUtils.stopAppiumServer("4723");
    }

}
