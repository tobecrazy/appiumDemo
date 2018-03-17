package main.java.com.dbyl.appiumCore.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The Class DateUtils.
 * 
 * @author Young
 * @since 2018/03/12
 * @version V1.0
 */
public class DateUtils {
	private static Calendar cal=Calendar.getInstance();
	public static String getCurrentDate()
	{
		SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date=new Date();
		return sfd.format(date);
	}

	public static void main(String [] args)
	{
		 System.out.println(getCurrentDate());
	}
}
