package com.oracle.ocpjp.ch07.exception;

import java.io.File;
import java.io.IOException;

public class HandlingException {

	/**
	 * É possível atribuir nova instância ao parâmetro do catch, mas não é uma
	 * boa prática
	 */
	private void exceptionHandlerNewIOException() {
		try {
			createFile();
		} catch (IOException e) {
			e = new IOException();
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Com multi-catch não é possível atribuir nova instância ao parâmetro do
	 * catch. Acontece um erro de compilação.
	 */
	private void exceptionHandlerMultiCatch() {
		try {
			createFile();
		} catch (IOException | SecurityException e) {
			// e = new IOException(); // expressão ilegal

			e.printStackTrace();
		}
	}

	/**
	 * Com multi-catch não se pode utilizar na expressão classes que possuem
	 * relacionamentos de herança. Exemplo IOException é subclasse de Exception.
	 */
	private void exceptionHandlerMultiCatchError() {
//		try {
//			createFile();
//		} catch (IOException | Exception e) {
//			e.printStackTrace();
//		}
	}

	private void createFile() throws IOException, SecurityException {
		File file = new File("c:\teste.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		file.getAbsolutePath();
	}
}