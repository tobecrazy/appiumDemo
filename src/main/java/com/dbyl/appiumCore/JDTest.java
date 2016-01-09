package main.java.com.dbyl.appiumCore;
<<<<<<< HEAD

=======
 
>>>>>>> 98091ccf0dd34d76a190c469feb44daa9254f580
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
<<<<<<< HEAD
=======
 

>>>>>>> 98091ccf0dd34d76a190c469feb44daa9254f580

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
<<<<<<< HEAD

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
=======
 
public class JDTest {
	private  AndroidDriver<WebElement>  driver;
 
	@BeforeClass(alwaysRun=true)
	public void startAppiumServer() throws IOException
	{
//		 Process p = Runtime
//		 .getRuntime()
//		 .exec("cmd.exe /C appium");
//		 BufferedReader bf = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//		 String line = null;
//		 while((line = bf.readLine()) != null) {
//		 System.out.println(line);
//		 }
		
	}
    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {
>>>>>>> 98091ccf0dd34d76a190c469feb44daa9254f580
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "JD.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
<<<<<<< HEAD
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "5.1");
        // if no need install don't add this
        // capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.jingdong.app.mall");
        // support Chinese
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        // no need sign
=======
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "5.1");
        //if no need install don't add this
        //capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.jingdong.app.mall");
        //support Chinese 
        capabilities.setCapability("unicodeKeyboard" ,"True");
        capabilities.setCapability("resetKeyboard", "True");
        //no need sign
>>>>>>> 98091ccf0dd34d76a190c469feb44daa9254f580
        capabilities.setCapability("noSign", "True");
        capabilities.setCapability("appActivity", ".MainActivity");
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
<<<<<<< HEAD

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
=======
 

 
    @Test(groups={"JDTest"})
    public void addContact(){
        List<WebElement> bottomElements = driver.findElementsByXPath("//android.widget.FrameLayout//android.widget.RadioButton");
        for(WebElement e:bottomElements)
        {
        	   e.click();
        }
        bottomElements .get(4).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        WebElement loginButton=driver.findElementById("com.jingdong.app.mall:id/dit");
        loginButton.click();
        List<WebElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
        textFieldsList.get(0).sendKeys("中文测试");
 
        textFieldsList.get(1).sendKeys("啊啊");
    
    }
    
    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
>>>>>>> 98091ccf0dd34d76a190c469feb44daa9254f580
        driver.quit();
    }
}