package main.java.com.dbyl.appiumCore.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import main.java.com.dbyl.appiumCore.tests.AppDemo;

public class TakeScreenShotListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		System.out.println(tr.getTestName());
		WebDriver currentDriver = ((AppDemo) tr.getInstance()).driver;
		System.out.println(currentDriver.getTitle());
		snapshot(currentDriver, currentDriver.getTitle());
	}

	/**
	 * @author young
	 * @param driver
	 * @param filename
	 */
	public void snapshot(WebDriver driver, String filename) {
		String currentPath = System.getProperty("user.dir"); // get current work
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			System.out.println("save snapshot path is:" + currentPath + "/" + filename);
			FileUtils.copyFile(scrFile, new File(currentPath + "\\" + filename));
		} catch (IOException e) {
			System.out.println("Can't save screenshot");
		} finally {
			System.out.println("screen shot finished, it's in " + currentPath + " folder");
		}
	}

}
