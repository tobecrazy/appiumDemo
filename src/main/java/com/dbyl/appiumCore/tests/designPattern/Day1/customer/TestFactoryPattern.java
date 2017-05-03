package main.java.com.dbyl.appiumCore.tests.designPattern.Day1.customer;

import org.testng.annotations.Test;

import main.java.com.dbyl.appiumCore.tests.designPattern.Day1.factory.FutoconnFactory;

public class TestFactoryPattern {
	
	@Test
	public void TestFactory()
	{
		FutoconnFactory factory=new FutoconnFactory();
		factory.generate("CellPhone").BeSold();
		factory.generate("Computer").BeSold();
	}

}
