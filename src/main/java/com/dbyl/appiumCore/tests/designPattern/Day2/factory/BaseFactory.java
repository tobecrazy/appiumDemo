package main.java.com.dbyl.appiumCore.tests.designPattern.Day2.factory;

import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.products.ICPU;
import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.products.IScreen;

/**
 * 抽象工厂
 * @author young
 *
 */
public abstract class BaseFactory {
	public abstract ICPU generateCPU();
	public abstract IScreen generateScreen();

}
