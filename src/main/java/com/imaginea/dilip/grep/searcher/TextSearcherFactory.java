package com.imaginea.dilip.grep.searcher;

import java.util.HashMap;
import java.util.Map;

public class TextSearcherFactory {

	private static Map<String, TextSearcherType> typeMap = new HashMap<String, TextSearcherType>();
	static {
		typeMap.put("j", TextSearcherType.JAVA);
		typeMap.put("c", TextSearcherType.CUSTOM);
	}

	/**
	 * It will returns the default text searcher implementation. In this case
	 * default searcher is java native implementor.
	 * 
	 * @param searchKey
	 * @return
	 */
	public static TextSearcher getTextSearcher(String searchKey) {
		return getTextSearcher("j", searchKey, false);
	}

	/**
	 * It will returns the text searcher based on the type prefix.<br/>
	 * j - java implementor <br/>
	 * c - custom implementor
	 * 
	 * @param type
	 * @param searchKey
	 * @return
	 */
	public static TextSearcher getTextSearcher(String type, String searchKey,
			boolean caseInSensitive) {
		TextSearcher ts = null;
		if (type != null) {
			try {
				ts = typeMap.get(type).getTextSearcher(searchKey,
						caseInSensitive);
			} catch (NullPointerException e) {
				throw new IllegalArgumentException(
						"Invalid searcher type. Type should be java or custom");
			}

		} else {
			throw new IllegalArgumentException("Type should not be null.");
		}
		return ts;
	}
}
