package hashing.meio_aberta;

import auxiliar.Primo;
import avl.AVL;

/**
 * Hash(x) = (x + i2) mod THk Duplicar a tabela THk = ProximoPrimo(2*THk-1), TH0
 * é um primo arbitrário
 */
public class HashingMeioAberta {
	private AVL tabelaHash[];
	private int tamHash;

	private int maximoElementos;
	private int quantidadePreencida;

	// Construor
	public HashingMeioAberta(int tamanho, int lambda) {
		tabelaHash = new AVL[tamanho];
		tamHash = tabelaHash.length;
		for (int i = 0; i <= lambda; i++) {
			maximoElementos += (int) Math.pow(2, i);
		}

	}

	// Insere um elemento na tabela
	public void inserir(int valor) {
		// Passo a passo
		System.out.println("Inserir (" + valor + ")");
		// -----------//

		int indice;
		boolean foiInserido = false;

		for (int i = 0; i < tabelaHash.length / 2; i++) {
			indice = funcaoHash(valor + i * i); // Calcula a posicao do indice

			// Passo a passo
			System.out.println(valor + "+" + i + "^2" + " mod " + this.tamHash + " = " + indice);
			// -----------//

			// Se nao sxiste uma arvore nessa posicao
			if (tabelaHash[indice] == null) {
				tabelaHash[indice] = new AVL(); // Cria a arvore
				tabelaHash[indice].insert(valor); // Insere o valor
				foiInserido = true; // valor inserido

				// Passo a passo
				exibir();
				System.out.println();
				// -----------//

				break;
			}

			// Se não se a quantidade de elementos for menor que a quantidade
			// maxima de elementos
			else if (tabelaHash[indice].getTotalElementos() < maximoElementos) {

				if (tabelaHash[indice].insert(valor) == -1) // Insere o elemento
					continue; // Coninua tentando

				// Se a posicao chegou no seu limite maximo
				if (tabelaHash[indice].getTotalElementos() == maximoElementos)
					quantidadePreencida++; // Incremente +1 na quantidade de
											// valores ja preenchido na tabela
				foiInserido = true; // valor inserido

				// Passo a passo
				exibir();
				System.out.println();
				// -----------//

				break;
			}else if(this.tabelaHash[indice].getTotalElementos() >= maximoElementos)
				continue;
		}

		// Se nao conseguiu inserir o elemento
		if (!foiInserido) {

			// Passo a passo
			System.out.println("--------Rehashing--------");
			// ------------//

			rehashing(); // Faz rehashing

			// Passo a passo
			System.out.println("-------------------------");
			exibir();
			// -----------//

			inserir(valor); // Insere o elemento
		}

		// Se a tabela esta metade cheia + 1
		if (quantidadePreencida > (tamHash / 2) + 1)
			rehashing(); // faça rehashing
	}

	// Rehashing
	private void rehashing() {
		AVL tabelaAntiga[] = this.tabelaHash; // tabela aux recebe a antiga
												// tabela
		// Cria uma nova tabela
		tabelaHash = new AVL[Primo.proximoPrimo(2 * this.tamHash)];
		tamHash = tabelaHash.length;

		// Percorrea a tabela antiga
		for (int i = 0; i < tabelaAntiga.length; i++) {
			// Se existe elementos nessa posicao
			if (tabelaAntiga[i] != null) {
				// Insere os elementos dessa posicao na nova tabela
				for (int valor : tabelaAntiga[i].getListaElementos()) {
					inserir(valor); // Insere os elementos na tabela
				}
			}
		}
	}

	// Deleta um elemento na tabela
	public void deletar() {
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
