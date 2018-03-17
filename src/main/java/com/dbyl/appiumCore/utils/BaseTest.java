package main.java.com.dbyl.appiumCore.utils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;

/**
 * The Class BaseTest.
 * 
 * @author Young
 * @version V1.0
 * @since 2018/3/17
 */
@Listeners({ main.java.com.dbyl.appiumCore.testng.TestngListener.class })
public class BaseTest {

	/**
	 * After suite.
	 * 
	 * @author Young
	 */
	@AfterSuite
	public void afterSuite() {
        
	}

}
