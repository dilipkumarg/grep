package com.imaginea.dilip.grep.searcher;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearcherCustomImplTest {
	private final String TEST_PATTERN = "java";
	private final String TEST_STRING1 = "now you are learning java";
	private final String TEST_STRING2 = "now you are learning php";
	private final String TEST_STRING3 = "now you are learning JAVA";


	@Test
	public void testIsStringContainsCaseSensitive() {
		SearcherCustomImpl searcher = new SearcherCustomImpl(TEST_PATTERN, false);
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
}
