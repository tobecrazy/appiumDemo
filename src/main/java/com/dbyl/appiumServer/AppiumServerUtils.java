package main.java.com.dbyl.appiumServer;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;

/**
 * @author Young
 * @since 2016-11-09
 */
public class AppiumServerUtils {
	public static AppiumDriverLocalService service;
	public static AppiumServerUtils appiumServerUtils;

	/**
	 * @author young
	 * @return
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
	 * This method is for start appium use default host and IP
	 * 
	 * @author young
	 * @return
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
	 * @author Young
	 */
	public void stopServer() {
		if (service != null) {
			service.stop();
		}
	}

	/**
	 * @author young
	 * @param ipAddress
	 * @param port
	 * @return
	 */
	public URL startServer(String ipAddress, int port) {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress(ipAddress);
		builder.usingPort(port);
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		if (service == null || !service.isRunning()) {
			throw new RuntimeException("An appium server node is not started!");
		}
		return service.getUrl();

	}

	/**
	 * @author young
	 * @param ipAddress
	 * @param port
	 * @param logFile
	 * @param arguments
	 * @return
	 */
	public URL startServer(String ipAddress, int port, File logFile, ServerArgument... arguments) {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress(ipAddress);
		builder.usingPort(port);
		builder.withLogFile(logFile);
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
	 * @author young
	 * @param ipAddress
	 * @param port
	 * @param capabilities
	 * @return
	 */
	public URL startServer(String ipAddress, int port, DesiredCapabilities capabilities) {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress(ipAddress);
		builder.usingPort(port);
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
