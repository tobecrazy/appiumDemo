package main.java.com.dbyl.appiumCore.tests.designPattern.Day2.factory;

import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.products.AMOLEDScreen;
import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.products.ICPU;
import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.products.IScreen;
import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.products.SnapDragon;

/**
 * 
 * @author young
 *
 */
public class IPhoneFactory extends BaseFactory {

	@Override
	public ICPU generateCPU() {

		return new SnapDragon();

	}

	@Override
	public IScreen generateScreen() {

		return new AMOLEDScreen();

	}

}
