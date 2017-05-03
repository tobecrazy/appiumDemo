package main.java.com.dbyl.appiumCore.tests.designPattern.Day1.product;

/**
 * @version 1.0
 * @author young
 *
 */
public class CellPhone implements IProduct {
	public String productName = "iPhone7";

	public void BeSold() {
		System.out.println("I'm a " + productName + ", buy me");
	}

}
