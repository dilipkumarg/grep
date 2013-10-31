package com.imaginea.dilip.grep.entities;

import java.util.Stack;

public class Fragment {
	private State first;
	private Stack<State> stack;

	public Fragment() {
		this.first = null;
		this.stack = new Stack<State>();
	}

	public boolean isFirstNull() {
		return (this.first == null);
	}

	public State getFirst() {
		return first;
	}

	public void setFirst(State first) {
		this.first = first;
	}

	public State getValidFirst() {
		return (this.first != null) ? this.first : this.stack.pop();
	}

	public Stack<State> getStack() {
		return stack;
	}

	public void setStack(Stack<State> stack) {
		this.stack = stack;
	}
}
