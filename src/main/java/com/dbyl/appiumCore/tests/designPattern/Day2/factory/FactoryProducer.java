package main.java.com.dbyl.appiumCore.tests.designPattern.Day2.factory;

/**
 * 获取具体工厂
 * @author young
 *
 */
public class FactoryProducer {
	public BaseFactory getFactory(String product)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> c = Class.forName(product);
		return (BaseFactory) c.newInstance();
	}

}
