package main.java.com.dbyl.appiumServer;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;

/**
 * @author Young
 */
public class AppiumServerUtils
{
    public static String APPIUMSERVERSTART = "C:\\Program Files (x86)\\Appium\\node_modules\\.bin\\appium.cmd";

    public static void startServer() throws IOException, InterruptedException
    {
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
     * @author Young
     * @param port
     * @throws IOException
     * @throws InterruptedException
     */
    public static void startServer(String port) throws IOException, InterruptedException
    {
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
    public static void stopAppiumServer(String appiumServicePort) throws ExecuteException, IOException
    {
        RuntimeExec exec = new RuntimeExec();
        exec.excuteCMD("cmd /c echo off & FOR /F \"usebackq tokens=5\" %a in" + " (`netstat -nao ^| findstr /R /C:\""
                + appiumServicePort + "\"`) do (FOR /F \"usebackq\" %b in"
                + " (`TASKLIST /FI \"PID eq %a\" ^| findstr /I node.exe`) do taskkill /F /PID %a)");
    }
}
