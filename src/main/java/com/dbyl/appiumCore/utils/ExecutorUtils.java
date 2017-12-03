package main.java.com.dbyl.appiumCore.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

/**
 * @since 2016-03-04
 * @version v1.2
 * @author Young
 *
 */
public class ExecutorUtils {

	/**
	 * This method is for execute the command without track, if return 1 means
	 * run successful
	 * 
	 * @author Young
	 * @param command
	 * @return
	 * @throws ExecuteException
	 * @throws IOException
	 */
	public static String runWithoutWatchDog(String command)
			throws ExecuteException, IOException {
		CommandLine cmdLine = CommandLine.parse(command);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream,
				errorStream);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(streamHandler);
		int exitValue = executor.execute(cmdLine);
		if (exitValue == 0) {
			String out = outputStream.toString("utf-8");
			return out;
		} else {
			String error = errorStream.toString("utf-8");
			return error;
		}

	}

	public static int runWithoutWatchDog(String command, boolean isDebug)
			throws ExecuteException, IOException {
		CommandLine cmdLine = CommandLine.parse(command);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream,
				errorStream);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(streamHandler);
		int exitValue =-1;
		String error = null;
		try{
			 exitValue = executor.execute(cmdLine);
		}
		catch(Exception e)
		{
			error=e.getMessage();
			System.out.println("Encounter an error when try to excute:"+command);
			
		}
		 
		if (isDebug) {
			String out = outputStream.toString("utf-8");
			error = error+ errorStream.toString("utf-8");
			System.out.println("==========================================");
			System.out.println(out);
			System.out.println(error);
			System.out.println("==========================================");

		}
		return exitValue;
	}

	/**
	 * @author Young
	 * @param command
	 * @param isDebug TODO
	 * @return
	 * @throws ExecuteException
	 * @throws IOException
	 */
	public static int runWithWatchDog(String command, boolean isDebug) throws ExecuteException,
			IOException {
		CommandLine cmdLine = CommandLine.parse(command);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream,
				errorStream);
		ExecuteWatchdog watchdog = new ExecuteWatchdog(6000);
		executor.setWatchdog(watchdog);
		executor.setStreamHandler(streamHandler);
		int exitValue =-1;
		String error=null;
		try{
			 exitValue = executor.execute(cmdLine);
		}
		catch(Exception e)
		{
			error=e.getMessage();
			System.out.println("Encounter an error when try to excute:"+command);
			
		}
		 
		if (isDebug) {
			String out = outputStream.toString("utf-8");
			error = error+ errorStream.toString("utf-8");
			System.out.println("==========================================");
			System.out.println(out);
			System.out.println(error);
			System.out.println("==========================================");

		}
		return exitValue;
	}

}
