package com.oracle.ocpjp.ch08.format;

public class ScannerClass {
	public static void main(String[] args) {
		System.out.print("input: ");
		System.out.flush();
		
		try {
			java.util.Scanner scanner = new java.util.Scanner(System.in);
			String token;
			do {
				token = scanner.findInLine(args[0]);
				System.out.println("found: "+ token);
			} while(token != null);
		} catch(Exception e) {
			System.out.println("scan exec");
		}
	}
}