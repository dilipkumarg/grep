package com.imaginea.dilip.grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.imaginea.dilip.grep.util.TextFileReader;
import com.imaginea.dilip.grep.util.TextSearcher;

public class Grep {
	public static void main(String[] args) throws IOException {
		Grep grep = new Grep();
		if (args.length > 0) {
			String searchKey = args[0];
			long startTime = System.currentTimeMillis();
			System.out.println("File Searching started: "
					+ new Date(startTime).toString());
			BufferedReader br = null;
			try {
				br = grep.getBufferedReader(args);
				grep.printSearchResults(br, searchKey);
				long endTime = System.currentTimeMillis();
				System.out.println("File searching completed: "
						+ new Date(endTime).toString());
				System.out.println("Search Duration : " + (endTime - startTime)
						+ "ms");
			} finally {
				if (br != null) {
					br.close();
				}
			}
		}

	}

	private BufferedReader getBufferedReader(String[] args) throws IOException {
		BufferedReader br = null;
		if (args.length > 1) {
			br = TextFileReader.readFileAsBuff(new File(args[1])
					.getAbsolutePath());
		} else {
			br = TextFileReader.readFileAsBuff(Grep.class.getResource(
					"/sample.txt").getPath());
		}
		return br;
	}

	private void printSearchResults(BufferedReader br, String searchKey)
			throws IOException {
		String curLine = "";
		TextSearcher ts = new TextSearcher(searchKey);
		while ((curLine = br.readLine()) != null) {
			if (ts.isStringContains(curLine)) {
				System.out.println(curLine);
			}
		}
	}

}
