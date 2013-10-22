package com.imaginea.dilip.grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.imaginea.dilip.grep.entities.Arguments;
import com.imaginea.dilip.grep.helpers.ArgumentsBuilder;
import com.imaginea.dilip.grep.searcher.TextSearcher;
import com.imaginea.dilip.grep.searcher.TextSearcherFactory;
import com.imaginea.dilip.grep.util.TextFileReader;

public class Grep {

	public Grep() {

	}

	public static void main(String[] args) throws IOException {
		Grep grep = new Grep();
		Arguments arguments = new ArgumentsBuilder().buildArgs(args);
		if (arguments.isAllParamPassed()) {
			grep.doSearch(arguments);
		} else {
			throw new IllegalArgumentException(
					"Search key and File Name must be passed");
		}

	}

	private void doSearch(Arguments args) throws IOException {
		BufferedReader br = null;
		try {
			br = getBufferedReader(args.getFilePath());
			long startTime = System.currentTimeMillis();
			System.out.println("Searching started: "
					+ new Date(startTime).toString());
			printSearchResults(br, args);
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

	private BufferedReader getBufferedReader(String filePath)
			throws IOException {

		return TextFileReader.readFileAsBuff(new File(filePath)
				.getAbsolutePath());
	}

	private void printSearchResults(BufferedReader br, Arguments args)
			throws IOException {
		String curLine = "";
		TextSearcher ts = TextSearcherFactory.getTextSearcher(
				args.getImplType(), args.getSearchKey(),
				args.isCaseInSensitive());
		while ((curLine = br.readLine()) != null) {
			if (ts.isStringContains(curLine)) {
				System.out.println(curLine);
			}
		}
	}

}
