package com.imaginea.dilip.grep.helpers;

import com.imaginea.dilip.grep.FileSearcher;

public class OutPutPrinter implements Runnable {
	private final FileSearcher fs;

	public OutPutPrinter(FileSearcher fs) {
		this.fs = fs;
	}

	@Override
	public void run() {
		while (true) {
			String curLine = fs.getInQueue().poll();
			if (curLine != null) {
				System.out.println(curLine);
			} else {
				if (fs.isJobComplete()) {
					break;
				}
			}
		}
	}
}
