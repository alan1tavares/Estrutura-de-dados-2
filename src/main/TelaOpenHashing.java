package main;

import java.util.ArrayList;
import java.util.Scanner;

import auxiliar.Utilidades;
import hashing.aberta.HashingAberta;
import hashing.aberta.avl.OpenHashingAvl;
import hashing.aberta.lista.OpenHashingLista;

public class TelaOpenHashing {
	private HashingAberta hashing;

	public void run() {

		menuOpcose();

	}

	private void menuOpcose() {
		int op;
		do {
			System.out.println("Menu Hashing Aberta");
			System.out.println("1 - Com listas");
			System.out.println("2 - Com avl");
			System.out.println("0 - Sair");

			Scanner sc = new Scanner(System.in);
			System.out.print("Opção -> ");
			op = sc.nextInt();

			System.out.println();

			if (op == 1)
				opcaoComListas();
			else if (op == 2)
				opcaoComAvl();

		} while (op != 0);

	}

	private void opcaoComAvl() {
		int op = 0;
		do {
			System.out.println("1 - Inserir lista");
			System.out.println("2 - Remover");
			System.out.println("3 - Exibir");
			System.out.println("0 - Sair");

			Scanner sc = new Scanner(System.in);
			System.out.print("Opção -> ");
			op = sc.nextInt();
			System.out.println();

			switch (op) {
			case 1:
				System.out.print("Tamanho da hashing -> ");
				int tamanho = sc.nextInt();
				this.hashing = new OpenHashingAvl(tamanho);

				sc = new Scanner(System.in);
				System.out.print("Inserir valores -> ");
				String s = sc.nextLine();

				System.out.println();

				ArrayList<Integer> ls = Utilidades.stringToIntArray(s);
				for (Integer integer : ls) {
					hashing.inserir(integer);
				}

				hashing.exibir();
				System.out.println();
				
				break;
			case 2:
				if (hashing != null)
					hashing.exibir();
				break;
			case 3:

				break;

			default:
				break;
			}
		} while (op != 0);

	}

	private void opcaoComListas() {
		int op;
		do {
			System.out.println("1 - Inserir lista");
			System.out.println("2 - Remover");
			System.out.println("3 - Exibir");
			System.out.println("0 - Sair");

			Scanner sc = new Scanner(System.in);
			System.out.print("Opção -> ");
			op = sc.nextInt();
			System.out.println();

			switch (op) {
			case 1:
				System.out.print("Tamanho da hashing -> ");
				int tamanho = sc.nextInt();
				this.hashing = new OpenHashingLista(tamanho);

				sc = new Scanner(System.in);
				System.out.print("Inserir valores -> ");
				String s = sc.nextLine();

				System.out.println();

				ArrayList<Integer> ls = Utilidades.stringToIntArray(s);
				for (Integer integer : ls) {
					hashing.inserir(integer);
				}

				hashing.exibir();
				System.out.println();
				break;
			case 2:
				if (hashing != null)
					hashing.exibir();
				break;
			case 3:

				break;

			default:
				break;
			}
		} while (op != 0);
	}
}
