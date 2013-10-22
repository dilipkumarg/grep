package com.imaginea.dilip.grep.searcher.custom;

public enum CharType {
	NormalChar('n') {

		@Override
		public int isCharContains(char[] searchKey, CharType[] searchStr,
				char[] targetStr, char curChar, int charPos, int targetPos) {
			int res = -1;
			if (curChar == targetStr[targetPos]) {
				res = targetPos;
			}
			return res;
		}

	},
	StarChar('*') {

		@Override
		public int isCharContains(char[] searchKey, CharType[] searchStr,
				char[] targetStr, char curChar, int charPos, int targetPos) {
			int res = targetPos;
			// evaluating only if it is 2nd or(and) next char
			if (charPos > 0) {
				
				//while()
			}
			return res;
		}

	},
	DotChar('.') {

		@Override
		public int isCharContains(char[] searchKey, CharType[] searchStr,
				char[] targetStr, char curChar, int charPos, int targetPos) {
			// TODO Auto-generated method stub
			return 0;
		}

	},
	PlusChar('+') {

		@Override
		public int isCharContains(char[] searchKey, CharType[] searchStr,
				char[] targetStr, char curChar, int charPos, int targetPos) {
			// TODO Auto-generated method stub
			return 0;
		}

	},
	QuestionChar('?') {

		@Override
		public int isCharContains(char[] searchKey, CharType[] searchStr,
				char[] targetStr, char curChar, int charPos, int targetPos) {
			// TODO Auto-generated method stub
			return 0;
		}

	};
	private final char type;

	private CharType(char type) {
		this.type = type;
	}

	public abstract int isCharContains(char[] searchKey, CharType[] searchStr,
			char[] targetStr, char curChar, int charPos, int targetPos);

	public char getType() {
		return type;
	}
}
