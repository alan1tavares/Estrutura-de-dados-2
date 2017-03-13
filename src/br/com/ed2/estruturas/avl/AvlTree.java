package br.com.ed2.estruturas.avl;

import br.com.ed2.estruturas.Estrutura;

public class AvlTree<Type extends Comparable<? super Type>> implements Estrutura<Type> {
	private AvlNode<Type> raiz;

	@Override
	public void inserir(Type elemento) {
		this.raiz = inserir(elemento, raiz);

	}

	private AvlNode<Type> inserir(Type elemento, AvlNode<Type> no) {

		if (no == null)
			return new AvlNode<Type>(elemento);

		int resultadoComparado = elemento.compareTo(no.elemento);

		// Insiro o no na esquera se o no for menor que a raiz
		// caso contrario insiro na direita
		if (resultadoComparado < 0)
			no.filhoEsquerdo = inserir(elemento, no.filhoEsquerdo);
		else if (resultadoComparado > 0)
			no.filhoDireto = inserir(elemento, no.filhoDireto);
		// No duplicado faz nada

		// Balanceia o no
		return balancear(no);
	}

	private static final int ALLOWED_IMBALANCE = 1;

	private AvlNode<Type> balancear(AvlNode<Type> no) {
		// Verifica se a árvore está pesando pra esquerda
		if (alturaDo(no.filhoEsquerdo) - alturaDo(no.filhoDireto) > ALLOWED_IMBALANCE)
			// Verifica se a rotação ira ocorrer pro lado de fora
			// Se não é pro lado de dentro
			if (alturaDo(no.filhoEsquerdo.filhoEsquerdo) >= alturaDo(no.filhoEsquerdo.filhoDireto))
				no = rotacaoSimplesEsquerda(no);
			else
				no = rotacaoDuplaEsquerda(no);
		else
		// Verifica se a árvore está pesando para direita
		if (alturaDo(no.filhoDireto) - alturaDo(no.filhoEsquerdo) > ALLOWED_IMBALANCE)
			// Verifica se a rotação ira ocorrer pro lado de fora
			// Se não é pro lado de dentro
			if (alturaDo(no.filhoDireto.filhoDireto) >= alturaDo(no.filhoDireto.filhoEsquerdo))
				no = rotacaoSimplesDireita(no);
			else
				no = rotacaoDuplaDireita(no);

		// Atualiza a altura
		no.altura = Math.max(alturaDo(no.filhoEsquerdo), alturaDo(no.filhoDireto)) + 1;

		return no;
	}

	private AvlNode<Type> rotacaoDuplaDireita(AvlNode<Type> k3) {
		k3 = rotacaoSimplesEsquerda(k3.filhoDireto);
		return rotacaoSimplesDireita(k3);
	}

	// Rotacao Dupla direita-esquerda
	private AvlNode<Type> rotacaoDuplaEsquerda(AvlNode<Type> k3) {
		k3 = rotacaoSimplesDireita(k3.filhoEsquerdo);
		return rotacaoSimplesEsquerda(k3);
	}

	private AvlNode<Type> rotacaoSimplesDireita(AvlNode<Type> k2) {
		AvlNode<Type> k1 = k2.filhoDireto;
		k2.filhoDireto = k1.filhoEsquerdo;
		k1.filhoEsquerdo = k2;
		k2.altura = Math.max(alturaDo(k2.filhoEsquerdo), alturaDo(k2.filhoDireto)) + 1;
		k1.altura = Math.max(alturaDo(k1.filhoEsquerdo), alturaDo(k1.filhoDireto)) + 1;
		return k1;
	}

	private AvlNode<Type> rotacaoSimplesEsquerda(AvlNode<Type> k2) {
		AvlNode<Type> k1 = k2.filhoEsquerdo;
		// Faz rotação
		k2.filhoEsquerdo = k1.filhoDireto;
		k1.filhoDireto = k2;
		// Atualiza a altura
		k2.altura = Math.max(alturaDo(k2.filhoEsquerdo), alturaDo(k2.filhoDireto)) + 1;
		k1.altura = Math.max(alturaDo(k1.filhoEsquerdo), alturaDo(k1.filhoDireto)) + 1;
		// k1 é o pai
		return k1;
	}

	static <T> int alturaDo(AvlNode<T> no) {
		return no == null ? -1 : no.altura;
	}

	@Override
	public void deletar(Type elemento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buscar(Type elemento) {
		// TODO Auto-generated method stub

	}

	public void checarBalanco() {
		checarBalanco(raiz);

	}

	private int checarBalanco(AvlNode<Type> t) {
		if (t == null)
			return -1;
		if (t != null) {
			int hl = checarBalanco(t.filhoEsquerdo);
			int hr = checarBalanco(t.filhoDireto);
			if (Math.abs(alturaDo(t.filhoEsquerdo) - alturaDo(t.filhoDireto)) > 1 || alturaDo(t.filhoEsquerdo) != hl
					|| alturaDo(t.filhoDireto) != hr)
				System.out.println("OOPS!");
		}
		return alturaDo(t);
	}

}
