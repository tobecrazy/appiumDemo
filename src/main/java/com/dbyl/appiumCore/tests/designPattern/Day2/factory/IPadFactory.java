package main.java.com.dbyl.appiumCore.tests.designPattern.Day2.factory;

import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.products.A10;
import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.products.ICPU;
import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.products.IScreen;
import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.products.RetinaScreen;

/**
 * IPad 工厂
 * 
 * @author young
 *
 */
public class IPadFactory extends BaseFactory {

	@Override
	public ICPU generateCPU() {
		return new A10();

	}

	@Override
	public IScreen generateScreen() {
		return new RetinaScreen();

	}

}
