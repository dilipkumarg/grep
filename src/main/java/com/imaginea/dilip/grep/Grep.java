package com.imaginea.dilip.grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imaginea.dilip.grep.searcher.TextSearcher;
import com.imaginea.dilip.grep.searcher.TextSearcherFactory;
import com.imaginea.dilip.grep.util.TextFileReader;

public class Grep {
	private String searchKey = null;
	private String fileName;
	private boolean isCustomImpl;

	public Grep() {
		fileName = Grep.class.getResource("/sample.txt").getPath();
		isCustomImpl = false;
	}

	public static void main(String[] args) throws IOException {
		Grep grep = new Grep();
		grep.buildArgs(args);
		if (grep.isSearchKeyNotNull()) {
			grep.doSearch();

		} else {
			throw new IllegalArgumentException("Search key must be passed");
		}

	}

	private void doSearch() throws IOException {
		BufferedReader br = null;
		try {
			br = getBufferedReader(fileName);
			long startTime = System.currentTimeMillis();
			System.out.println("Searching started: "
					+ new Date(startTime).toString());
			printSearchResults(br, searchKey, isCustomImpl);
			long endTime = System.currentTimeMillis();
			System.out.println("Searching completed: "
					+ new Date(endTime).toString());
			System.out.println("Search Duration : " + (endTime - startTime)
					+ "ms");
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	private boolean isSearchKeyNotNull() {
		return searchKey != null;
	}

	private void buildArgs(String[] args) {
		StringBuilder specialArgs = new StringBuilder();
		List<String> params = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				specialArgs.append(args[i]);
			} else {
				params.add(args[i]);
			}
		}
		buildSpecialArgs(specialArgs.toString());
		buildParams(params);
	}

	private void buildSpecialArgs(String specialArgs) {
		if (specialArgs.contains("c")) {
			isCustomImpl = true;
		}
	}

	private void buildParams(List<String> params) {
		int size = params.size();
		if (size > 0) {
			searchKey = params.get(0);
			if (size > 1) {
				fileName = params.get(1);
			}
		}
	}

	private BufferedReader getBufferedReader(String filePath)
			throws IOException {

		return TextFileReader.readFileAsBuff(new File(filePath)
				.getAbsolutePath());
	}

	private void printSearchResults(BufferedReader br, String searchKey,
			boolean isCustomImpl) throws IOException {
		String curLine = "";
		String implType = (isCustomImpl ? "c" : "j");
		TextSearcher ts = TextSearcherFactory.getTextSearcher(implType,
				searchKey);
		while ((curLine = br.readLine()) != null) {
			if (ts.isStringContains(curLine)) {
				System.out.println(curLine);
			}
		}
	}

}
