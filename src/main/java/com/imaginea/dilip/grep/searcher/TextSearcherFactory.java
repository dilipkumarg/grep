package com.imaginea.dilip.grep.searcher;

public class TextSearcherFactory {
	/**
	 * It will returns the default text searcher implementation. In this case
	 * default searcher is java native implementor.
	 * 
	 * @param searchKey
	 * @return
	 */
	public static TextSearcher getTextSearcher(String searchKey) {
		return getTextSearcher("j", searchKey);
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
	public static TextSearcher getTextSearcher(String type, String searchKey) {
		TextSearcher ts = null;
		if (type != null) {
			if (type.equalsIgnoreCase("j")) {
				ts = new SearcherJavaImpl(searchKey);
			} else if (type.equalsIgnoreCase("c")) {
				ts = new SeacherCustomImpl(searchKey);
			} else {
				throw new IllegalArgumentException(
						"Invalid searcher type. Type should be java or custom");
			}
		} else {
			throw new IllegalArgumentException("Type should not be null.");
		}
		return ts;
	}
}
