package com.oracle.ocpjp.ch07.assertion;

public class Assertion {
	public static void main(String[] args) {
		new Assertion().testAssertCorretoUso();
		new Assertion().testAssertPublic();
		new Assertion().testAssertException();
	}
	
	/**
	 * Método privado.
	 */
	private void testAssertCorretoUso() {
		int x = 1;
		assert 1 == 1;
		assert (x > 0) : x = 2;
		assert (1 == 1);
		assert(true) : "Passando mensagem de erro com assert error";
		assert (true) : "teste01";
	}
	
	/**
	 * Convenção Oracle e cobrado na certificação, assert não deve ser usado em métodos <b>public</b>.
	 */
	public void testAssertPublic() {
		assert(true);
	}
	
	
	private void testAssertException() {
		try {
			assert(false);
		} catch(AssertionError e) {
			System.out.println("Convenção Oracle, assert não deve ser tratado em um catch.");
		}
	}
	
	protected void assertTestProtected() {
		assert(true);
	}
}