package com.imaginea.dilip.grep.searcher.util;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Test;

import com.imaginea.dilip.grep.util.PostfixConverter;
import com.imaginea.dilip.grep.util.TextFileReader;

public class PostfixConverterTest {
	private final String TEST_FILE = "/testcases_postfixconverter.txt";

	@Test
	public void test() throws IOException {
		BufferedReader br = null;
		try {
			br = TextFileReader.readFileAsBuff(this.getClass()
					.getResource(TEST_FILE).getPath());
			String currLine = "";
			String[] tokens;
			String response;
			while ((currLine = br.readLine()) != null) {
				tokens = currLine.split("::");
				if (tokens.length >= 2) {
					char[] infixStr = tokens[0].toCharArray();
					response = new String(PostfixConverter.infixToPostfix(
							infixStr, 0, infixStr.length));
					assertEquals("Fails at->" + tokens[0] + "::" + tokens[1]
							+ "-> return value is:" + response, tokens[1],
							response);
				}
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

}
