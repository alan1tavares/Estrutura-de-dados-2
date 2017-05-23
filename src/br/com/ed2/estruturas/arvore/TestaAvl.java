package br.com.ed2.estruturas.arvore;

public class TestaAvl {

	public static void main(String[] args) {
		AvlTree<Integer> t = new AvlTree<>();
		final int SMALL = 40;
		final int NUMS = 1000000;
		final int GAP = 37;
		int k = 0;
		
		System.out.println("Checando...");
		/*for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
			System.out.println("INSERIR " + i);
			t.inserir(i);
			if(NUMS < SMALL)
				t.checarBalanco();
			k++;
		}*/
		inserir(t);
		t.preOrdem();
		System.out.println();
		//System.out.println(t.log);
	}

	private static void inserir(AvlTree<Integer> t) {
		// 50, 30, 20, 70, 40, 35, 37, 38, 10, 32, 45, 42, 25, 47, 36 
		t.inserir(50);
		t.inserir(30);
		t.inserir(20);
		t.inserir(70);
		t.inserir(40);
		t.inserir(35);
		t.inserir(37);
		t.inserir(38);
		t.inserir(10);
		t.inserir(32);
		t.inserir(45);
		t.inserir(42);
		t.inserir(25);
		t.inserir(47);
		t.inserir(36);
		
	}

}
