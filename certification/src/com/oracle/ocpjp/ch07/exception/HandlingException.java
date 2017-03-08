package com.oracle.ocpjp.ch07.exception;

import java.io.File;
import java.io.IOException;

public class HandlingException {

	/**
	 * � poss�vel atribuir nova inst�ncia ao par�metro do catch, mas n�o � uma
	 * boa pr�tica
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
	 * Com multi-catch n�o � poss�vel atribuir nova inst�ncia ao par�metro do
	 * catch. Acontece um erro de compila��o.
	 */
	private void exceptionHandlerMultiCatch() {
		try {
			createFile();
		} catch (IOException | SecurityException e) {
			// e = new IOException(); // express�o ilegal

			e.printStackTrace();
		}
	}

	/**
	 * Com multi-catch n�o se pode utilizar na express�o classes que possuem
	 * relacionamentos de heran�a. Exemplo IOException � subclasse de Exception.
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