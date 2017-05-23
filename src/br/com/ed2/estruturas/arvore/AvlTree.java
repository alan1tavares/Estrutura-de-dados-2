package br.com.ed2.estruturas.avl;

import br.com.ed2.desenho.arvore.Arvore2D;
import br.com.ed2.estruturas.PassoaAPasso;
import br.com.ed2.estruturas.relatorio.Pagina;
import br.com.ed2.estruturas.relatorio.Relatorio;
import br.com.ed2.estruturas.relatorio.RotuloRelatorioAvl;
import javafx.scene.layout.Pane;

public class AvlTree<Type extends Comparable<? super Type>> implements PassoaAPasso<Type> {
	private AvlNode<Type> raiz;

	private Relatorio relatorio;

	private static final int ALLOWED_IMBALANCE = 1;

	private String auxStrRelatorio = "";

	// Construtores

	/**
	 * Construtor que indica se realiza uma relat�io ou n�o.
	 * 
	 * @param possuiRelatorio
	 *            Verdadeiro se � pra criar o relat�rio. Falso se n�o � pra
	 *            fazer o relat�rio.
	 */
	public AvlTree(boolean possuiRelatorio) {
		if (possuiRelatorio == true)
			this.relatorio = new Relatorio();
	}

	public AvlTree() {

	}

	// M�todos p�blicos

	@Override
	public void inserir(Type elemento) {

		// String para colocar no relat�rio
		this.auxStrRelatorio = RotuloRelatorioAvl.ENTRADA + elemento + RotuloRelatorioAvl.FINAL_TEXTO;

		this.raiz = inserir(elemento, raiz); // Tenta inseirir o elemento na

		if (this.relatorio != null)

			colocaNoRelatorio(auxStrRelatorio);
	}

	// M�todos privado da �rvore.

	/**
	 * Insere um elemento em uma sub�rvore e depois retorna essa sub�vore
	 * balanceadas.
	 * 
	 * @param elemento
	 *            Elemento a ser colocado na �rvore. Se ele ja existir ir�
	 *            lan�ar uma exce��o.
	 * @param t
	 *            Sub�rvore que ira receber o @param elemento.
	 * @return Retorna a sub�rvore @param t balanceada.
	 */
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
			this.auxStrRelatorio += "\nElemento ja existe na �rvore";

		// Balanceia o no
		return balancear(t);
	}

	private AvlNode<Type> balancear(AvlNode<Type> t) {
		// Verifica se a �rvore est� pesando pra esquerda
		if (alturaDo(t.filhoEsquerdo) - alturaDo(t.filhoDireto) > ALLOWED_IMBALANCE)
			// Verifica se a rota��o ira ocorrer pro lado de fora
			// Se n�o � pro lado de dentro
			if (alturaDo(t.filhoEsquerdo.filhoEsquerdo) >= alturaDo(t.filhoEsquerdo.filhoDireto))
				t = rotacaoSimplesEsquerda(t, false);
			else {
				t = rotacaoDuplaEsquerda(t);
			}
		else
		// Verifica se a �rvore est� pesando para direita
		if (alturaDo(t.filhoDireto) - alturaDo(t.filhoEsquerdo) > ALLOWED_IMBALANCE)
			// Verifica se a rota��o ira ocorrer pro lado de fora
			// Se n�o � pro lado de dentro
			if (alturaDo(t.filhoDireto.filhoDireto) >= alturaDo(t.filhoDireto.filhoEsquerdo))
				t = rotacaoSimplesDireita(t, false);
			else {
				t = rotacaoDuplaDireita(t);
			}

		// Atualiza a altura
		t.altura = Math.max(alturaDo(t.filhoEsquerdo), alturaDo(t.filhoDireto)) + 1;

		return t;
	}

	/**
	 * Faz a ROTACAO DUPLA direita no n� k3.
	 * 
	 * @param k3
	 *            N� pai de uma sub�rovre que vai sofrer a rota��o.
	 * @return Retorna a sub�rvore depois de ter realizada a rota��o.
	 */
	private AvlNode<Type> rotacaoDuplaDireita(AvlNode<Type> k3) {
		// Relat�rio
		if (this.relatorio != null) {
			// Coloca no relatorio essa p�gina.
			// Mostra que a �rvore est� desbaanceada no elemento k3.
			colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k3);

			// Coloca no relat�rio essa p�gina.
			// Mostra o tipo de rota��o que dever� fazer.
			String str = "Efetuar Rota��o dupla direita involvendo os elementos, " + k3.elemento + " "
					+ k3.filhoDireto.elemento + " " + k3.filhoDireto.filhoEsquerdo.elemento
					+ RotuloRelatorioAvl.FINAL_TEXTO;
			colocaNoRelatorio(str, this.raiz.altura + 2);
		}

		// Algoritmo de rota��o
		k3.filhoDireto = rotacaoSimplesEsquerda(k3.filhoDireto, true);
		AvlNode<Type> rotacaoSimplesDireita = rotacaoSimplesDireita(k3, true);
		// --------------------

		// Relat�rio
		if (this.relatorio != null) {
			// Coloca no rela�trio essa p�gina.
			// Mostra como ficou depois da retoa��o ter seido efetuada.
			this.auxStrRelatorio = "Rota��o dupla direita realizada";
		}
		// Retorna a sub�rvore
		return rotacaoSimplesDireita;
	}

	/**
	 * Faz uma ROTACAO DUPLA esquerda no k3. direita-esquerda.
	 * 
	 * @param k3
	 *            N� pai de uma sub�rovre que vai sofrer a rota��o.
	 * @return Retorna a sub�rvore depois de realizada a rota��o.
	 */
	private AvlNode<Type> rotacaoDuplaEsquerda(AvlNode<Type> k3) {

		// Relatorio
		if (this.relatorio != null) {
			// Coloca no relatorio essa p�gina.
			// Mostra que a �rvore est� desbaanceada no elemento k3.
			colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k3);

			// Coloca no relat�rio essa p�gina.
			// Mostra o tipo de rota��o que dever� fazer.
			String str = RotuloRelatorioAvl.ARVORE_DESBALANCEADA
					+ "Efetuar Rota��o dupla esquerda involvendo nos elementos, " + k3.elemento + " "
					+ k3.filhoEsquerdo.elemento + " " + k3.filhoEsquerdo.filhoDireto.elemento
					+ RotuloRelatorioAvl.FINAL_TEXTO;
			colocaNoRelatorio(str, raiz.altura + 2);
		}

		// Algoritmo de rota��o
		k3.filhoEsquerdo = rotacaoSimplesDireita(k3.filhoEsquerdo, true);
		AvlNode<Type> rotacaoSimplesEsquerda = rotacaoSimplesEsquerda(k3, true);
		// --------------------

		// Relat�rio
		if (this.relatorio != null) {
			// Coloca no rela�trio essa p�gina.
			// Mostra como ficou depois da retoa��o ter seido efetuada.
			this.auxStrRelatorio = "Rota��o dupla esquerda realizada";
		}
		// Retorna a sub�rvore
		return rotacaoSimplesEsquerda;
	}

	/**
	 * Faz ROTACAO SIMPLIES direita em um n�
	 * 
	 * @param k2
	 *            n� de uma sub�rvore que ser� aplicado a rota��o
	 * @param b
	 *            var�vel utilizada
	 * @return retorna o n� da sub�rvore depois de ter efetuado a rota��o
	 */
	private AvlNode<Type> rotacaoSimplesDireita(AvlNode<Type> k2, boolean b) {

		// Relatorio
		if (this.relatorio != null && b == false) {
			// Coloca no relatorio essa p�gina.
			// Mostra que a �rvore est� desbaanceada no elemento k3.
			colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k2);

			// Coloca no relat�rio essa p�gina.
			// Mostra o tipo de rota��o que dever� fazer
			String str = RotuloRelatorioAvl.ARVORE_DESBALANCEADA
					+ "Efetuar Rota��o simples direita involvendo os elementos, " + k2.elemento + " "
					+ k2.filhoDireto.elemento + RotuloRelatorioAvl.FINAL_TEXTO;
			colocaNoRelatorio(str, raiz.altura + 2);
		}

		// Algoritmo de rota��o
		AvlNode<Type> k1 = k2.filhoDireto;
		k2.filhoDireto = k1.filhoEsquerdo;
		k1.filhoEsquerdo = k2;
		k2.altura = Math.max(alturaDo(k2.filhoEsquerdo), alturaDo(k2.filhoDireto)) + 1;
		k1.altura = Math.max(alturaDo(k1.filhoEsquerdo), alturaDo(k1.filhoDireto)) + 1;
		// ---------------------

		// Relatorio
		if (this.relatorio != null && b == false) {
			// Coloca no rela�trio essa p�gina.
			// Mostra como ficou depois da retoa��o ter seido efetuada.
			this.auxStrRelatorio = "Rota��o simples direita realizada";
		}
		// Retorna a sub�rvore
		return k1;
	}

	private AvlNode<Type> rotacaoSimplesEsquerda(AvlNode<Type> k2, boolean b) {
		// Relatorio
		if (this.relatorio != null && b == false) {
			// Coloca no relatorio essa p�gina.
			// Mostra que a �rvore est� desbaanceada no elemento k3.
			colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k2);

			// Coloca no relat�rio essa p�gina.
			// Mostra o tipo de rota��o que dever� fazer
			String str = RotuloRelatorioAvl.ARVORE_DESBALANCEADA
					+ "Efetuar Rota��o simples esqueda involvendo os elementos: " + k2.elemento + " "
					+ k2.filhoEsquerdo.elemento + RotuloRelatorioAvl.FINAL_TEXTO;
			colocaNoRelatorio(str, this.raiz.altura + 2);

		}

		// Agortimo de rota��o
		AvlNode<Type> k1 = k2.filhoEsquerdo;
		// Faz rota��o
		k2.filhoEsquerdo = k1.filhoDireto;
		k1.filhoDireto = k2;
		// Atualiza a altura
		k2.altura = Math.max(alturaDo(k2.filhoEsquerdo), alturaDo(k2.filhoDireto)) + 1;
		k1.altura = Math.max(alturaDo(k1.filhoEsquerdo), alturaDo(k1.filhoDireto)) + 1;
		// k1 � o pai
		// --------------------

		// Relat�rio
		if (this.relatorio != null && b == false) {
			// Coloca no rela�trio essa p�gina.
			// Mostra como ficou depois da retoa��o ter seido efetuada.
			this.auxStrRelatorio = "Rota��o simples esquerda realizada";
		}
		return k1;
	}

	/**
	 * Retorna a altura de uma sub-�rvore.
	 * 
	 * @param no
	 *            sub-arvore que quer saber a altura.
	 * @return reotrna -1 se a sub-arvore n�o existe se ela existe retorna a
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
	 * M�todo que checa se a �rvore est� balanaceada.
	 */
	public void checarBalanco() {
		checarBalanco(raiz);

	}

	/*
	 * M�todo privado que checa o balanceamento de uma sub-�rvore.
	 * 
	 * @param t sub-�rvore que vai ser checado o balanceamento.
	 * 
	 * @return retorna -1 se a sub-arvore n�o existe.
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
	 * M�todo que salva todos elemento em pre-ondem em uma string
	 * 
	 * @return Retorna uma string com os elementos de uma �rvore em pre-ordem
	 */
	public String preOrdem() {
		return preOrdem(raiz);
	}

	/*
	 * M�todo privado que coloca em pre-ordem os elementos de uma sub-�rvore em
	 * uma string
	 * 
	 * @return Retorna uma string com os elementos de uma sub-�rvore em
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
	 * Retorna a Raiz da �rvore
	 * 
	 * @return n� raiz
	 */
	public AvlNode<Type> getRaiz() {
		return raiz;
	}

	public Relatorio getRelatorio() {
		return this.relatorio;
	}

	/*
	 * M�todos privados
	 */

	/**
	 * M�todo auxiliar para colocar uma p�gina no relat�rio. Este m�todo associa
	 * automaticamente a �rvore com o texto passado.
	 * 
	 * @param str
	 *            O texto a ser colcado no relat�rio
	 * @param altura
	 *            A altura da �rvore que vai ser desenhada dentro de uma pane e
	 *            colocado depois no relat�rio associado a ou texto passado
	 */
	private void colocaNoRelatorio(String str, int altura) {
		// Monta o desenho da �rvore e coloca em um Pane
		Arvore2D arvore2D = new Arvore2D();
		Pane desenho = arvore2D.arvorePreOrdem(preOrdem(), altura, 14);
		// --------------
		this.relatorio.inserirPagina(new Pagina(str, desenho));
	}

	/**
	 * M�todo auxiliar para colocar uma p�gina no relat�rio. Este m�todo associa
	 * automaticamente a �rvore com o texto passado.
	 * 
	 * @param str
	 *            O texto a ser colcado no relat�rio
	 */
	private void colocaNoRelatorio(String str) {
		// Monta o desenho da �rvore e coloca em um Pane
		colocaNoRelatorio(str, this.raiz.altura + 1);
	}

	/**
	 * M�todo que insere no relat�rio que a �rvore est� desbalanceada.
	 * 
	 * @param k3
	 *            elemento onde a �rvore est� desbalanceada.
	 */
	private void colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(AvlNode<Type> k3) {
		this.auxStrRelatorio += "\n" + RotuloRelatorioAvl.ARVORE_DESBALANCEADA + "no " + k3.elemento;
		colocaNoRelatorio(this.auxStrRelatorio, raiz.altura + 2);
	}
}
