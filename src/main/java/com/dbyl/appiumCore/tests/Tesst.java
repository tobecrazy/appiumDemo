package main.java.com.dbyl.appiumCore.tests;
 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tesst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	       Calendar calendar = Calendar.getInstance();
	        Date date = calendar.getTime();
	    
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	      System.out.print(simpleDateFormat.format(date));
	}

}
