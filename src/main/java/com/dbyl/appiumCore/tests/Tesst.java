package main.java.com.dbyl.appiumCore.tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

/**
 * The Class Tesst.
 */
public class Tesst {

	/**
	 * Date test.
	 */
	@Test
	public static void DateTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -60);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.print(simpleDateFormat.format(date));
	}

}
