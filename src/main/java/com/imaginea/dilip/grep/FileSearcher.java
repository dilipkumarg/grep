package com.imaginea.dilip.grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.imaginea.dilip.grep.entities.Arguments;
import com.imaginea.dilip.grep.helpers.TaskRunner;
import com.imaginea.dilip.grep.searcher.TextSearcher;
import com.imaginea.dilip.grep.searcher.TextSearcherFactory;

public class FileSearcher {
	private final Arguments args;
	private final TextSearcher ts;
	private final Queue<String> inQueue;
	// private final Queue<String> outQueue;
	private boolean jobComplete;
	private final int NUM_IN_THREADS = 5;
	// private final int NUM_OUT_THREADS = 1;
	private final List<Thread> workers;

	public FileSearcher(Arguments args) {
		this.args = args;
		this.ts = TextSearcherFactory.getTextSearcher(args.getImplType(),
				args.getSearchKey(), args.isCaseInSensitive());
		this.inQueue = new ConcurrentLinkedQueue<String>();
		// this.outQueue = new ConcurrentLinkedQueue<String>();
		this.jobComplete = false;
		workers = new ArrayList<Thread>();
	}

	public Queue<String> getInQueue() {
		return inQueue;
	}

	/*
	 * public Queue<String> getOutQueue() { return outQueue; }
	 */

	private void startThreads() {
		for (int i = 0; i < NUM_IN_THREADS; i++) {
			Thread worker = new Thread(new TaskRunner(this));
			worker.setName("Thread-Search-" + i);
			worker.start();
			workers.add(worker);
		}
		// output threads for priniting output to console
		/*
		 * for (int i = 0; i < NUM_OUT_THREADS; i++) { Thread worker = new
		 * Thread(new OutPutPrinter(this)); worker.setName("Thread-Print-" + i);
		 * worker.start(); workers.add(worker); }
		 */
	}

	private void blockUntilThreadsAlive() throws InterruptedException {
		boolean isAlive = false;
		do {
			isAlive = false;
			for (Thread thread : workers) {
				if (thread.isAlive()) {
					isAlive = true;
					synchronized (this) {
						wait(1);
					}
				}
			}
		} while (isAlive);
	}

	public void printSearchResults(BufferedReader br) throws IOException,
			InterruptedException {
		waitIfDebugMode(args);
		String curLine = "";
		jobComplete = false;
		try {
			startThreads();
			while ((curLine = br.readLine()) != null) {
				inQueue.add(curLine);
				// if(this.ts.isStringContains(curLine)) {
				// System.out.println(curLine);
				// }
			}
		} finally {
			// in any case we should terminate threads.
			jobComplete = true;
		}
		blockUntilThreadsAlive();

	}

	public boolean isJobComplete() {
		return jobComplete;
	}

	private void waitIfDebugMode(Arguments args) {
		if (args.isDebugMode()) {
			System.out.println("Press any key to start application.");
			Scanner sc = null;
			try {
				sc = new Scanner(System.in);
				sc.nextLine();
			} finally {
				if (sc != null) {
					sc.close();
				}
			}
		}
	}

	public TextSearcher getTextSearcher() {
		return this.ts;
	}

}
