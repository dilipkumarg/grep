package com.imaginea.dilip.grep.searcher;

import java.util.regex.Pattern;

public class SearcherJavaImpl implements TextSearcher {
	private Pattern pattern;

	public SearcherJavaImpl(String searchKey, boolean caseInSensitive) {
		if (caseInSensitive) {
			this.pattern = Pattern.compile(searchKey, Pattern.CASE_INSENSITIVE);
		} else {
			this.pattern = Pattern.compile(searchKey);
		}
	}

	public boolean isStringContains(String sString) {
		return pattern.matcher(sString).find();
	}
}
