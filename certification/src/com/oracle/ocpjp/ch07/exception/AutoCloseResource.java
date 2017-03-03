package com.oracle.ocpjp.ch07.exception;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class AutoCloseResource {
	/**
	 * Método com exemplo de try com resources, nova funcionalidade do Java 7
	 * que não precisa que seja finalizado o resource, o mesmo existe somente no
	 * bloco do try, depois é automaticamente fechado.
	 * 
	 * @param file
	 */
	private void tryWithResources(File file) {
		try (Reader reader = new BufferedReader(new FileReader(file))) {

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método com exemplo de um try sem o recurso de resources, somente
	 * utilizando o objeto normal como era utilizado nas versões anteriores do
	 * java.
	 * 
	 * @param file
	 */
	private void tryWithoutResources(File file) {
		Reader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método simula o multi-catch thrown pelo método, não pode fazer o e = new
	 * Exception(), pelo mesmo motivo do multi-catch.
	 * 
	 * @param file
	 * @throws IOException
	 * @throws NullPointerException
	 */
	private void tryWithResourceException(File file) throws IOException, NullPointerException {
		try (Reader reader1 = new BufferedReader(new FileReader(file));
				Reader reader2 = new BufferedReader(new FileReader(file))) {

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Mudança de regra dos blocos try-catch-finally, anteriormente ao declarar
	 * um bloco try era obrigatório um catch ou um finally após, no caso do
	 * try-resource não é obrigatório porque o bloco de resource já possui um
	 * finally acoplado nele.
	 * 
	 * @param file
	 * @throws IOException
	 */
	private void tryWithResourceOnlyTry(File file) throws IOException {
		try (Reader reader = new BufferedReader(new FileReader(file))) {

		}
	}

	/**
	 * Somente classes que implementam a interface AutoCloseable ou Closeable podem ser
	 * utilizados no try-resources.
	 */
	private void resourceValido() {
		// try (String s = "") {
		//
		// }
	}

	/**
	 * Após o término do bloco try com resource é chamado o método close do
	 * resource, nesse exemplo, como é lançada uma exception antes do final do
	 * bloco try, o bloco catch recebe essa exception e ao executar o close do
	 * resource é lançada outra exception que será suprimida.
	 */
	private void tryResourceSuppressedException() {
		try (TestException01 test1 = new TestException01("1")) {
			throw new Exception("Teste");
		} catch (Exception e) {
			e.printStackTrace();
			for (Throwable t : e.getSuppressed()) {
				System.err.println("Suppressed:" + t);
			}
		}
	}

	/**
	 * Nesse caso o resource test2 será considerado como a exception principal,
	 * porque será lançada por primeiro, depois a exception do resource test1
	 * será lançada e adicionada ao array de suppressed exceptions.
	 */
	private void tryResourceSupressedException2() {
		try (TestException01 test1 = new TestException01("1"); TestException01 test2 = new TestException01("2")) {

		} catch (Exception e) {
			e.printStackTrace();
			for (Throwable t : e.getSuppressed()) {
				System.err.println("Suppressed:" + t);
			}
		}
	}
	
	class TestException01 implements AutoCloseable {
		String teste;

		TestException01(String teste) {
			this.teste = teste;
		}

		@Override
		public void close() throws Exception {
			throw new IOException("Fechando - " + this.teste);
		}
	}
	
	class Lamb implements Closeable {
		@Override
		public void close() {
			throw new RuntimeException("a");
		}
	}
	
	public static void main(String[] args) {
		new AutoCloseResource().run();
	}
	
	public void run() {
		try(Lamb l = new Lamb()) {
			throw new IOException();
		} catch(IOException e) {
			throw new RuntimeException("c");
		}
	}
}