package hashing.fechada.tentativa.linear;

import java.util.LinkedList;

import hashing.fechada.HashingFechada;

/**
 * f(i), e h(x) = (x + i) mod TH, i = 0, ..., TH-1
 */
public class ClosedHashingLinear extends HashingFechada {

	private Integer[] tabelaHash;
	private int tamHash;

	public ClosedHashingLinear(int tamanho) {
		tabelaHash = new Integer[tamanho];
		tamHash = tabelaHash.length;
	}

	// Insere um elemento na tabela
	public void inserir(int valor) {
		int indice;

		// Passo a passo
		System.out.println("\nInserir (" + valor + ")");
		// -----------//

		for (int i = 0; i < tabelaHash.length; i++) {
			indice = funcaoHash(valor + i);

			// Passo a passo
			System.out.println("(" + valor + "+" + i + ")" + " mod " + tamHash + " = " + indice);
			// ------------//

			// Se nao existe elemento nessa posicao
			if (tabelaHash[indice] == null) {
				tabelaHash[indice] = valor; // Insere o elemento
				break; // Sai do laco
			}
		}
	}

	// Deleta um elemento na tabela
	public void deletar(int valor) {
		int indice;

		for (int i = 0; i < tabelaHash.length; i++) {
			indice = funcaoHash(valor + i);

			// Passo a passo
			System.out.println("(" + valor + "+" + i + ")" + " mod " + tamHash + " = " + indice);
			// -----------//

			// Se existe algum numero nesse indice e
			// se nesse indice e igual ao valor a se deletado
			if (tabelaHash[indice] != null && tabelaHash[indice] == valor) {
				tabelaHash[indice] = null;
				break;
			}
		}

	}

	// Funcao hashing
	public int funcaoHash(int valor) {
		return valor % tamHash;
	}

	// Exibi a tabela
	public void exibir() {
		for (int i = 0; i < tabelaHash.length; i++) {
			System.out.print("[" + i + "]");
			if (tabelaHash[i] != null) {
				System.out.print(" -> " + tabelaHash[i]);
			}
			System.out.println();
		}

	}
}
