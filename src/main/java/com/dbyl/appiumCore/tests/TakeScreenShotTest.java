package main.java.com.dbyl.appiumCore.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TakeScreenShotTest
{
    public static AndroidDriver<WebElement> driver;

    /**
     * @author Young
     * @throws IOException
     */
//    public void startRecord() throws IOException
//    {
//    	String cmd="cmd.exe /C adb shell screenrecord /sdcard/runCase.mp4";
//    	if(System.getProperty("os.name").contains("Mac"))
//    	{
//    		cmd="adb shell screenrecord /sdcard/runCase.mp4";
//    	}
//        Runtime rt = Runtime.getRuntime(); 
//        // this code for record the screen of your device
//        rt.exec(cmd);
//
//    }

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception
    {
        // set up appium

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
        driver = new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
//        startRecord();

    }

    @Test
    public void runChromeWebBrowser()
    {
        driver.get("http://www.baidu.com");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println(driver.getTitle());
        snapshot((TakesScreenshot) driver, "before_search.png");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('index-kw').value='appium'");
        // driver.findElementByXPath("//textarea[@id='index-kw']").sendKeys("appium");
        // driver.tap(1,
        // driver.findElement(By.xpath("//button[@id='index-bn']")), 100);
        driver.findElement(By.xpath("//button[@id='index-bn']")).click();
        System.out.println(driver.getTitle());
        // snapshot((TakesScreenshot) driver, "after_search.png");
        Assert.assertTrue(driver.getTitle().contains("appium"));

    }

    /**
     * This Method create for take screenshot
     * 
     * @author Young
     * @param drivername
     * @param filename
     */
    public static void snapshot(TakesScreenshot drivername, String filename)
    {
        // this method will take screen shot ,require two parameters ,one is
        // driver name, another is file name

        String currentPath = System.getProperty("user.dir"); // get current work
                                                             // folder
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy
        // somewhere
        try
        {
            System.out.println("save snapshot path is:" + currentPath + "/" + filename);
            FileUtils.copyFile(scrFile, new File(currentPath + "\\" + filename));
        } catch (IOException e)
        {
            System.out.println("Can't save screenshot");
            e.printStackTrace();
        } finally
        {
            System.out.println("screen shot finished, it's in " + currentPath + " folder");
        }
    }

    @AfterClass
    public void afterTestStopDriver()
    {
        driver.quit();
    }
}