package main.java.com.dbyl.appiumCore.tests;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Demo extends UiAutomatorTestCase
{
    public void press()
    {
        UiDevice.getInstance().pressHome();
    }
}
