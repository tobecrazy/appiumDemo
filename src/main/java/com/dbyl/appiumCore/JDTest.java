package main.java.com.dbyl.appiumCore;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
 


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;
import java.util.List;
 
public class JDTest {
	private  AndroidDriver  driver;
 
    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "JD.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
        //if no need install don't add this
        //capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.jingdong.app.mall");
        //support Chinese 
        capabilities.setCapability("unicodeKeyboard" ,"True");
        capabilities.setCapability("resetKeyboard", "True");
        //no need sign
        capabilities.setCapability("noSign", "True");
        capabilities.setCapability("appActivity", ".MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
 
    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }
 
    @Test(groups={"JDTest"})
    public void addContact(){
        WebElement el = driver.findElement(By.name("我的"));
        el.click();
        List<WebElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
        textFieldsList.get(0).sendKeys("Some Name");
        textFieldsList.get(1).sendKeys("10086");
        textFieldsList.get(2).sendKeys("Some@example.com");
        driver.swipe(100, 500, 100, 100, 2);
        driver.findElementByName("Save").click();
    }
}