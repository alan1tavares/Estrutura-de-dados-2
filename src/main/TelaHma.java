package main;

import java.util.ArrayList;
import java.util.Scanner;

import auxiliar.Utilidades;
import hashing.meio_aberta.HashingMeioAberta;

public class TelaHma {
	private HashingMeioAberta hma;

	public void run() {
		menuOpcoes();

	}

	private void menuOpcoes() {
		int key;
		do {
			System.out.println("Menu - HMA");
			System.out.println("1 - Inserir lista");
			System.out.println("2 - Exibir");
			System.out.println("0 - Sair");
			System.out.print("Escolha a opcao -> ");

			Scanner sc = new Scanner(System.in);
			key = sc.nextInt();

			if (key == 1)
				inserirLista();
			
			else if (key == 2) {
				if (hma != null)
					hma.exibir();
			}
		} while (key != 0);
	}

	private void criarHma() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Tamanho -> ");
		int tamanho = sc.nextInt();

		System.out.print("Lambda -> ");
		int lambda = sc.nextInt();

		this.hma = new HashingMeioAberta(tamanho, lambda);
	}

	private void inserirLista() {
		System.out.println();

		// Criar o objeto
		criarHma();

		// Inserir valores
		System.out.print("Inserir lista ->");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();

		ArrayList<Integer> intList = Utilidades.stringToIntArray(s);

		for (Integer integer : intList) {
			this.hma.inserir(integer);
		}

	}
}
