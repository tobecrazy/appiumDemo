package main.java.com.dbyl.appiumServer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import main.java.com.dbyl.appiumCore.utils.AppiumBaseExecutor;

/**
 * The Class BaseUITest.
 */
public class BaseUITest extends AppiumBaseExecutor {
	
	/** The logger. */
	AppLogger logger = new AppLogger(BaseUITest.class);

	/**
	 * Instantiates a new base UI test.
	 *
	 * @param driver the driver
	 */
	public BaseUITest(AppiumDriver<WebElement> driver) {
		super(driver);
	}

	/**
	 * Start.
	 *
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecuteException the execute exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void start() throws InterruptedException, ExecuteException, IOException {
		AppiumServerUtils.getInstance().startServer("127.0.0.1", 4723);
	}

	/**
	 * Stop.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	public void stop() throws InterruptedException {
		AppiumServerUtils.getInstance().stopServer();
	}

	/**
	 * Take screen shot.
	 *
	 * @author young
	 * @param driver the driver
	 */
	public void takeScreenShot(AppiumDriver<?> driver) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String dateStr = sf.format(date);
		logger.info(driver.getTitle() + "\n");
		String path = this.getClass().getSimpleName() + "_" + dateStr + ".png";
		takeScreenShot((TakesScreenshot) driver, path);
	}

	/**
	 * Take screen shot.
	 *
	 * @author Young
	 * @param drivername the drivername
	 * @param path the path
	 */
	public void takeScreenShot(TakesScreenshot drivername, String path) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name
		String currentPath = System.getProperty("user.dir"); // get current work
		logger.info(currentPath);
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		try {
			logger.info("save snapshot path is:" + currentPath + path);
			FileUtils.copyFile(scrFile, new File(currentPath + "\\" + path));
		} catch (Exception e) {
			logger.error("Can't save screenshot");
			e.printStackTrace();
		} finally {
			logger.info("screen shot finished");
		}
	}
}
