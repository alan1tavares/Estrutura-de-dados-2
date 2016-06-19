package hashing.fechada.tentativa.linear;

public class TesteFechada {

	public static void main(String[] args) {
		ClosedHashingLinear cl = new ClosedHashingLinear(7);
		cl.inserir(89);
		cl.inserir(18);
		cl.inserir(49);
		cl.inserir(60);
		cl.inserir(69);
		
		cl.exibir();
		System.out.println();
	}

}
