package com.oracle.ocpjp.ch08.format;

public class StringProcessing {
	public static void main(String[] args) {
		new StringProcessing().printMatches("abaaaba", "ab");
		/*
		 * Quando a pesquisa for consumida, os caracteres n�o podem ser
		 * reaproveitados, portanto o �ndice 2 n�o ser� sensibilizado, porque o
		 * �ndice 2 est� no resultado da pesquisa do �ndice 0
		 */
		new StringProcessing().printMatches("abababa", "aba");

		/*
		 * Conceito de word para Java n�o entra espa�os em branco
		 */
		new StringProcessing().printMatches("a 14 a1#5a #_a", "\\w");

		/*
		 * Retorna todas as orcorr�ncias que n�o seja word
		 */
		new StringProcessing().printMatches("a 14 a1#5a #_a", "\\W");

		/*
		 * Retorna todas as ocorr�ncias de espa�os em branco
		 */
		new StringProcessing().printMatches("a 14 a1#5a #_a", "\\s");

		/*
		 * Retorna tudo que n�o seja espa�o em branco
		 */
		new StringProcessing().printMatches("a 14 a1#5a #_a", "\\S");

		/*
		 * Retorna tudo que for d�gito
		 */
		new StringProcessing().printMatches("a 14 15 _a", "\\d");

		/*
		 * Retorna tudo que n�o for d�gito
		 */
		new StringProcessing().printMatches("a 14 15 _a", "\\D");

		/*
		 * Retorna tudo que esteja no in�cio de uma "palavra"
		 */
		new StringProcessing().printMatches("#a43 14 ag#df #15 _a#a", "\\b");

		/*
		 * Retorna tudo que n�o esteja no in�cio de uma "palavra"
		 */
		new StringProcessing().printMatches("#a43 14 ag#df #15 _a#a", "\\B");

		/*
		 * Retorna inteiros na pesquisa
		 */
		new StringProcessing().printMatches("#a43 14 ag#df #15 _a#a", "\\d+", true);
		
		/*
		 * Retorna valores hexadecimais na pesquisa
		 */
		new StringProcessing().printMatches("12 0x 0x12 0x121f 0xh897 0x876a", "0[xX]([0-9a-fA-F])+", true);
		
		/*
		 * Identificador dot (.) pesquisa qualquer caractere
		 */
		new StringProcessing().printMatches("ac abc a c a  c", "a.c", true);
		
		/*
		 * Identificador asterisco busca todos
		 */
		new StringProcessing().printMatches("yyxxxyxx", ".*xx", true);
	}

	private void printMatches(String source, String search) {
		printMatches(source, search, false);
		System.out.println("\n");
	}

	private void printMatches(String source, String search, boolean printGroup) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(search);
		java.util.regex.Matcher matcher = pattern.matcher(source);

		System.out.println("source: " + source);
		System.out.println(" index: 01234567890123456789012345678901234567890123456789\n");
		System.out.println("pattern: " + matcher.pattern());
		if (!printGroup) {
			System.out.print("match positions: ");
		} else {
			System.out.println("index\tvalor");
		}

		while (matcher.find()) {
			StringBuilder result = new StringBuilder(Integer.toString(matcher.start()));
			if (printGroup) {
				result.append("\t" + matcher.group());
				System.out.println(result.toString());
			} else {
				System.out.print(result.toString() + " ");
			}
		}
	}
}