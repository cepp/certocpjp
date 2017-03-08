package com.oracle.ocpjp.ch08.format;

public class StringTokenizerTest {
	public static void main(String[] args) {
		new StringTokenizerTest().printStringTokenizer("a bc d e", null);

		
		new StringTokenizerTest().printStringTokenizer("a b cab a ba d", "a");
	}

	private void printStringTokenizer(String source, String pattern) {
		java.util.StringTokenizer stringTokenizer = new java.util.StringTokenizer("a bc d e");

		System.out.println("\n" + stringTokenizer.countTokens());

		while (stringTokenizer.hasMoreTokens()) {
			System.out.println(">" + stringTokenizer.nextToken() + "< ");
		}

		System.out.println("\n" + stringTokenizer.countTokens());
	}
}