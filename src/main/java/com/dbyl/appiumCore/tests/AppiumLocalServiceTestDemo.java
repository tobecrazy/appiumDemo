package main.java.com.dbyl.appiumCore.tests;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumLocalServiceTestDemo
{
    private static String definedNode = "C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js";

    public static void main(String[] args) throws InterruptedException
    {

        System.setProperty(AppiumServiceBuilder.APPIUM_PATH, definedNode);
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1").usingPort(4723).withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
                .withArgument(GeneralServerFlag.COMMAND_TIMEOUT, "60"));
        service.start();

        Thread.sleep(20);

        if (service != null)
        {
            System.out.println(service.getUrl() + "\t");
            service.stop();

        }
    }
}
