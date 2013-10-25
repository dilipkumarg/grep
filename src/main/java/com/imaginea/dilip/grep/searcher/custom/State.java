package com.imaginea.dilip.grep.searcher.custom;

public class State {

	public static char JOIN = 256;
	public static char SPLIT = 257;

	private final char ch;
	private State out;
	private State out1;
	private int lastLink;

	public State(char ch) {
		this.ch = ch;
		this.out = null;
		this.out1 = null;
		this.lastLink = 0;
	}

	public State getOut() {
		return out;
	}

	public void setOut(State out) {
		this.out = out;
	}

	public State getOut1() {
		return out1;
	}

	public void setOut1(State out1) {
		this.out1 = out1;
	}

	public int getLastLink() {
		return lastLink;
	}

	public void setLastLink(int lastLink) {
		this.lastLink = lastLink;
	}

	public int getCh() {
		return ch;
	}
}
