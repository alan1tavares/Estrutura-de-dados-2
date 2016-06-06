package avl;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner rl = new Scanner(System.in);
		int escolhaMenu;

		do {
			System.out.println("Menu");
			System.out.println("1 - AVL");
			System.out.println("0 - Sair");

			System.out.print("Escolha uma opção-> ");
			escolhaMenu = rl.nextInt();
			menuOpcao(escolhaMenu);

		} while (escolhaMenu != 0);
	}

	
	// Menu da Avl
	public static void menuAvl() {
		
		AVL avl = new AVL();
		
		// Opcao do menu
		Scanner rl = new Scanner(System.in);
		int menuOpcao;
		
		// Valor da Avl
		Scanner rlV = new Scanner(System.in);
		int valorAvl;
		
		do {
			// Menu
			System.out.println("Menu - AVL");
			System.out.println("1 - Inserir");
			System.out.println("2 - Eibir");
			System.out.println("0 - Sair");
			
			System.out.print("Escolha uma opção-> ");
			menuOpcao = rl.nextInt();
			
			
			switch (menuOpcao) {
			// Inserção da Avl
			case 1:
				System.out.print("Insira uma valor-> ");
				valorAvl = rlV.nextInt();
				avl.insert(valorAvl);
				System.out.println(valorAvl);
				break;
				
			// Exibição da AVL
			case 2:
				avl.imprimir();
				break;
			default:
				break;
			}
		} while (menuOpcao != 0);
	}

	public static void menuOpcao(int opcao) {
		switch (opcao) {
		case 1:
			menuAvl();
			break;

		default:
			break;
		}
	}

}
