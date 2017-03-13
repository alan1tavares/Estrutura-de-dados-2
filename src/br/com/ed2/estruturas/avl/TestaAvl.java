package br.com.ed2.estruturas.avl;

public class TestaAvl {

	public static void main(String[] args) {
		AvlTree<Integer> t = new AvlTree<>();
		final int SMALL = 40;
		final int NUMS = 1000000;
		final int GAP = 37;
		int k = 0;
		
		System.out.println("Checando...");
		for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
			System.out.println("INSERIR " + i);
			t.inserir(i);
			if(NUMS < SMALL)
				t.checarBalanco();
			k++;
		}
		

	}

}
