package main.java.com.dbyl.appiumCore;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class JDTest
{
    private AndroidDriver<WebElement> driver;

    @BeforeClass(alwaysRun = true)
    public void startAppiumServer() throws IOException, InterruptedException
    {
        AppiumServerUtils.startServer();

    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "5.1");
        // if no need install don't add this
        // capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.jingdong.app.mall");
        // support Chinese
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        // no need sign
        capabilities.setCapability("noSign", "True");
        capabilities.setCapability("appActivity", ".MainActivity");
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test(groups = { "JDTest" })
    public void addContact()
    {
        List<WebElement> bottomElements = driver
                .findElementsByXPath("//android.widget.FrameLayout//android.widget.RadioButton");
        for (WebElement e : bottomElements)
        {
            e.click();
        }
        bottomElements.get(4).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        WebElement loginButton = driver.findElementById("com.jingdong.app.mall:id/dit");
        loginButton.click();
        List<WebElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
        textFieldsList.get(0).sendKeys("中文测试");

        textFieldsList.get(1).sendKeys("啊啊");

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception
    {
        driver.quit();
    }
}