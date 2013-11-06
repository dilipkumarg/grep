package com.imaginea.dilip.grep.helpers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.imaginea.dilip.grep.entities.Arguments;

@RunWith(JUnit4.class)
public class ArgumentsBuilderTest {
	private ArgumentsBuilder argBuilder;

	@Before
	public void setUp() throws Exception {
		argBuilder = new ArgumentsBuilder();
	}

	@Test
	public void test1() {
		String[] args = { "searchkey", "-ci", "filepath" };
		Arguments arguments = argBuilder.buildArgs(args);
		Arguments argumentsExpected = new Arguments("searchkey", "filepath",
				"c", "ci");
		assertTrue("\nActual:" + arguments.toString() + "\nExpected:"
				+ argumentsExpected.toString(),
				isTwoSame(argumentsExpected, arguments));

	}

	@Test
	public void test2() {
		String[] args = { "searchkey", "filepath" };
		Arguments arguments = argBuilder.buildArgs(args);
		Arguments argumentsExpected = new Arguments("searchkey", "filepath",
				"j","");
		assertTrue("\nActual:" + arguments.toString() + "\nExpected:"
				+ argumentsExpected.toString(),
				isTwoSame(argumentsExpected, arguments));

	}

	@Test
	public void test3() {
		String[] args = { "searchkey", "-", "filepath" };
		Arguments arguments = argBuilder.buildArgs(args);
		Arguments argumentsExpected = new Arguments("searchkey", "filepath",
				"j", "");
		assertTrue("\nActual:" + arguments.toString() + "\nExpected:"
				+ argumentsExpected.toString(),
				isTwoSame(argumentsExpected, arguments));

	}

	@Test
	public void test4() {
		String[] args = { "searchkey", "-c", "filepath" };
		Arguments arguments = argBuilder.buildArgs(args);
		Arguments argumentsExpected = new Arguments("searchkey", "filepath",
				"c", "c");
		assertTrue("\nActual:" + arguments.toString() + "\nExpected:"
				+ argumentsExpected.toString(),
				isTwoSame(argumentsExpected, arguments));

	}

	@Test
	public void test5() {
		String[] args = { "searchkey", "-i", "filepath" };
		Arguments arguments = argBuilder.buildArgs(args);
		Arguments argumentsExpected = new Arguments("searchkey", "filepath",
				"j", "i");
		assertTrue("\nActual:" + arguments.toString() + "\nExpected:"
				+ argumentsExpected.toString(),
				isTwoSame(argumentsExpected, arguments));

	}

	@Test
	public void test6() {
		String[] args = {};
		Arguments arguments = argBuilder.buildArgs(args);
		Arguments argumentsExpected = new Arguments(null, null, "j", "");
		assertTrue("\nActual:" + arguments.toString() + "\nExpected:"
				+ argumentsExpected.toString(),
				isTwoSame(argumentsExpected, arguments));

	}

	@Test
	public void test7() {
		String[] args = { "searchkey" };
		Arguments arguments = argBuilder.buildArgs(args);
		Arguments argumentsExpected = new Arguments(null, null, "j", "");
		assertTrue("\nActual:" + arguments.toString() + "\nExpected:"
				+ argumentsExpected.toString(),
				isTwoSame(argumentsExpected, arguments));

	}

	@Test
	public void test8() {
		String[] args = { "searchkey", "filepath", "-cid" };
		Arguments arguments = argBuilder.buildArgs(args);
		Arguments argumentsExpected = new Arguments("searchkey", "filepath",
				"c", "cid");
		assertTrue("\nActual:" + arguments.toString() + "\nExpected:"
				+ argumentsExpected.toString(),
				isTwoSame(argumentsExpected, arguments));

	}

	@Test
	public void test9() {
		String[] args = { "-ci", "searchkey", "filepath" };
		Arguments arguments = argBuilder.buildArgs(args);
		Arguments argumentsExpected = new Arguments("searchkey", "filepath",
				"c", "ci");
		assertTrue("\nActual:" + arguments.toString() + "\nExpected:"
				+ argumentsExpected.toString(),
				isTwoSame(argumentsExpected, arguments));

	}

	@Test
	public void test10() {
		String[] args = { "-c", "-i", "-d", "searchkey", "filepath" };
		Arguments arguments = argBuilder.buildArgs(args);
		Arguments argumentsExpected = new Arguments("searchkey", "filepath",
				"c", "cid");
		assertTrue("\nActual:" + arguments.toString() + "\nExpected:"
				+ argumentsExpected.toString(),
				isTwoSame(argumentsExpected, arguments));

	}

	private boolean isTwoSame(Arguments arg1, Arguments arg2) {
		boolean response = false;
		// if two args are null response is true
		if (arg1 == null && arg2 == null) {
			response = true;
		} else if (arg1 == null || arg2 == null) { // if one is null
			response = false;
		} else {
			// checking all values are same or not
			if (arg1.getFilePath() == arg2.getFilePath()
					&& arg1.getSearchKey() == arg2.getSearchKey()
					&& arg1.getImplType() == arg2.getImplType()
					&& arg1.isCaseInSensitive() == arg2.isCaseInSensitive()) {
				response = true;
			}
		}
		return response;
	}
}
