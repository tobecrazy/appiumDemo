package main.java.com.dbyl.appiumCore.tests.designPattern.Day2.customer;

import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.factory.BaseFactory;
import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.factory.FactoryProducer;
import main.java.com.dbyl.appiumCore.tests.designPattern.Day2.factory.IPhoneFactory;

/**
 * 
 * @author young
 *
 */
public class Buyer {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		BaseFactory producer = new FactoryProducer().getFactory(IPhoneFactory.class.getName());
		producer.generateCPU().showCPUInfo();
		producer.generateScreen().showScreenInfo();
	}

}
