package com.imaginea.dilip.grep.searcher.custom;

import com.imaginea.dilip.grep.entities.Fragment;
import com.imaginea.dilip.grep.entities.State;

public enum CharType {
	/**
	 * It will implement zero or more elements. In this case it will pops one
	 * state from the stack, and creates one new SPLIT state. and State(ch)
	 * added to the SPLIT state's out1. <br/>
	 * SPLIT -> out1 = state(ch); state(ch) -> out = SPLIT.
	 */
	STAR {

		@Override
		public Fragment doProcess(char ch, Fragment frag) {
			State s1 = frag.getStack().pop();
			State s2 = new State(State.SPLIT);
			s2.setOut1(s1);
			s1.setOut(s2);
			frag.getStack().push(s2);
			return frag;
		}

	},
	/**
	 * It will implement the 1 or more elements. In this case it will pops one
	 * state from stack and set its out to SPLIT state.<br/>
	 * State(ch) -> out = SPLIT; SPLIT -> out1 = State(ch);
	 */
	PLUS {

		@Override
		public Fragment doProcess(char ch, Fragment frag) {
			State s1 = frag.getStack().pop();
			State s2 = new State(State.SPLIT);
			s1.setOut(s2);
			s2.setOut1(s1);
			frag.getStack().push(s1);
			return frag;
		}

	},
	/**
	 * It will implement the 0 or 1 case. In this case it will create new States
	 * one is SPLIT state and other is JOIN state, and pops one state from the
	 * stack and adds in between of them.
	 * 
	 * SPLIT->out1 = new State(ch) && new State->out, SPLIT->out = JOIN
	 */
	QUESTION {

		@Override
		public Fragment doProcess(char ch, Fragment frag) {
			State q1 = frag.getStack().pop();
			// adding two new states. and making current state as optional
			State q2 = new State(State.SPLIT);
			State q3 = new State(State.JOIN);
			q2.setOut1(q1);
			q1.setOut(q3);
			q3.setOut(q3);
			frag.getStack().push(q2);
			return frag;
		}

	},
	/**
	 * It will implement the any case match. In this case it simply create new
	 * State with any char and adds to the stack.
	 */
	ANY {

		@Override
		public Fragment doProcess(char ch, Fragment frag) {
			State any = new State(State.ANY);
			frag.getStack().push(any);
			return frag;
		}

	},
	/**
	 * It will pops two chars from the stack and concatenates it.
	 */
	CONCAT {
		@Override
		public Fragment doProcess(char ch, Fragment frag) {
			State s2 = frag.getStack().pop();
			State s1 = frag.getStack().pop();
			if (frag.isFirstNull()) {
				frag.setFirst(s1);
			}
			while (s1.getOut() != null) {
				s1 = s1.getOut();
			}
			s1.setOut(s2);
			frag.getStack().push(s2);
			return frag;
		}
	},
	/**
	 * It will implement the normal char operation. In this case it will simple
	 * create new state with given char and push to the stack.
	 */
	DEFAULT {

		@Override
		public Fragment doProcess(char ch, Fragment frag) {
			State s = new State(ch);
			frag.getStack().push(s);
			return frag;
		}

	};

	public abstract Fragment doProcess(char ch, Fragment frag);
}
