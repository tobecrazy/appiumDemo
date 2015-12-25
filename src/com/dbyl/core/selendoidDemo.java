package com.dbyl.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;

public class selendoidDemo {
	@BeforeTest
	public void beforeTestSetCapabilities()
	{
		
	}

	@Test
	public void runTest() throws Exception
	{
		SelendroidCapabilities capa = new SelendroidCapabilities("com.jingdong.app.mall:4.4.3");
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
