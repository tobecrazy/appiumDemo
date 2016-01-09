package main.java.com.dbyl.appiumCore;

import java.io.IOException;

/**
 * @author Young
 */
public class AppiumServerUtils
{
    public static String APPIUMSERVERSTART = "C:\\Program Files (x86)\\Appium\\node_modules\\.bin\\appium.cmd";

    public static void startServer() throws IOException, InterruptedException
    {
        RuntimeExec stop = new RuntimeExec();
        stop.stopAppiumServer("4723");
        RuntimeExec appiumObj = new RuntimeExec();
        appiumObj.excuteCMD(APPIUMSERVERSTART);
        System.out.println("Appium server start");
    }

}
