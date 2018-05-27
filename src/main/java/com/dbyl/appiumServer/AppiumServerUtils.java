package main.java.com.dbyl.appiumServer;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;

/**
 * The Class AppiumServerUtils.
 *
 * @author Young
 * @since 2016-11-09
 */
public class AppiumServerUtils {
	
	/** The service. */
	public static AppiumDriverLocalService service;
	
	/** The appium server utils. */
	public static AppiumServerUtils appiumServerUtils;
	
	/** The current folder. */
	public String currentFolder=System.getProperty("user.dir");
	
	/**
	 * Gets the single instance of AppiumServerUtils.
	 *
	 * @author young
	 * @return single instance of AppiumServerUtils
	 */
	public static AppiumServerUtils getInstance() {
		if (appiumServerUtils == null) {
			synchronized (AppiumServerUtils.class) {
				if (appiumServerUtils == null) {
					appiumServerUtils = new AppiumServerUtils();
				}

			}
		}
		return appiumServerUtils;
	}

	/**
	 * This method is for start appium use default host and IP.
	 *
	 * @author young
	 * @return the url
	 */
	public URL startAppiumServerByDefault() {
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		if (service == null || !service.isRunning()) {
			throw new RuntimeException("An appium server node is not started!");
		}
		return service.getUrl();
	}
	
	/**
	 * Start appium server no reset.
	 *
	 * @return the url
	 */
	public URL startAppiumServerNoReset() {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		if (service == null || !service.isRunning()) {
			throw new RuntimeException("An appium server node is not started!");
		}
		return service.getUrl();
	}

	/**
	 * Stop server.
	 *
	 * @author Young
	 */
	public void stopServer() {
		if (service != null) {
			service.stop();
		}
	}

	/**
	 * Start server.
	 *
	 * @author young
	 * @param ipAddress the ip address
	 * @param port the port
	 * @return the url
	 */
	public URL startServer(String ipAddress, int port) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String dateStr = sf.format(date);
		String path = currentFolder+"/logs/"+"appium_default_log_" + dateStr + ".log";
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress(ipAddress);
		builder.usingPort(port);
		File logFile=new File(path);
		builder.withLogFile(logFile);
		builder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
		builder.withEnvironment(System.getenv());
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		if (service == null || !service.isRunning()) {
			throw new RuntimeException("An appium server node is not started!");
		}
		return service.getUrl();

	}

	/**
	 * Start server.
	 *
	 * @author young
	 * @param ipAddress the ip address
	 * @param port the port
	 * @param logFile the log file
	 * @param arguments the arguments
	 * @return the url
	 */
	public URL startServer(String ipAddress, int port, File logFile, ServerArgument... arguments) {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress(ipAddress);
		builder.usingPort(port);
		builder.withLogFile(logFile);
		builder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
		for (ServerArgument argument : arguments) {
			builder.withArgument(argument);
		}
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		if (service == null || !service.isRunning()) {
			throw new RuntimeException("An appium server node is not started!");
		}
		return service.getUrl();

	}

	/**
	 * Start server.
	 *
	 * @author young
	 * @param ipAddress the ip address
	 * @param port the port
	 * @param capabilities the capabilities
	 * @return the url
	 */
	public URL startServer(String ipAddress, int port, DesiredCapabilities capabilities) {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress(ipAddress);
		builder.usingPort(port);
		builder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
		if (capabilities != null) {
			builder.withCapabilities(capabilities);
		}
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		if (service == null || !service.isRunning()) {
			throw new RuntimeException("An appium server node is not started!");
		}
		return service.getUrl();

	}

}
