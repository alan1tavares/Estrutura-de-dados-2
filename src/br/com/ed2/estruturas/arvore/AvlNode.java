package br.com.ed2.estruturas.avl;

import java.util.ArrayList;

/**
 * Classe que irá representar um nó em uma árvore avl 
 * @author Alan Tavares
 *
 */
public class AvlNode<Type> {
	 protected AvlNode<Type> filhoEsquerdo;
	 protected AvlNode<Type> filhoDireto;
	 protected Type elemento;
	 protected int altura;
	
	public AvlNode(Type elemento) {
		this(elemento, null, null);
	}

	public AvlNode(Type elemento, AvlNode<Type> esquerda, AvlNode<Type> direta) {
		this.filhoEsquerdo = esquerda;
		this.filhoDireto = direta;
		this.elemento = elemento;
		this.altura = 0;
	}
	
	public int alturaDoNo(AvlNode<Type> t){
		return t == null ? -1 : t.altura;
	}
}
