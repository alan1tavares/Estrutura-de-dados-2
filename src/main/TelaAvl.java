package main;

import java.util.ArrayList;
import java.util.Scanner;

import auxiliar.Utilidades;
import avl.AVL;

public class TelaAvl {
	private AVL avl;

	public void run() {
		menuOpcoes();
	}

	private void menuOpcoes() {
		int op;
		do {
			System.out.println("Menu - AVL");
			System.out.println("1 - Inserir lista");
			System.out.println("2 - Exibir");
			System.out.println("0 - Sair");

			System.out.print("Opção -> ");
			Scanner sc = new Scanner(System.in);
			op = sc.nextInt();

			if (op == 1)
				inserir();
			else if (op == 2)
				exbir();
			
		} while (op != 0);

	}

	private void inserir() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Inserir lista -> ");
		String s = sc.nextLine();
		
		this.avl = new AVL();

		ArrayList<Integer> intList = Utilidades.stringToIntArray(s);
		for (Integer integer : intList) {
			this.avl.insert(integer);
		}

	}

	private void exbir() {
		if (avl != null)
			avl.imprimir();
	}
}
