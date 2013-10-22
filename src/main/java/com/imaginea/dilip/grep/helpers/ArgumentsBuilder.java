package com.imaginea.dilip.grep.helpers;

import java.util.ArrayList;
import java.util.List;

import com.imaginea.dilip.grep.entities.Arguments;

public class ArgumentsBuilder {
	private String searchKey;
	private String filePath;
	private String implType;
	private boolean isCaseInSensitive;

	public ArgumentsBuilder() {
		searchKey = null;
		filePath = null;
		implType = "j";
		isCaseInSensitive = false;
	}

	public Arguments buildArgs(String[] args) {
		StringBuilder specialArgs = new StringBuilder();
		List<String> params = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				specialArgs.append(args[i].replaceAll("-", ""));
			} else {
				params.add(args[i]);
			}
		}
		buildSpecialArgs(specialArgs.toString());
		buildParams(params);
		return new Arguments(searchKey, filePath, implType, isCaseInSensitive);
	}

	private void buildSpecialArgs(String specialArgs) {
		if (specialArgs.contains("c")) {
			implType = "c";
		}
		if (specialArgs.contains("i")) {
			isCaseInSensitive = true;
		}
	}

	private void buildParams(List<String> params) {
		int size = params.size();
		if (size > 1) {
			searchKey = params.get(0);
			filePath = params.get(1);
		}
	}
}
