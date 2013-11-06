package com.imaginea.dilip.grep.entities;

public class Arguments {
	private final String searchKey;
	private final String filePath;
	private final String implType;
	private final String flags;

	public Arguments(String searchKey, String filePath, String implType,
			String flags) {
		this.searchKey = searchKey;
		this.filePath = filePath;
		this.implType = implType;
		this.flags = flags;

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
		return flags.contains("i");
	}

	public boolean isDebugMode() {
		return flags.contains("d");
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Search Key:" + searchKey);
		sb.append("\nFile Path:" + filePath);
		sb.append("\nImplementation Type:" + implType);
		sb.append("\n CaseInSensitive:" + isCaseInSensitive());
		sb.append("\n IsDebugMode:" + isDebugMode());
		return sb.toString();
	}

}
