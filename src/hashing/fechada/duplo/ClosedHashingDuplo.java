package hashing.fechada.duplo;

import auxiliar.Primo;
import hashing.fechada.HashingFechada;

/**
 * f(0) = CHAVE mod TH, f(i) = i * hd(CHAVE) mod TH 
 * hd(ChAVE) = r - (CHAVE mod r)
 * r é um primo menor que TAM_HASH
 */
public class ClosedHashingDuplo extends HashingFechada {
	private Integer[] tabelaHash;
	private int tamHash;

	public ClosedHashingDuplo(int tamanho) {
		tabelaHash = new Integer[tamanho];
		tamHash = tabelaHash.length;
	}

	// Insere um elemento na tabela
	public void inserir(int valor) {
		int indice = funcaoHash(valor); // 1a funcao hash

		int r = Primo.primoAnterior(tamHash); // um primo menor que tamHash
		int funcao = r - funcaoHash(valor, r); // 2a funcao hash - hd(x)

		// Passo a passo
		System.out.println("\nInserir(" + valor + ")");
		System.out.println("funcao2 = " + r + "-" + "(" + valor + " mod " + r + ") = " + funcao);
		System.out.println("1a tentaiva = " + valor + " mod " + tamHash + " = " + indice);
		// ------------//

		for (int i = 1; i < tabelaHash.length; i++) {

			// Se nao existe elemento nessa posicao
			if (tabelaHash[indice] == null) {
				tabelaHash[indice] = valor; // Insere o elemento
				break; // Sai do laco
			}
			// Calcula outro indice com outra funcao
			indice = funcaoHash(i * funcao);

			// Passo a passo
			System.out.println("(" + i + "*" + funcao + ") mod " + tamHash + " = " + indice);
			// ------------//
		}
	}

	// Deleta um elemento na tabela
	public void deletar(int valor) {
		int indice = funcaoHash(valor); // 1a funcao hash

		int r = Primo.primoAnterior(tamHash); // um primo menor que tamHash
		int funcao = r - funcaoHash(valor, r); // 2a funcao hash - hd(x)

		// Passo a passo
		System.out.println("\nDeletar(" + valor + ")");
		System.out.println("funcao2 = " + r + "-" + "(" + valor + " mod " + r + ") = " + funcao);
		System.out.println("1a tentaiva = " + valor + " mod " + tamHash + " = " + indice);
		// ------------//

		for (int i = 0; i < tabelaHash.length; i++) {
			// indice = funcaoHash(valor + i);

			// Se existe algum numero nesse indice e
			// se nesse indice e igual ao valor a se deletado
			if (tabelaHash[indice] != null && tabelaHash[indice] == valor) {
				tabelaHash[indice] = null;
				break;
			}
			indice = funcaoHash(i * funcao);
			// Passo a passo
			System.out.println("(" + i + "*" + funcao + ") mod " + tamHash + " = " + indice);
			// ------------//
		}

	}

	// Funcao hashing
	public int funcaoHash(int valor) {
		return valor % tamHash;
	}

	// Funcao hashing
	public int funcaoHash(int valor, int r) {
		return valor % r;
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
