package br.com.ed2.estruturas.avl;

import br.com.ed2.estruturas.Estrutura;
import br.com.ed2.estruturas.relatorio.Relatorio;
import br.com.ed2.estruturas.relatorio.RelatorioAvl;

public class AvlTree<Type extends Comparable<? super Type>> implements Estrutura<Type> {
	private AvlNode<Type> raiz;
	//String log = "";
	Relatorio log;
	private boolean passoRotacaoSimplesLog = true;

	private static final int ALLOWED_IMBALANCE = 1;

	public AvlTree(boolean relatorio){
		if(relatorio == true) log = new RelatorioAvl();
	}
	
	public AvlTree(){
		
	}
	
	@Override
	public void inserir(Type elemento) {
		this.log. += "Entrada: " + elemento + "\n";
		this.raiz = inserir(elemento, raiz);
		this.log += "Saida: " + preOrdem();

	}

	private AvlNode<Type> inserir(Type elemento, AvlNode<Type> t) {
		if (t == null)
			return new AvlNode<Type>(elemento);

		int resultadoComparado = elemento.compareTo(t.elemento);

		// Insiro o no na esquera se o no for menor que a raiz
		// caso contrario insiro na direita
		if (resultadoComparado < 0)
			t.filhoEsquerdo = inserir(elemento, t.filhoEsquerdo);
		else if (resultadoComparado > 0)
			t.filhoDireto = inserir(elemento, t.filhoDireto);
		else
			this.log += "Elemento " + elemento + "ja sexiste\n";

		// Balanceia o no
		return balancear(t);
	}

	private AvlNode<Type> balancear(AvlNode<Type> t) {
		// Verifica se a árvore está pesando pra esquerda
		if (alturaDo(t.filhoEsquerdo) - alturaDo(t.filhoDireto) > ALLOWED_IMBALANCE)
			// Verifica se a rotação ira ocorrer pro lado de fora
			// Se não é pro lado de dentro
			if (alturaDo(t.filhoEsquerdo.filhoEsquerdo) >= alturaDo(t.filhoEsquerdo.filhoDireto))
				t = rotacaoSimplesEsquerda(t);
			else {
				this.passoRotacaoSimplesLog = false;
				t = rotacaoDuplaEsquerda(t);
				this.passoRotacaoSimplesLog = true;
			}
		else
		// Verifica se a árvore está pesando para direita
		if (alturaDo(t.filhoDireto) - alturaDo(t.filhoEsquerdo) > ALLOWED_IMBALANCE)
			// Verifica se a rotação ira ocorrer pro lado de fora
			// Se não é pro lado de dentro
			if (alturaDo(t.filhoDireto.filhoDireto) >= alturaDo(t.filhoDireto.filhoEsquerdo))
				t = rotacaoSimplesDireita(t);
			else {
				this.passoRotacaoSimplesLog = false;
				t = rotacaoDuplaDireita(t);
				this.passoRotacaoSimplesLog = true;
			}

		// Atualiza a altura
		t.altura = Math.max(alturaDo(t.filhoEsquerdo), alturaDo(t.filhoDireto)) + 1;

		return t;
	}

	private AvlNode<Type> rotacaoDuplaDireita(AvlNode<Type> k3) {
		this.log += "Árovre desbalanceada: " + preOrdem();
		this.log += "Rotação dupla direita involvendo os elementos: " + k3.elemento + " " + k3.filhoDireto.elemento
				+ " " + k3.filhoDireto.filhoEsquerdo.elemento + "\n";
		// ---------------------
		k3.filhoDireto = rotacaoSimplesEsquerda(k3.filhoDireto);
		return rotacaoSimplesDireita(k3);
	}

	// Rotacao Dupla (direita-esquerda)
	private AvlNode<Type> rotacaoDuplaEsquerda(AvlNode<Type> k3) {
		this.log += "Árvore desbalanceada: " + preOrdem();
		this.log += "Rotação dupla esquerda involvendo os elementos: " + k3.elemento + " " + k3.filhoEsquerdo.elemento
				+ " " + k3.filhoEsquerdo.filhoDireto.elemento + "\n";
		// --------------------
		k3.filhoEsquerdo = rotacaoSimplesDireita(k3.filhoEsquerdo);
		return rotacaoSimplesEsquerda(k3);
	}

	private AvlNode<Type> rotacaoSimplesDireita(AvlNode<Type> k2) {
		if (this.passoRotacaoSimplesLog == true) {
			this.log += "Árvore desbalanceada: " + preOrdem();
			this.log += "Rotacao simples direita involvendo os elementos: " + k2.elemento + " "
					+ k2.filhoDireto.elemento + "\n";
		}
		// --------------------
		AvlNode<Type> k1 = k2.filhoDireto;
		k2.filhoDireto = k1.filhoEsquerdo;
		k1.filhoEsquerdo = k2;
		k2.altura = Math.max(alturaDo(k2.filhoEsquerdo), alturaDo(k2.filhoDireto)) + 1;
		k1.altura = Math.max(alturaDo(k1.filhoEsquerdo), alturaDo(k1.filhoDireto)) + 1;
		return k1;
	}

	private AvlNode<Type> rotacaoSimplesEsquerda(AvlNode<Type> k2) {
		if (this.passoRotacaoSimplesLog == true) {
			this.log += "Árvore desbalanceada: " + preOrdem();
			this.log += "Rotacao simples esquerda involvendo os elementos: " + k2.elemento + " "
					+ k2.filhoEsquerdo.elemento + "\n";
		}
		// --------------------
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

	/**
	 * Retorna a altura de uma sub-árvore.
	 * 
	 * @param no
	 *            sub-arvore que quer saber a altura.
	 * @return reotrna -1 se a sub-arvore não existe se ela existe retorna a
	 *         latura dela.
	 */
	public static <T> int alturaDo(AvlNode<T> no) {
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

	/**
	 * Método que checa se a árvore está balanaceada.
	 */
	public void checarBalanco() {
		checarBalanco(raiz);

	}

	/*
	 * Método privado que checa o balanceamento de uma sub-árvore.
	 * 
	 * @param t sub-árvore que vai ser checado o balanceamento.
	 * 
	 * @return retorna -1 se a sub-arvore não existe.
	 */
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

	/**
	 * Método que salva todos elemento em pre-ondem em uma string
	 * 
	 * @return Retorna uma string com os elementos de uma árvore em pre-ordem
	 */
	public String preOrdem() {
		return preOrdem(raiz);
	}

	/*
	 * Método privado que coloca em pre-ordem os elementos de uma sub-árvore em
	 * uma string
	 * 
	 * @return Retorna uma string com os elementos de uma sub-árvore em
	 * pre-ordem
	 */
	private String preOrdem(AvlNode<Type> t) {
		String a = "";
		if (t != null) {
			// System.out.print(t.elemento + " ");
			a = t.elemento + " ";
			a += preOrdem(t.filhoEsquerdo);
			a += preOrdem(t.filhoDireto);
		}
		return a;
	}
	
	/**
	 * Retorna a Raiz da árvore
	 * @return nó raiz
	 */
	public AvlNode<Type> getRaiz() {
		return raiz;
	}

}
