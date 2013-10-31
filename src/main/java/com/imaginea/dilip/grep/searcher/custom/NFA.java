package com.imaginea.dilip.grep.searcher.custom;

import com.imaginea.dilip.grep.entities.State;
import com.imaginea.dilip.grep.util.PostfixConverter;

public class NFA {
	private final State first;
	private final NFAMatcher nfaMatcher;

	public NFA(char[] regex) {
		this.first = new NFAConverter().fromPostFixExpressoin(PostfixConverter
				.infixToPostfix(regex, 0, regex.length));
		this.nfaMatcher = new NFAMatcher(this.first);
	}

	/**
	 * returns the reference of first state in NFA.
	 * 
	 * @return
	 */
	public State getFirst() {
		return first;
	}

	/**
	 * returns true if string matched or else returns false.
	 * 
	 * @param targetStr
	 * @return
	 */
	public boolean isContains(char[] targetStr) {
		return nfaMatcher.isContains(targetStr);
	}

	public static void main(String[] args) {
		NFA nfa = new NFA(".ramati".toCharArray());
		System.out.println(nfa.isContains("ppramati".toCharArray()));
		// System.out.println(nfa.isMatch("prmati".toCharArray()));
	}

}
