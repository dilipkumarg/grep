package com.imaginea.dilip.grep.searcher;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Test;

import com.imaginea.dilip.grep.util.TextFileReader;

public class SearcherCustomImplTest {
	private final String TEST_PATTERN = "java";
	private final String TEST_STRING1 = "now you are learning java";
	private final String TEST_STRING2 = "now you are learning php";
	private final String TEST_STRING3 = "now you are learning JAVA";

	private final String CASE_SENSITIVE_FILE = "/testcases_caseSensitive.txt";
	private final String CASE_IN_SENSITIVE_FILE = "/testcases_caseInsensitive.txt";

	@Test
	public void testIsStringContainsCaseSensitive() {
		SearcherCustomImpl searcher = new SearcherCustomImpl(TEST_PATTERN,
				false);
		assertTrue(searcher.isStringContains(TEST_STRING1));
		assertFalse(searcher.isStringContains(TEST_STRING3));
		assertFalse(searcher.isStringContains(TEST_STRING2));
	}

	@Test
	public void testIsStringContainsCaseInSensitive() {
		SearcherCustomImpl searcher = new SearcherCustomImpl("JAVA", true);
		assertTrue(searcher.isStringContains(TEST_STRING1));
		assertTrue(searcher.isStringContains(TEST_STRING3));
		assertFalse(searcher.isStringContains(TEST_STRING2));
	}

	@Test
	public void isStringContainsCaseSenstive() throws IOException {
		BufferedReader br = null;
		try {
			br = TextFileReader.readFileAsBuff(this.getClass()
					.getResource(CASE_SENSITIVE_FILE).getPath());
			SearcherCustomImpl searcher = new SearcherCustomImpl(TEST_PATTERN,
					false);
			String currLine = "";
			String[] tokens;
			boolean response;
			while ((currLine = br.readLine()) != null) {
				tokens = currLine.split("::");
				if (tokens.length >= 2) {
					response = searcher.isStringContains(tokens[0]);
					assertEquals("Fails at->" + tokens[0] + "::" + tokens[1]
							+ "-> return value is:" + response,
							Boolean.valueOf(tokens[1]), response);
				}
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	@Test
	public void isStringContainsCaseInSenstive() throws IOException {
		BufferedReader br = null;
		try {
			br = TextFileReader.readFileAsBuff(this.getClass()
					.getResource(CASE_IN_SENSITIVE_FILE).getPath());
			SearcherCustomImpl searcher = new SearcherCustomImpl(TEST_PATTERN,
					true);
			String currLine = "";
			String[] tokens;
			boolean response;
			while ((currLine = br.readLine()) != null) {
				tokens = currLine.split("::");
				if (tokens.length >= 2) {
					response = searcher.isStringContains(tokens[0]);
					assertEquals("Fails at->" + tokens[0] + "::" + tokens[1]
							+ "-> return value is:" + response,
							Boolean.valueOf(tokens[1]), response);
				}
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}

	}
}
