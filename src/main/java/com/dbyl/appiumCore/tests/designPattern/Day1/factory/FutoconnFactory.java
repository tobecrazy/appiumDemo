package main.java.com.dbyl.appiumCore.tests.designPattern.Day1.factory;

import main.java.com.dbyl.appiumCore.tests.designPattern.Day1.product.CellPhone;
import main.java.com.dbyl.appiumCore.tests.designPattern.Day1.product.Computer;
import main.java.com.dbyl.appiumCore.tests.designPattern.Day1.product.IProduct;

public class FutoconnFactory {
	/**
	 * @author young
	 * @param productName
	 * @return
	 */
	public IProduct generate(String productName) {
		if (productName.equals("CellPhone")) {
			return new CellPhone();
		} else if (productName.equals("Computer")) {
			return new Computer();
		}
		return null;
	}

}
