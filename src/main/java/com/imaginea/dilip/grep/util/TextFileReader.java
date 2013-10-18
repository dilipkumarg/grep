package com.imaginea.dilip.grep.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {

	public static BufferedReader readFileAsBuff(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		return br;
	}
}
