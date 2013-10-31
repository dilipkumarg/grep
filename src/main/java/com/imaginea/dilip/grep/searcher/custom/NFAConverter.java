package com.imaginea.dilip.grep.searcher.custom;

import com.imaginea.dilip.grep.entities.Fragment;
import com.imaginea.dilip.grep.entities.State;
import com.imaginea.dilip.grep.helpers.DefaultHashMap;

public class NFAConverter {
	private static DefaultHashMap<Character, CharType> charTypes = new DefaultHashMap<Character, CharType>(
			CharType.DEFAULT);
	static {
		charTypes.put('$', CharType.CONCAT);
		charTypes.put('*', CharType.STAR);
		charTypes.put('+', CharType.PLUS);
		charTypes.put('?', CharType.QUESTION);
		charTypes.put('.', CharType.ANY);

	}

	public NFAConverter() {
	}

	public State fromPostFixExpressoin(char[] regex) {
		Fragment frag = new Fragment();
		char ch;
		for (int i = 0; i < regex.length; i++) {
			ch = regex[i];
			if (ch != '\\') {
				charTypes.get(ch).doProcess(ch, frag);
			} else {
				if (++i < regex.length) {
					CharType.DEFAULT.doProcess(regex[i], frag);
				}
			}
		}
		return frag.getValidFirst();
	}
}
