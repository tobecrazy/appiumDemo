package main.java.com.dbyl.appiumCore.tests;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @since 2015-6
 * @author Young
 */
public class DemoForGestureLocker
{
    private URL                      ServerUrl;
    private AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void startAppiumServer() throws IOException, InterruptedException
    {
        ServerUrl = AppiumServerUtils.getInstance().startServer("127.0.0.1", 4723);
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
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
        // if no need install don't add this

        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "Locker.apk");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.AppiumGirls.locker");
        // set no sign
        capabilities.setCapability("noSign", "True");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        driver = new AndroidDriver<MobileElement>(ServerUrl, capabilities);

    }

    @Test(groups = { "GustureLockerTest" })
    public void GustureLockerTest() throws InterruptedException
    {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        MobileElement button = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"设置手势密码\")");
        button.click();
       
        // get all the items of gesture locker
        List<MobileElement> items = driver.findElementsByClassName("android.widget.ImageView");

        for (MobileElement item : items)
        {
            /**
             * 0 1 2 3 4 5 6 7 8
             */
            item.click();
        }

        // create a Z from 0->1->2->4->6->7->8
        TouchAction touches = new TouchAction(driver);
		touches.press(items.get(0)).waitAction().moveTo(items.get(1)).waitAction().moveTo(items.get(2))
                .waitAction().moveTo(items.get(4)).moveTo(items.get(6)).waitAction().moveTo(items.get(7))
                .waitAction().moveTo(items.get(8)).release();
        touches.perform();
        
        touches.press(items.get(0)).waitAction().moveTo(items.get(1)).waitAction().moveTo(items.get(2))
                .waitAction().moveTo(items.get(4)).release();
        touches.perform();
        Assert.assertTrue(driver.findElementByName("与上一次绘制不一致，请重新绘制").isDisplayed());

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception
    {
        driver.quit();
        AppiumServerUtils.getInstance().stopServer();
    }

}