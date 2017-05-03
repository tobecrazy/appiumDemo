package main.java.com.dbyl.appiumCore.tests.designPattern.Day1.product;

/**
 * 
 * @author young
 *
 */
public class Computer implements IProduct {
	String productName = "Thinkpad P50";

	public void BeSold() {
		System.out.println("I'm a " + productName + ", buy me");
	}

}
