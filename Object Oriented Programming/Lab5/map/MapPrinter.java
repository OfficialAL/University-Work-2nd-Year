package map;

import java.util.Map;

public class MapPrinter {
	public static void printKeys(Map<String, String> map) {
		for (String key : map.keySet()) {
			System.out.println(key);
		}
	}

	public static void printValues(Map<String, String> map) {
		for (String value : map.values()) {
			System.out.println(value);
		}
	}

	public static void printPairs(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}