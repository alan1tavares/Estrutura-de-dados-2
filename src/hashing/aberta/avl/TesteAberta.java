package hashing.aberta.avl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class TesteAberta {
	public static void main(String[] args) {
		OpenHashingAvl op = new OpenHashingAvl(7);
		op.inserir(0);
		op.inserir(1);
		op.inserir(85);
		op.inserir(6);
		op.inserir(36);
		op.inserir(46);
		op.inserir(89);
		op.inserir(112);
		op.inserir(44);
		
		
		
		op.exibir();
		System.out.println();
		System.out.println("Fator de carga " + op.getFatorDeCarga());
		System.out.println("Fator de balanceamento " + op.fatorDeBalanceamento());
	}
}
