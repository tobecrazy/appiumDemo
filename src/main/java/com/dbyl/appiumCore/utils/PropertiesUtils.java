package main.java.com.dbyl.appiumCore.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @since 2016-03-09
 * @author young
 *
 */
public class PropertiesUtils {
	private static Properties property;
	private static String dir = System.getProperty("user.dir");

	/**
	 * @author young
	 * @param filePath
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getProperties(String filePath, String key) throws IOException {
		property = new Properties();
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);
		property.load(in);
		return property.getProperty(key);
	}

	/**
	 * @author young
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getProperties(String key) throws IOException {
		String file = dir + "config.properties";
		return getProperties(file, key);
	}

}
