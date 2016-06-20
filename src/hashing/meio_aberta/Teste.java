package hashing.meio_aberta;

public class Teste {

	public static void main(String[] args) {
		HashingMeioAberta hma = new HashingMeioAberta(3, 2);
		hma.inserir(13);
		hma.inserir(15);
		hma.inserir(24);
		hma.inserir(6);
		hma.inserir(23);
		hma.inserir(55);
		hma.inserir(42);
		
		hma.exibir();
		System.out.println();
		
		hma.inserir(28);
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
		
		hma.exibir();

	}

}
