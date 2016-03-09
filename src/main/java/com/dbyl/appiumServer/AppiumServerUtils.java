package main.java.com.dbyl.appiumServer;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * @author Young
 * @since 2016-03-09
 */
public class AppiumServerUtils {
	public static String APPIUMSERVERSTART = "C:\\Program Files (x86)\\Appium\\node_modules\\.bin\\appium.cmd";
	private static String definedNode = "C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js";
	static AppiumDriverLocalService service;
	private static String OSType = System.getProperty("os.name");

	/**
	 * @param ip
	 * @param port
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ExecuteException
	 */
	public static String startServer(String ip, int port) throws InterruptedException, ExecuteException, IOException {
		stopAppiumServer(Integer.toString(port));
		String serverURL = null;
		if (OSType.contains("Mac")) {
			definedNode = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
			service = AppiumDriverLocalService.buildDefaultService();
		} else {
			System.setProperty(AppiumServiceBuilder.APPIUM_PATH, definedNode);
			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withIPAddress(ip).usingPort(port)
					.withArgument(GeneralServerFlag.LOG_LEVEL, "debug").withArgument(GeneralServerFlag.LOG_TIMESTAMP));
		}

		if (service != null) {
			serverURL = service.getUrl().toString();
			service.start();
			return serverURL;

		} else {
			throw new InterruptedException();
		}

	}

	/**
	 * @author Young
	 */
	public static void stopServer() {
		if (service != null) {
			service.stop();
		}
	}

	/**
	 * use Appium services
	 * 
	 * @deprecated
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void startServer() throws IOException, InterruptedException {
		stopAppiumServer("4723");
		startServer("4723");
		// RuntimeExec appiumObj = new RuntimeExec();
		// appiumObj.excuteCMD(APPIUMSERVERSTART);
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		CommandLine commandLine = CommandLine.parse(APPIUMSERVERSTART);
		ExecuteWatchdog dog = new ExecuteWatchdog(60 * 1000);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.setWatchdog(dog);
		executor.execute(commandLine, resultHandler);
		resultHandler.waitFor(5000);
		System.out.println("Appium server start");
	}

	/**
	 * @deprecated
	 * @author Young
	 * @param port
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void startServer(String port) throws IOException, InterruptedException {
		stopAppiumServer("4723");
		RuntimeExec stop = new RuntimeExec();
		stop.stopAppiumServer(port);
		// RuntimeExec appiumObj = new RuntimeExec();
		// appiumObj.excuteCMD(APPIUMSERVERSTART);
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		CommandLine commandLine = CommandLine.parse(APPIUMSERVERSTART);
		ExecuteWatchdog dog = new ExecuteWatchdog(60 * 1000);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.setWatchdog(dog);
		executor.execute(commandLine, resultHandler);
		resultHandler.waitFor(5000);
		System.out.println("Appium server start");
	}

	/**
	 * @author Young
	 * @param appiumServicePort
	 * @throws ExecuteException
	 * @throws IOException
	 */
	public static void stopAppiumServer(String appiumServicePort) throws ExecuteException, IOException {
		String cmdline = "cmd /c echo off & FOR /F \"usebackq tokens=5\" %a in" + " (`netstat -nao ^| findstr /R /C:\""
				+ appiumServicePort + "\"`) do (FOR /F \"usebackq\" %b in"
				+ " (`TASKLIST /FI \"PID eq %a\" ^| findstr /I node.exe`) do taskkill /F /PID %a)";
		RuntimeExec exec = new RuntimeExec();
		if (OSType.contains("Mac")) {
			cmdline = "lsof -i:" + appiumServicePort + "|grep -v 'PID' |awk  {'print \\$2'}|xargs kill -9";
		}
		exec.excuteCMD(cmdline);
	}
}
