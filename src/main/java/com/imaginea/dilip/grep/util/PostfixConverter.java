package com.imaginea.dilip.grep.util;

public class PostfixConverter {
	public static char[] infixToPostfix(char[] infixStr, int startPos,
			int endPos) {
		// char[] postFix = new char[infixStr.length];
		StringBuilder sb = new StringBuilder();
		if (endPos <= infixStr.length) {
			boolean subset = false;
			boolean subsetFirstChar = false;
			for (int i = startPos; i < endPos; i++) {
				char ch = infixStr[i];
				if (ch == '(') {
					subsetFirstChar = (i == startPos);
					int subEndPos = getSubSetPos(infixStr, ++i, endPos);
					sb.append(infixToPostfix(infixStr, i, subEndPos));
					i = subEndPos;
					sb.append('$');
					subset = true;
				} else if (i == startPos || ch == '*' || ch == '?' || ch == '+') {
					// for special chars we need to remove last concat operator.
					boolean dolChar = false;
					if (!(sb.length() == 0)
							&& sb.charAt(sb.length() - 1) == '$') {
						sb.deleteCharAt(sb.length() - 1);
						dolChar = true;
					}
					sb.append(ch);
					if (isEscapedAnyCase(infixStr, i, ch)) {
						sb.append(infixStr[++i]);
					}
					if (dolChar && !subsetFirstChar) {
						sb.append('$');
					}
					if (subset) {
						subset = false;
					}
				} else {
					sb.append(ch);
					if (isEscapedAnyCase(infixStr, i, ch)) {
						sb.append(infixStr[++i]);
					}
					sb.append('$');
					if (subset) {
						sb.append('$');
						subset = false;
					}
				}

			}
		} else {
			throw new IllegalArgumentException(
					"end position must be less than or equal to array length");
		}

		return sb.toString().toCharArray();
	}

	private static boolean isEscapedAnyCase(char[] infixStr, int pos,
			char curChar) {
		return (curChar == '\\' && (((pos + 1) < infixStr.length) && (infixStr[pos + 1] == '.')));
	}

	private static int getSubSetPos(char[] infixStr, int pos, int endPos) {
		int leftBraceCount = 1, rightBraceCount = 0;
		int subPos = pos;
		for (int i = pos; i < endPos; i++) {
			if (infixStr[i] == '(') {
				leftBraceCount++;
			} else if (infixStr[i] == ')') {
				rightBraceCount++;
			}

			if (leftBraceCount == rightBraceCount) {
				subPos = i;
				break;
			}

		}
		return subPos;
	}

	public static void main(String[] args) {
		char[] str = "(pramati)+".toCharArray();
		System.out.println(infixToPostfix(str, 0, str.length));
	}
}
