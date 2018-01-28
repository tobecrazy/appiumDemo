package main.java.com.dbyl.appiumCore.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import main.java.com.dbyl.appiumCore.utils.AppiumBaseExecutor;

/**
 * The Class TianQiMainPage.
 *
 * @author young
 */
public class TianQiMainPage extends AppiumBaseExecutor {

	/**
	 * Instantiates a new tian qi main page.
	 *
	 * @param driver the driver
	 */
	public TianQiMainPage(AppiumDriver<?> driver) {
		super(driver);
	}

	/** The tab home. */
	@AndroidFindBy(id="cn.dbyl.young.tianqi:id/tab_home")
	MobileElement tab_home;
	
	/** The tab personal. */
	@AndroidFindBy(id="cn.dbyl.young.tianqi:id/tab_personal")
	MobileElement tab_personal;
	
	
	
	
}
