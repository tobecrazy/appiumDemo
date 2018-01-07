/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package main.java.com.dbyl.appiumCore.tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.com.dbyl.appiumServer.AppiumServerUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;


/**
 * Test Mobile Driver features
 */
public class AndroidGestureTest {
	private AndroidDriver<MobileElement> driver;
	private URL url;

	@BeforeClass
	public void beforeTestInit() throws Exception {
		url = AppiumServerUtils.getInstance().startAppiumServerByDefault();

	}

	@BeforeClass(dependsOnMethods={"beforeTestInit"})
	public void setup() throws Exception {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "ApiDemos-debug.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		driver = new AndroidDriver<>(url, capabilities);
	}

	@Test
	public void MultiGestureSingleActionTest() throws InterruptedException {

		MultiTouchAction multiTouch = new MultiTouchAction(driver);
		TouchAction action0 = new TouchAction(driver).tap(100, 300);
		multiTouch.add(action0).perform();
	}

	@Test
	public void dragNDropTest() {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\"Views\"))");
		driver.findElementByAccessibilityId("Views").click();

		driver.findElement(MobileBy.AndroidUIAutomator("description(\"Drag and Drop\")")).click();
		WebElement actionBarTitle = driver.findElement(MobileBy.AndroidUIAutomator("text(\"Views/Drag and Drop\")"));

		Assert.assertEquals("Wrong title.", "Views/Drag and Drop", actionBarTitle.getText());
		WebElement dragDot1 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		WebElement dragDot3 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_3"));

		WebElement dragText = driver.findElement(By.id("io.appium.android.apis:id/drag_text"));
		Assert.assertEquals("Drag text not empty", "", dragText.getText());

		TouchAction dragNDrop = new TouchAction(driver).longPress(dragDot1).moveTo(dragDot3).release();
		dragNDrop.perform();

		Assert.assertNotEquals("Drag text empty", "", dragText.getText());
	}

	@Test
	public void TapSingleFingerTest() throws InterruptedException {
		 
	}

	@Test
	public void elementGestureTest() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		MobileElement e = driver.findElement(MobileBy.AccessibilityId("App"));
		e.click();
		 
		 

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}