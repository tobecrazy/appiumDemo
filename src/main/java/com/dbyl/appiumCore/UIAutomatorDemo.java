package main.java.com.dbyl.appiumCore;

import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UIAutomatorDemo extends UiAutomatorTestCase
{

    public void TestDevice()
    {
        Demo demo = new Demo();
        demo.press();

    }

    public static void main(String[] args) throws UiObjectNotFoundException, RemoteException
    {

        new UiAutomatorHelper("Demo", "main.java.com.dbyl.appiumCore.UIAutomatorDemo", "UIAutomatorDemo", "1");

    }

}
