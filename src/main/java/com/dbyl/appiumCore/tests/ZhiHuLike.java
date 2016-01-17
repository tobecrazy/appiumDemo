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

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import main.java.com.dbyl.appiumServer.AppiumLogger;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZhiHuLike
{
    AppiumLogger                         logger    = new AppiumLogger(JDTest.class);
    private AndroidDriver<MobileElement> driver;
    boolean                              isInstall = false;
    private String                       url;

    /**
     * @author Young
     * @throws IOException
     * @throws InterruptedException
     */
    @BeforeClass(alwaysRun = true)
    public void startRecord() throws IOException, InterruptedException
    {

        Runtime rt = Runtime.getRuntime();
        // this code for record the screen of your device
        rt.exec("cmd.exe /C adb shell screenrecord /sdcard/runCase.mp4");

    }

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception
    {

        url = AppiumServerUtils.startServer("127.0.0.1", 4723);
        logger.info("get url" + url);
        // set up appium

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
        // if no need install don't add this
        if (isInstall)
        {
            File classpathRoot = new File(System.getProperty("user.dir"));
            File appDir = new File(classpathRoot, "apps");
            File app = new File(appDir, "zhihu.apk");
            capabilities.setCapability("app", app.getAbsolutePath());
        }
        capabilities.setCapability("appPackage", "com.zhihu.android");
        // support Chinese
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        // no need sign
        capabilities.setCapability("noSign", "True");
        capabilities.setCapability("appActivity", ".ui.activity.GuideActivity");
        driver = new AndroidDriver<MobileElement>(new URL(url), capabilities);

    }

    public void login()
    {
        By loginElement = By.id("com.zhihu.android:id/login_and_register");
        WebElement loginButton;
        if (isElementPresent(driver, loginElement, 60))
        {
            loginButton = driver.findElement(loginElement);
            loginButton.click();
        } else
        {
            Assert.fail("login failed");
        }

        // wait for 20s
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // find login userName and password editText
        WebElement userNameInputbox = driver.findElementById("com.zhihu.android:id/email_or_phone");
        userNameInputbox.sendKeys("13282774643");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement passwordInputBox = driver.findElementById("com.zhihu.android:id/password");
        passwordInputBox.sendKeys("appium123");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement confirmButton = driver.findElementById("com.zhihu.android:id/btn_confirm");
        if (confirmButton.isEnabled())
        {
            confirmButton.click();
        } else
        {
            Assert.assertTrue(false, "Login failed");
        }

        // find ok button byName
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

        Assert.assertTrue(driver.findElement(By.name("首页")).isDisplayed());

    }

    @Test(groups = "swipeTest", priority = 1)
    public void swipe()
    {

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        if (!isElementPresent(By.name("首页"), 20))
        {
            login();
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, String> swipeObject = new HashMap<String, String>();
        swipeObject.put("startX", "100");
        swipeObject.put("startY", "400");
        swipeObject.put("endX", "100");
        swipeObject.put("endY", "200");
        swipeObject.put("duration", "400");

        js.executeScript("mobile: swipe", swipeObject);
        snapshot((TakesScreenshot) driver, "zhihu_before_swipe.png");
        swipeToUp(driver, 500);
        snapshot((TakesScreenshot) driver, "zhihu_after_swipe.png");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        swipeToDown(driver, 1000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<MobileElement> titles = driver.findElementsById("com.zhihu.android:id/title");
        logger.info(titles.get(0).getText());
        titles.get(0).click();
        

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // swipe to right
        swipeToRight(driver, 100);
        By by = new By.ById("com.zhihu.android:id/showcase_close");

        snapshot((TakesScreenshot) driver, "zhihu_showClose.png");
        if (isElementPresent(by, 30))
        {
            driver.findElement(by).click();
        }
        swipeToRight(driver, 500);
    }

    /**
     * This Method for swipe up
     * 
     * @author Young
     * @param driver
     * @param during
     */
    public void swipeToUp(AndroidDriver<MobileElement> driver, int during)
    {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, during);
    }

    /**
     * This Method for swipe down
     * 
     * @author Young
     * @param driver
     * @param during
     */
    public void swipeToDown(AndroidDriver<MobileElement> driver, int during)
    {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, during);
    }

    public List<MobileElement> swipeToElement(By by, int during)
    {
        List<MobileElement> allTheAnwsers = new ArrayList<MobileElement>();
        List<MobileElement> myAnwsers = driver.findElementsById("com.zhihu.android:id/title");
        allTheAnwsers.addAll(myAnwsers);
        while (!isElementPresent(by, 2))
        {
            swipeToUp(driver, during);
            myAnwsers = driver.findElementsById("com.zhihu.android:id/title");
            allTheAnwsers.addAll(myAnwsers);
        }
        return allTheAnwsers;
    }

    private boolean isElementPresent(MobileElement e)
    {
        boolean isPresent = false;
        try
        {
            if (e.isDisplayed())
            {
                isPresent = true;
            }

        } catch (Exception e1)
        {
            isPresent = false;
            logger.debug("Element isn't Present");
        }
        return isPresent;
    }

    /**
     * This Method for swipe Left
     * 
     * @author Young
     * @param driver
     * @param during
     */
    public void swipeToLeft(AndroidDriver<?> driver, int during)
    {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, during);
    }

    /**
     * This Method for swipe Right
     * 
     * @author Young
     * @param driver
     * @param during
     */
    public void swipeToRight(AndroidDriver<MobileElement> driver, int during)
    {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, during);
        // wait for page loading
    }

    @Test(groups = { "like" }, priority = 2)
    public void clickLike()
    {

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (!isElementPresent(By.name("首页"), 5))
        {
            login();
        }

        driver.swipe(100, 400, 100, 200, 500);
        WebElement myButton = driver.findElement(By.className("android.widget.ImageButton"));
        myButton.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.swipe(700, 500, 100, 500, 10);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<MobileElement> textViews = driver.findElementsByClassName("android.widget.TextView");
        textViews.get(0).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElementById("com.zhihu.android:id/name").click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // wait for 60s if WebElemnt show up less than 60s , then return , until
        // 60s

        By by = new By.ById("com.zhihu.android:id/showcase_close");

        snapshot((TakesScreenshot) driver, "zhihu_showClose.png");
        if (isElementPresent(by, 30))
        {
            driver.findElement(by).click();
        }

        driver.findElementByXPath(
                "//android.widget.RelativeLayout/android.support.v7.widget.LinearLayoutCompat/android.support.v7.widget.LinearLayoutCompat[2]")
                .click();
        String userName = "Young liu";
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + userName + "\")").click();
        swipeToDown(driver, 1);
        driver.findElementByName("答过").click();

        List<MobileElement> allTheAnwsers = swipeToElement(new By.ByName("是否我一直追一个女孩儿过两年她就跟我了呢？"), 100);
        // driver.scrollTo("是否我一直追一个女孩儿过两年她就跟我了呢？");

        allTheAnwsers.addAll(allTheAnwsers);
        System.out.println(allTheAnwsers.size());
        for (MobileElement e : allTheAnwsers)
        {
            e.click();
            // click like
        }

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

    /**
     * @param by
     * @param timeOut
     * @return
     */
    public boolean isElementPresent(final By by, int timeOut)
    {
        return isElementPresent(driver, by, timeOut);

    }

    /**
     * @author Young
     * @param driver
     * @param by
     * @param timeout
     * @return
     */
    public boolean isElementPresent(AndroidDriver<MobileElement> driver, final By by, int timeout)
    {
        boolean isPresent;
        try
        {
            isPresent = new AndroidDriverWait(driver, timeout).until(new ExpectedCondition<MobileElement>()
            {

                @Override
                public MobileElement apply(AndroidDriver d)
                {
                    return (MobileElement) d.findElement(by);

                }

            }).isDisplayed();
        } catch (Exception e)
        {
            isPresent = false;
        }
        return isPresent;
    }

    /**
     * This method for delete text in textView
     * 
     * @author Young
     * @param text
     */

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception
    {
        driver.quit();
        AppiumServerUtils.stopServer();

    }
}