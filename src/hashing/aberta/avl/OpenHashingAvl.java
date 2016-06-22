package hashing.aberta.avl;

import java.util.LinkedList;

import avl.AVL;
import hashing.aberta.HashingAberta;

/**
 * Se comporta como uma estrtura de busca. Nao pode ter elementos repetidos.
 */
public class OpenHashingAvl extends HashingAberta {
	private AVL tabelaHash[];
	private int tamHash;

	private int fatorDeCarga;

	public OpenHashingAvl(int tamanho) {
		tabelaHash = new AVL[tamanho];
		tamHash = tabelaHash.length;
	}

	// Insere um elemento na tabela
	public void inserir(int valor) {
		int indice = funcaoHash(valor);

		// Passo a passo
		System.out.println(valor + "%" + this.tamHash + " = " + indice);
		// ------------//

		if (tabelaHash[indice] == null)
			tabelaHash[indice] = new AVL();

		// Insere o elemento
		tabelaHash[indice].insert(valor);

		// Atualiza o fator de carga
		if (tabelaHash[indice].getAltura() > fatorDeCarga)
			fatorDeCarga = tabelaHash[indice].getAltura();
		exibir();
		System.out.println();
	}

	// Deleta um elemento na tabela
	public void deletar(int valor) {
		/*
		 * Falta implementar
		 */
	}

	// Retorna a carga do indice na tabela
	public int cargaDoIndice(int indice) {
		if (tabelaHash[indice] != null)
			return tabelaHash[indice].getAltura();
		return 0;
	}

	// Funcao hashing
	public int funcaoHash(int valor) {
		return valor % tamHash;
	}

	// Retorna o fator de carga
	public int getFatorDeCarga() {
		return fatorDeCarga;
	}

	// Fator de balanceamento
	// indica como esta a distribuicao de dados na tabela
	public double fatorDeBalanceamento() {
		double somatorio = 0;
		for (int i = 0; i < tabelaHash.length; i++) {
			somatorio = somatorio + cargaDoIndice(i);
		}
		double fatorBalanceamento = somatorio / (tamHash * fatorDeCarga);

		return fatorBalanceamento;
	}

	// Exibi a tabela
	public void exibir() {
		for (int i = 0; i < tabelaHash.length; i++) {
			System.out.print("[" + i + "] -> ");
			if (tabelaHash[i] != null) {
				tabelaHash[i].imprimir();
			} else
				System.out.println();
		}
	}

}
