package hashing.fechada;

import hashing.fechada.duplo.ClosedHashingDuplo;
import hashing.fechada.duplo.ClosedHashingDuploOutraFuncao;

public class TesteFechada {

	public static void main(String[] args) {
		ClosedHashingDuploOutraFuncao cl = new ClosedHashingDuploOutraFuncao(7);
		cl.inserir(89);
		cl.inserir(18);
		cl.inserir(49);
		cl.inserir(60);
		cl.inserir(69);
		
		cl.exibir();
		System.out.println();
		
		cl.deletar(69);
		cl.exibir();
		System.out.println();
	}
}