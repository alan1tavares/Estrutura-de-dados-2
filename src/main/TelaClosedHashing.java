package main;

import java.util.ArrayList;
import java.util.Scanner;

import auxiliar.Utilidades;
import hashing.fechada.HashingFechada;
import hashing.fechada.duplo.ClosedHashingDuplo;
import hashing.fechada.tentativa.linear.ClosedHashingLinear;
import hashing.fechada.tentativa.quadratica.ClosedHashingQuadratica;

public class TelaClosedHashing {
	private HashingFechada hashing;

	public void run() {
		menuOpcoes();
	}

	private void menuOpcoes() {
		int op;
		do {
			System.out.println("\nMenu Hashing Fechada");
			System.out.println("1 - Tentaiva Linear");
			System.out.println("2 - Tentativa Quadratica");
			System.out.println("3 - Tetativa dupla");
			System.out.println("0 - Sair");

			System.out.print("\nOpção -> ");
			Scanner sc = new Scanner(System.in);
			op = sc.nextInt();
			
			System.out.print("\nTamanho da hashing -> ");
			int tamanho = sc.nextInt();
			
			switch (op) {
			case 1:
				this.hashing = new ClosedHashingLinear(tamanho);
				menuHashing("\nHashing Fechada Linear");
				break;
			case 2:
				this.hashing = new ClosedHashingQuadratica(tamanho);
				menuHashing("\nHashing Fechada Quadratica");
				break;
			case 3:
				this.hashing = new ClosedHashingDuplo(tamanho);
				menuHashing("\nHashing Fechada Dupla");
				break;

			default:
				break;
			}

			System.out.println();
			
		} while (op != 0);

	}

	private void menuHashing(String s) {
		int op;
		do {
			System.out.println(s);
			System.out.println("1 - Inserir lista");
			System.out.println("2 - Remover");
			System.out.println("3 - Exibir");
			System.out.println("0 - Sair");
			
			System.out.print("\nOpção -> ");
			Scanner sc = new Scanner(System.in);
			op = sc.nextInt();
			
			switch (op) {
			case 1:
				inserirHashing();
				break;
			case 2:
				System.out.print("\nValor a ser removido -> ");
				int valor = sc.nextInt();
				this.hashing.deletar(valor);
				this.hashing.exibir();
				break;
			case 3:
				this.hashing.exibir();
				break;
			default:
				break;
			}
			
			System.out.println();
			
		} while (op != 0);
	}

	private void inserirHashing() {
		System.out.print("\nInsira a lista -> ");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		ArrayList<Integer> valores = Utilidades.stringToIntArray(s);
		for (Integer integer : valores) {
			this.hashing.inserir(integer);
		}
		
		System.out.println();
		this.hashing.exibir();
	}
}
