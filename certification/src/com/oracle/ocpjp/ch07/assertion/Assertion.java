package com.oracle.ocpjp.ch07.assertion;

public class Assertion {
	public static void main(String[] args) {
		new Assertion().testAssertCorretoUso();
		new Assertion().testAssertPublic();
		new Assertion().testAssertException();
	}
	
	/**
	 * M�todo privado.
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
	 * Conven��o Oracle e cobrado na certifica��o, assert n�o deve ser usado em m�todos <b>public</b>.
	 */
	public void testAssertPublic() {
		assert(true);
	}
	
	
	private void testAssertException() {
		try {
			assert(false);
		} catch(AssertionError e) {
			System.out.println("Conven��o Oracle, assert n�o deve ser tratado em um catch.");
		}
	}
	
	protected void assertTestProtected() {
		assert(true);
	}
}