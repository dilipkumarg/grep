package com.imaginea.dilip.grep.searcher;

public enum TextSearcherType {
	JAVA("j") {

		@Override
		public TextSearcher getTextSearcher(String searchKey,
				boolean caseInSensitive) {
			return new SearcherJavaImpl(searchKey, caseInSensitive);
		}
	},
	CUSTOM("c") {

		@Override
		public TextSearcher getTextSearcher(String searchKey,
				boolean caseInSensitive) {
			return new SearcherCustomImpl(searchKey, caseInSensitive);
		}

	};
	private String type;

	private TextSearcherType(String type) {
		this.type = type;
	}

	public abstract TextSearcher getTextSearcher(String searchKey,
			boolean caseInSensitive);

	public String getType() {
		return type;
	}
}
