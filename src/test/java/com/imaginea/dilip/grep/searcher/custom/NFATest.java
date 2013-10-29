package com.imaginea.dilip.grep.searcher.custom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class NFATest {

	NFA nfa;

	@Before
	public void setUp() throws Exception {
		nfa = new NFA("pramati".toCharArray());
	}

	@Test
	public void testSimpleCase() {
		State current = nfa.getFirst();
		char[] expected = "pramati".toCharArray();
		int i = 0;
		while (current != null) {
			if (i < expected.length) {
				assertEquals(expected[i++], current.getCh());
				current = current.getOut();
			} else {
				fail("current having more cases, than actual");
			}
		}
	}

}
