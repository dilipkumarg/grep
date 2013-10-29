package com.imaginea.dilip.grep.searcher.custom;

import java.util.Stack;

public class NFAConverter {
	private State first;

	public NFAConverter() {
		this.first = null;
	}

	public State fromPostFixExpressoin(char[] regex) {
		Stack<State> stack = new Stack<State>();
		char ch;
		for (int i = 0; i < regex.length; i++) {
			ch = regex[i];
			switch (ch) {
			case '\\':
				if (++i < regex.length) {
					State spl = new State(regex[i]);
					stack.push(spl);
				}
				break;
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
			case '.':
				State any = new State(State.ANY);
				stack.push(any);
				break;
			default:
				State s = new State(ch);
				stack.push(s);
				break;
			}
		}
		if (first == null && !stack.empty()) {
			this.first = stack.pop();
		}
		return this.first;
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
}
