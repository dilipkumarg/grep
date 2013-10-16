package com.imaginea.dilip.grep.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextFileReader {
	/**
	 * Reads file from the file and return string out of it. And it takes char
	 * set as "UTF-8"
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String path) throws IOException {
		// return readFile(path, StandardCharsets.UTF_8);
		return readFile(path, StandardCharsets.UTF_8);
	}

	/**
	 * Reads file from the file and return string out of it.
	 * 
	 * @param path
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}

	public static BufferedReader readFileAsBuff(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		return br;
	}
}
