package main.java.com.dbyl.appiumCore.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

public class XMLUtils {

	public static void main(String[] args) throws FileNotFoundException {
		Yaml yaml = new Yaml();
		String path = System.getProperty("user.dir");
		System.out.println(path + "/yaml.yml" + "   \n>>>");
		File file = new File(path + "/yaml.yml");
		FileInputStream fileInputStream = new FileInputStream(file);
		Map map = yaml.loadAs(fileInputStream, Map.class);// 装载的对象，这里使用Map, 当然也可使用自己写的对象
		printMap(map, map.size());

	}

	public static void printMap(Map map, int count) {
		Set set = map.keySet();
		for (Object key : set) {

			Object value = map.get(key);

			for (int i = 0; i < count; i++) {
				System.out.print("    ");
			}

			if (value instanceof Map) {

				System.out.println(key + ":");
				printMap((Map) value, count + 1);// 嵌套
			} else if (value instanceof List) {

				System.out.println(key + ":");
				for (Object obj : (List) value) {
					for (int i = 0; i < count; i++) {
						System.out.print("    ");
					}
					System.out.println("    - " + obj.toString());
				}
			} else {

				System.out.println(key + ": " + value);
			}
		}
	}
}
