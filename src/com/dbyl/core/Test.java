package com.dbyl.core;

import java.io.InputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.common.device.DeviceTargetPlatform;
 


public class Test {

	public static void main(String [] args) throws Exception
	{
		SelendroidCapabilities capa = new SelendroidCapabilities("io.selendroid.testapp:0.17.0");
//		capa.setPlatformVersion(DeviceTargetPlatform.ANDROID22);
//		capa.setEmulator(false);
//		capa.setModel("MeiZu");
		WebDriver driver = new SelendroidDriver(capa);
		WebElement inputField = driver.findElement(By.id("my_text_field"));
		Assert.assertEquals("true", inputField.getAttribute("enabled"));
		inputField.sendKeys("Selendroid");
		Assert.assertEquals("Selendroid", inputField.getText());
		driver.quit();
	}
}
