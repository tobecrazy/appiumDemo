package com.dbyl.core;

import java.io.IOException;
import java.io.InputStream;

public class Test {

	public static void main(String [] args)
	{
		Runtime rt=Runtime.getRuntime();
		
		   try {     
		        Process process = rt.exec("cmd.exe /C appium");   
		        InputStream in = process.getInputStream();     
		        while (in.read() != -1) {   
		            System.out.print(in.read());   
		        }   
		        in.close();   
		       // process.waitFor();   
		    } catch (Exception e) {            
		        e.printStackTrace();   
		    }   
	}
}
