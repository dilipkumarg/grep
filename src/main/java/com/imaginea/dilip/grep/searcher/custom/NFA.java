package com.imaginea.dilip.grep.searcher.custom;

import java.util.Stack;

import com.imaginea.dilip.grep.util.PostfixConverter;

public class NFA {
	private State first;

	public NFA() {
		this.first = null;
	}

	public State getFirst() {
		return first;
	}

	public void postToNfa(char[] regex) {
		Stack<State> stack = new Stack<State>();
		char ch;
		for (int i = 0; i < regex.length; i++) {
			ch = regex[i];
			switch (ch) {
			case '*':
				stack.push(doStarOperation(stack.pop()));
				break;
			case '+':
				stack.push(doPlus(stack.pop()));
				break;
			case '?':
				stack.push(doAnyOne(stack.pop()));
				break;

			case '$':
				State e2 = stack.pop();
				State e1 = stack.pop();
				stack.push(doConcat(e1, e2));
				break;

			default:
				State s = new State(ch);
				stack.push(s);
				break;
			}
		}
	}

	private State doAnyOne(State q1) {
		// adding two new states. and making current state as optional
		State q2 = new State(State.SPLIT);
		State q3 = new State(State.JOIN);
		q2.setOut1(q1);
		q1.setOut(q3);
		q3.setOut(q3);
		return q2;
	}

	private State doPlus(State s1) {
		State s2 = new State(State.SPLIT);
		s1.setOut(s2);
		s2.setOut1(s1);
		return s1;
	}

	private State doStarOperation(State s1) {
		State s2 = new State(State.SPLIT);
		s2.setOut1(s1);
		s1.setOut(s2);
		return s2;
	}

	private State doConcat(State s1, State s2) {
		if (first == null) {
			this.first = s1;
		}
		while (s1.getOut() != null) {
			s1 = s1.getOut();
		}
		s1.setOut(s2);
		return s2;
	}

	public boolean isContains(char[] targetStr) {
		boolean res = false;
		State current;
		int max = targetStr.length;
		for (int i = 0; i < max; i++) {
			current = this.first;
			// checking if first char equal or current char is split case
			if ((current.getCh() != targetStr[i])
					&& current.getCh() != State.SPLIT) {
				// searching for the first char match
				while (++i < max && current.getCh() != targetStr[i])
					;
			}
			// checking if first char matched
			if ((i < max && current.getCh() == targetStr[i])
					|| (current.getCh() == State.SPLIT)) {
				// looking for the next cases for complete match
				int cur = i;
				if (current.getCh() != State.SPLIT) {
					// moving ahead if first char matched
					current = current.getOut();
					++cur;
				}
				while ((current != null) && (cur < targetStr.length)
						&& (cur = isMatch(current, targetStr, cur)) != -1) {
					if (current.getCh() == State.SPLIT
							&& current.getOut() != null) {
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
				res = isFullTextMatched(current, targetStr, cur);
				// if full match found stopping execution
				if (res) {
					break;
				}
			}
		}
		return res;
	}

	private boolean isFullTextMatched(State current, char[] targetStr,
			int offset) {
		// if the split state in the final char. it will be accepted.
		return (isFinalCh(current) || (((offset <= targetStr.length && offset != -1) && current
				.getCh() == targetStr[offset - 1]) && current.getOut() == null));
	}

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
						.getCh() == '.')) {
			res = offset;
		}
		return res;
	}

	private int isSpecialCaseMatched(State altLink, State current,
			char[] targetStr, int offset) {
		while (((altLink.getCh() == targetStr[offset]) || (altLink.getCh() == '.' && (current == null || ((offset < targetStr.length) && (targetStr[offset] != current
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

	public static void main(String[] args) {
		NFA nfa = new NFA();
		char[] infixStr = "p*ramati".toCharArray();
		nfa.postToNfa(PostfixConverter.infixToPostfix(infixStr, 0,
				infixStr.length));
		System.out.println(nfa.isContains("ppppppppppppramati".toCharArray()));
		// System.out.println(nfa.isMatch("prmati".toCharArray()));
	}

}
