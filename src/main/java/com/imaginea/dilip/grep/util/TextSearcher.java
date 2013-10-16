package com.imaginea.dilip.grep.util;

import java.util.ArrayList;
import java.util.List;

public class TextSearcher {
	static List<String> searchString(String searchKey, String string) {
		List<String> result = new ArrayList<String>();
		String[] tokens = string.split("\n");

		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].contains(searchKey)) {
				result.add(tokens[i]);
			}
		}

		return result;
	}

	public static boolean isStringContains(String key, String sString) {
		return sString.contains(key);
	}
}
