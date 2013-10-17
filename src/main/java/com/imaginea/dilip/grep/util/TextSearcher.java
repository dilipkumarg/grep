package com.imaginea.dilip.grep.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSearcher {
	private Pattern pattern;

	public TextSearcher(String expression) {
		this.pattern = Pattern.compile(expression);
	}

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

	public boolean isStringContains(String sString) {
		Matcher matcher = pattern.matcher(sString);
		return matcher.find();
	}
}
