package main.java.com.dbyl.appiumCore;

import android.os.RemoteException;
<<<<<<< HEAD
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
=======

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

public class UIAutomatorDemo {

	public static void main(String[] args) throws UiObjectNotFoundException, RemoteException {
		UiDevice.getInstance().pressHome();
		  

        
	}
>>>>>>> 98091ccf0dd34d76a190c469feb44daa9254f580

}
