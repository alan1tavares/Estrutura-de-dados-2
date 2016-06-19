package hashing.fechada;

import hashing.fechada.duplo.ClosedHashingDuplo;

public class TesteFechada {

	public static void main(String[] args) {
		ClosedHashingDuplo cl = new ClosedHashingDuplo(7);
		cl.inserir(89);
		cl.inserir(18);
		cl.inserir(49);
		cl.inserir(60);
		cl.inserir(69);
		
		cl.exibir();
		System.out.println();
	}
}