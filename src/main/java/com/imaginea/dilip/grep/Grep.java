package com.imaginea.dilip.grep;

import java.io.File;
import java.io.IOException;

public class Grep {
	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			String searchKey = args[0];
			String fileContent = "";
			if (args.length > 1) {
				fileContent = TextFileReader.readFile(Grep.class.getResource(
						File.separator + args[1]).getPath());
			} else {
				fileContent = TextFileReader.readFile(Grep.class.getResource(
						"/sample.txt").getPath());
			}
			for (String token : TextSearcher.searchString(searchKey,
					fileContent)) {
				System.out.println(token + "\n");
			}
		}
	}

}
