package hashing.aberta;

import java.util.ArrayList;
/*
 * É a quantidade de elementos na maior lista interna
 * */
public class OpenHashing {
	private ArrayList<Integer>[] tabelaHash;
	private int tamHash;

	private int fatorDeCarga;

	public OpenHashing(int tamanho) {
		tabelaHash = new ArrayList[tamanho];
		tamHash = tabelaHash.length;
	}

	// Insere um elemento na tabela
	public void inserir(int valor) {
		int indice = funcaoHash(valor);
		if (tabelaHash[indice] == null)
			tabelaHash[indice] = new ArrayList<>();
		
		// Atualiza o fator de carga
		tabelaHash[indice].add(valor);
		if (tabelaHash[indice].size() > fatorDeCarga)
			fatorDeCarga = tabelaHash[indice].size();
	}

	// Deleta um elemento na tabela
	public void deletar(int valor) {
		int indice = funcaoHash(valor);
		if (tabelaHash[indice] != null) 
			tabelaHash[indice].remove(tabelaHash[indice].indexOf(valor));
		
		// Atualiza o fator de carga
		fatorDeCarga = 0;
		for (int i = 0; i < tabelaHash.length; i++) {
				if(cargaDoIndice(i) > fatorDeCarga)
					fatorDeCarga = cargaDoIndice(i);	
		}
	}

	// Retorna a carga do indice na tabela
	public int cargaDoIndice(int indice) {
		if (tabelaHash[indice] != null)
			if (tabelaHash[indice].size() > fatorDeCarga)
				return tabelaHash[indice].size();
		return -1;
	}

	// Funcao hashing
	public int funcaoHash(int valor) {
		return valor % tamHash;
	}

	// Retorna o fator de carga
	public int getFatorDeCarga() {
		return fatorDeCarga;
	}

	// Exibi a tabela
	public void exibir() {
		for (int i = 0; i < tabelaHash.length; i++) {
			System.out.print(i + " ->");
			if (tabelaHash[i] != null) {
				for (int valor : tabelaHash[i])
					System.out.print(" " + valor);
			}
			System.out.println();
		}

	}

}
