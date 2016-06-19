package hashing.fechada.tentativa.linear;

import java.util.LinkedList;

public class ClosedHashingLinear {

	private Integer[] tabelaHash;
	private int tamHash;

	public ClosedHashingLinear(int tamanho) {
		tabelaHash = new Integer[tamanho];
		tamHash = tabelaHash.length;
	}

	// Insere um elemento na tabela
	public void inserir(int valor) {
		int indice;

		for (int i = 0; i < tabelaHash.length; i++) {
			indice = funcaoHash(valor + i);
			// Se nao existe elemento nessa posicao
			if (tabelaHash[indice] == null) {
				tabelaHash[indice] = valor; // Insere o elemento
				break; // Sai do laco
			}
		}
	}

	// Deleta um elemento na tabela
	public void deletar(int valor) {


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
