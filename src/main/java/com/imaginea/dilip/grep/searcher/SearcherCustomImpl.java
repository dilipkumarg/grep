package com.imaginea.dilip.grep.searcher;

public class SearcherCustomImpl implements TextSearcher {
	private final char[] searchKey;
	private final boolean isCaseInSensitive;

	public SearcherCustomImpl(String searchKey, boolean caseSensitive) {
		this.isCaseInSensitive = caseSensitive;
		if (searchKey.length() < 1) {
			throw new IllegalArgumentException("Search Key should not be empty");
		}
		if (isCaseInSensitive) {
			// converting to lower case if it is a case insensitive search
			searchKey = searchKey.toLowerCase();
		}
		this.searchKey = searchKey.toCharArray();
	}

	public boolean isStringContains(String sString) {
		if (isCaseInSensitive) {
			sString = sString.toLowerCase();
		}
		return isContains(sString.toCharArray());
	}

	private boolean isContains(char[] targetString) {
		boolean isContains = false;
		int targetStrLen = targetString.length;

		char firstChar = searchKey[0];
		if (searchKey.length <= targetStrLen) {
			for (int i = 0; i < targetStrLen; i++) {
				// searching for the first char match
				if (targetString[i] != firstChar) {
					while (++i < targetStrLen && targetString[i] != firstChar)
						;
				}
				// if found first char
				if (i <= targetStrLen) {
					// checking remaining elements
					int k = 0;
					while (++k < searchKey.length && (i + k) < targetStrLen
							&& searchKey[k] == targetString[i + k])
						;
					// checking if total string found or not
					if (k == searchKey.length) {
						isContains = true;
					}
				}
			}

		}
		return isContains;
	}

}
