package com.imaginea.dilip.grep.helpers;

import com.imaginea.dilip.grep.FileSearcher;

public class TaskRunner implements Runnable {
	private final FileSearcher fs;

	public TaskRunner(FileSearcher fs) {
		this.fs = fs;
	}

	@Override
	public void run() {
		while (true) {
			String curLine = null;
			curLine = fs.getInQueue().poll();

			if (curLine != null) {
				if (fs.getTextSearcher().isStringContains(curLine)) {
					// fs.getOutQueue().add(curLine);
					// System.out.println(curLine);
				}
			} else {
				if (fs.isJobComplete()) {
					break;
				} else {
					synchronized (this) {
						try {
							wait(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

}
