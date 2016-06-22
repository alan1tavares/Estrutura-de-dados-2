package hashing.meio_aberta;

import java.util.ArrayList;
import java.util.Scanner;

import auxiliar.Utilidades;

public class Teste {
//(13 15 24 6 23 55 42 28 37 52 68 29 13 66 31
	public static void main(String[] args) {
		HashingMeioAberta hma = new HashingMeioAberta(3, 1);
		ArrayList<Integer> h;
		
		hma.inserir(13);
		hma.inserir(15);
		hma.inserir(24);
		hma.inserir(6);
		hma.inserir(23);
		hma.inserir(55);
		hma.inserir(42);
		//Scanner sc = new Scanner(System.in);
		//String s = sc.nextLine();
		//h = Utilidades.stringToIntArray(s);
		/*h.add(13);
		h.add(15);
		h.add(24);
		h.add(6);
		h.add(23);
		h.add(55);
		h.add(42);*/
		
		/*for (Integer integer : h) {
			hma.inserir(integer);
		}*/
		
		
		hma.exibir();
		System.out.println();
		
		/*hma.inserir(28);
		hma.inserir(37);
		hma.inserir(52);
		hma.inserir(68);
		hma.inserir(29);
		hma.inserir(13);
		hma.inserir(66);
		hma.inserir(31);
		
		hma.exibir();
		System.out.println();
		hma.inserir(31);
		
		hma.exibir();*/

	}

}
