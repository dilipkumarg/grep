package com.imaginea.dilip.grep.entities;

public class Arguments {
	private final String searchKey;
	private final String filePath;
	private final String implType;
	private final boolean isCaseInSensitive;

	public Arguments(String searchKey, String filePath, String implType,
			boolean isCaseInSensitive) {
		this.searchKey = searchKey;
		this.filePath = filePath;
		this.implType = implType;
		this.isCaseInSensitive = isCaseInSensitive;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public boolean isAllParamPassed() {
		return searchKey != null && filePath != null;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getImplType() {
		return implType;
	}

	public boolean isCaseInSensitive() {
		return isCaseInSensitive;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Search Key:" + searchKey);
		sb.append("\nFile Path:" + filePath);
		sb.append("\nImplementation Type:" + implType);
		sb.append("\n CaseInSensitive:" + isCaseInSensitive);
		return sb.toString();
	}
}
