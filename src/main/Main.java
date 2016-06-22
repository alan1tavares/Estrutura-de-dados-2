package main;

import java.util.Random;
import java.util.Scanner;

import auxiliar.NumerosTeste;
import avl.AVL;

public class Main {

	public static void main(String[] args) {

		Scanner rl = new Scanner(System.in);
		int escolhaMenu;

		do {
			System.out.println("Menu");
			System.out.println("1 - AVL");
			System.out.println("2 - Hashing Aberta");
			System.out.println("3 - Hashing Fechada");
			System.out.println("4 - HMA");
			System.out.println("0 - Sair");

			System.out.print("Escolha uma opção-> ");
			escolhaMenu = rl.nextInt();
			System.out.println();
			menuOpcao(escolhaMenu);

		} while (escolhaMenu != 0);
	}

	// Opcoes do menu
	public static void menuOpcao(int opcao) {
		switch (opcao) {
		case 1: // Avl
			TelaAvl telaAvl = new TelaAvl();
			telaAvl.run();
			break;
		case 2: // Hashing aberta
			TelaOpenHashing telaOpen = new TelaOpenHashing();
			telaOpen.run();
			break;
		case 3: // Hashing fechada
			TelaClosedHashing telaClosed = new TelaClosedHashing();
			telaClosed.run();
			break;
		case 4: // HMA
			TelaHma telaHMA = new TelaHma();
			telaHMA.run();
			break;
		default:
			break;
		}
	}
	
	
	}
