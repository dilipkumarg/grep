package com.imaginea.dilip.grep.searcher.custom;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.imaginea.dilip.grep.util.PostfixConverter;
import com.imaginea.dilip.grep.util.TextFileReader;

public class NFARegExTest {

	private final String REGEX_FILENAME = "/testcases_regexcases.txt";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSimpleCase() throws IOException {
		BufferedReader br = null;
		try {
			br = TextFileReader.readFileAsBuff(this.getClass()
					.getResource(REGEX_FILENAME).getPath());
			String currLine = "";
			String[] tokens;
			char[] infixStr;
			boolean response;
			while ((currLine = br.readLine()) != null) {
				tokens = currLine.split("::");
				if (tokens.length >= 3) {
					NFA nfa = new NFA();
					infixStr = tokens[0].toCharArray();
					nfa.postToNfa(PostfixConverter.infixToPostfix(infixStr, 0,
							infixStr.length));
					response = nfa.isContains(tokens[1].toCharArray());
					assertEquals("Fails at->" + tokens[0] + "::" + tokens[1]
							+ "::" + tokens[2] + "-> But return value is:"
							+ response, Boolean.valueOf(tokens[2]), response);
				}
			}
		} finally {
			if (br != null)
				br.close();
		}
	}

}
