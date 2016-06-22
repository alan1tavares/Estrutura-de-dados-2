package hashing.fechada.duplo;

import auxiliar.Primo;
import hashing.fechada.HashingFechada;

/**
 * h(x) = hi(CHAVE), onde h0 = CHAVE mod TH, e hi = CHAVE mod
 * ProxPrimoMenor(THi-1), i = 1, ...,k
 */
public class ClosedHashingDuploOutraFuncao extends HashingFechada{
	private Integer[] tabelaHash;
	private int tamHash;

	public ClosedHashingDuploOutraFuncao(int tamanho) {
		tabelaHash = new Integer[tamanho];
		tamHash = tabelaHash.length;
	}

	// Insere um elemento na tabela
	public void inserir(int valor) {
		int indice;
		int proxPrimoMenor = tamHash;

		// Passo a passo
		System.out.println("\nInserir(" + valor + ")");
		// ------------//
		while (true) {
			indice = funcaoHash(valor, proxPrimoMenor); // Calcula o indice a
														// ser inserido

			// Passo a passo
			System.out.println(valor + " mod " + proxPrimoMenor + " = " + indice);
			// ------------//

			// Se nao existe elemento nessa posicao
			if (tabelaHash[indice] == null) {
				tabelaHash[indice] = valor; // Insere o elemento
				break; // Sai do laco
			}

			// Se ja tiver tentando inserir um valor, com esse primo na funcao
			if (proxPrimoMenor == 2)
				break;

			proxPrimoMenor = Primo.primoAnterior(proxPrimoMenor); // Calula o
																	// proximo
																	// primo
																	// menor
		}
	}

	// Deleta um elemento na tabela
	public void deletar(int valor) {
		int indice;
		int proxPrimoMenor = tamHash;

		//Passo a passo
		System.out.println("\nDeletar(" + valor + ")");
		//-----------//
		
		while (true) {
			indice = funcaoHash(valor, proxPrimoMenor); // Calcula o indice a
														// ser inserido
			
			// Passo a passo
			System.out.println(valor + " mod " + proxPrimoMenor + " = " + indice);
			//------------//
			
			// Se existe algum numero nesse indice e
			// se nesse indice e igual ao valor a ser deletado
			if (tabelaHash[indice] != null && tabelaHash[indice] == valor) {
				tabelaHash[indice] = null; // Deleta o elemento
				break; // Sai do laco
			}

			// Se ja tiver tentando deletar um valor, com esse primo na funcao
			if (proxPrimoMenor == 2)
				break; // Sair do laco

			proxPrimoMenor = Primo.primoAnterior(proxPrimoMenor); // Calula o
																	// proximo
																	// primo
																	// menor
		}
	}

	// Funcao hashing
	public int funcaoHash(int valor) {
		return valor % tamHash;
	}

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
