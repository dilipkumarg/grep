package com.imaginea.dilip.grep.searcher.custom;

public class NFAMatcher {
	private final State first;

	public NFAMatcher(State first) {
		this.first = first;
	}

	/**
	 * returns true if string matched or else returns false.
	 * 
	 * @param targetStr
	 * @return
	 */
	public boolean isContains(char[] targetStr) {
		boolean res = false;
		State current;
		int max = targetStr.length;
		for (int i = 0; i < max; i++) {
			current = this.first;
			// checking if first char equal or current char is split case
			if ((current.getCh() != targetStr[i])
					&& current.getCh() != State.SPLIT
					&& current.getCh() != State.ANY) {
				// searching for the first char match
				while (++i < max && current.getCh() != targetStr[i])
					;
			}
			// checking if first char matched
			if ((i < max && current.getCh() == targetStr[i])
					|| (current.getCh() == State.SPLIT)
					|| (current.getCh() == State.ANY)) {
				res = checkFullMatch(current, targetStr, i);
				// if full match found stopping execution
				if (res) {
					break;
				}
			}
		}
		return res;
	}

	/**
	 * Checking full string matched or not
	 * 
	 * @param current
	 * @param targetStr
	 * @param cur
	 * @return
	 */
	private boolean checkFullMatch(State current, char[] targetStr, int cur) {
		// looking for the next cases for complete match
		if (current.getCh() == State.ANY || current.getCh() != State.SPLIT) {
			// if first char is not a split state means, first char matched.
			// moving ahead if first char matched.
			current = current.getOut();
			++cur;
		}
		while ((current != null) && (cur < targetStr.length)
				&& (cur = isMatch(current, targetStr, cur)) != -1) {
			if (current.getCh() == State.SPLIT && current.getOut() != null) {
				// moving step ahead if it is a wild card because we
				// already evaluating it in the inner function
				current = current.getOut();
			}
			if (current != null) {
				current = current.getOut();
			}
			++cur;
		}

		// checking if full text is matched or not
		return isFullTextMatched(current, targetStr, cur);
	}

	/**
	 * confirming whether full text matched or not
	 * 
	 * @param current
	 * @param targetStr
	 * @param offset
	 * @return
	 */
	private boolean isFullTextMatched(State current, char[] targetStr,
			int offset) {
		// if the split state in the final char. it will be accepted.
		return (isFinalCh(current) || (((offset <= targetStr.length && offset != -1) && current
				.getCh() == targetStr[offset - 1]) && current.getOut() == null));
	}

	/**
	 * This case is useful for strings ending with (*) or (?). we have to make
	 * sure that is there any character needed or not.
	 * 
	 * @param current
	 * @return
	 */
	private boolean isFinalCh(State current) {
		boolean res = true;
		// if current is null then it is final state
		if (current != null) {
			while ((current != null)
					&& (current.getCh() == State.SPLIT || current.getCh() == State.JOIN)) {
				current = current.getOut();
			}
			// if current is not null and not join, split state means it is on a
			// char case
			if (current != null) {
				res = false;
			}
		}
		return res;
	}

	private int isMatch(State current, char[] targetStr, int offset) {
		int res = -1;
		if (current.getCh() == State.SPLIT) {
			// checking alternate branch
			State altLink = current.getOut1();
			// moving current to next valid state
			current = getNextValidState(current);
			if (altLink != null && altLink.getCh() != State.SPLIT) {
				offset = isSpecialCaseMatched(altLink, current, targetStr,
						offset);
			}
		}
		// checking finally if the next char is there
		if (current == null
				|| ((offset < targetStr.length)
						&& current.getCh() == targetStr[offset] || current
						.getCh() == State.ANY)) {
			res = offset;
		}
		return res;
	}

	private int isSpecialCaseMatched(State altLink, State current,
			char[] targetStr, int offset) {
		while (((altLink.getCh() == targetStr[offset]) || (altLink.getCh() == State.ANY && (current == null || ((offset < targetStr.length) && (targetStr[offset] != current
				.getCh()))))) && (++offset < targetStr.length)) {
			// checking for join state. if it is there then stopping loop.
			// this case is useful for '?' char
			if (altLink.getOut() != null
					&& altLink.getOut().getCh() == State.JOIN) {
				break;
			}
		}
		return offset;
	}

	private State getNextValidState(State current) {
		current = current.getOut();
		if (current != null && current.getCh() == State.JOIN) {
			current = current.getOut();
		}
		return current;
	}

}
