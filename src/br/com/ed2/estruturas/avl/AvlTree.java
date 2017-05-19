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
	 * Construtor que indica se realiza uma relatóio ou não.
	 * 
	 * @param possuiRelatorio
	 *            Verdadeiro se é pra criar o relatório. Falso se não é pra
	 *            fazer o relatório.
	 */
	public AvlTree(boolean possuiRelatorio) {
		if (possuiRelatorio == true)
			this.relatorio = new Relatorio();
	}

	public AvlTree() {

	}

	// Métodos públicos

	@Override
	public void inserir(Type elemento) {

		// String para colocar no relatório
		this.auxStrRelatorio = RotuloRelatorioAvl.ENTRADA + elemento + RotuloRelatorioAvl.FINAL_TEXTO;

		this.raiz = inserir(elemento, raiz); // Tenta inseirir o elemento na

		if (this.relatorio != null)

			colocaNoRelatorio(auxStrRelatorio);
	}

	// Métodos privado da árvore.

	/**
	 * Insere um elemento em uma subárvore e depois retorna essa subávore
	 * balanceadas.
	 * 
	 * @param elemento
	 *            Elemento a ser colocado na árvore. Se ele ja existir irá
	 *            lançar uma exceção.
	 * @param t
	 *            Subárvore que ira receber o @param elemento.
	 * @return Retorna a subárvore @param t balanceada.
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
			this.auxStrRelatorio += "\nElemento ja existe na Árvore";

		// Balanceia o no
		return balancear(t);
	}

	private AvlNode<Type> balancear(AvlNode<Type> t) {
		// Verifica se a árvore está pesando pra esquerda
		if (alturaDo(t.filhoEsquerdo) - alturaDo(t.filhoDireto) > ALLOWED_IMBALANCE)
			// Verifica se a rotação ira ocorrer pro lado de fora
			// Se não é pro lado de dentro
			if (alturaDo(t.filhoEsquerdo.filhoEsquerdo) >= alturaDo(t.filhoEsquerdo.filhoDireto))
				t = rotacaoSimplesEsquerda(t, false);
			else {
				t = rotacaoDuplaEsquerda(t);
			}
		else
		// Verifica se a árvore está pesando para direita
		if (alturaDo(t.filhoDireto) - alturaDo(t.filhoEsquerdo) > ALLOWED_IMBALANCE)
			// Verifica se a rotação ira ocorrer pro lado de fora
			// Se não é pro lado de dentro
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
	 * Faz a ROTACAO DUPLA direita no nó k3.
	 * 
	 * @param k3
	 *            Nó pai de uma subárovre que vai sofrer a rotação.
	 * @return Retorna a subárvore depois de ter realizada a rotação.
	 */
	private AvlNode<Type> rotacaoDuplaDireita(AvlNode<Type> k3) {
		// Relatório
		if (this.relatorio != null) {
			// Coloca no relatorio essa página.
			// Mostra que a árvore está desbaanceada no elemento k3.
			colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k3);

			// Coloca no relatório essa página.
			// Mostra o tipo de rotação que deverá fazer.
			String str = "Efetuar Rotação dupla direita involvendo os elementos, " + k3.elemento + " "
					+ k3.filhoDireto.elemento + " " + k3.filhoDireto.filhoEsquerdo.elemento
					+ RotuloRelatorioAvl.FINAL_TEXTO;
			colocaNoRelatorio(str, this.raiz.altura + 2);
		}

		// Algoritmo de rotação
		k3.filhoDireto = rotacaoSimplesEsquerda(k3.filhoDireto, true);
		AvlNode<Type> rotacaoSimplesDireita = rotacaoSimplesDireita(k3, true);
		// --------------------

		// Relatório
		if (this.relatorio != null) {
			// Coloca no relaótrio essa página.
			// Mostra como ficou depois da retoação ter seido efetuada.
			this.auxStrRelatorio = "Rotação dupla direita realizada";
		}
		// Retorna a subárvore
		return rotacaoSimplesDireita;
	}

	/**
	 * Faz uma ROTACAO DUPLA esquerda no k3. direita-esquerda.
	 * 
	 * @param k3
	 *            Nó pai de uma subárovre que vai sofrer a rotação.
	 * @return Retorna a subárvore depois de realizada a rotação.
	 */
	private AvlNode<Type> rotacaoDuplaEsquerda(AvlNode<Type> k3) {

		// Relatorio
		if (this.relatorio != null) {
			// Coloca no relatorio essa página.
			// Mostra que a árvore está desbaanceada no elemento k3.
			colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k3);

			// Coloca no relatório essa página.
			// Mostra o tipo de rotação que deverá fazer.
			String str = RotuloRelatorioAvl.ARVORE_DESBALANCEADA
					+ "Efetuar Rotação dupla esquerda involvendo nos elementos, " + k3.elemento + " "
					+ k3.filhoEsquerdo.elemento + " " + k3.filhoEsquerdo.filhoDireto.elemento
					+ RotuloRelatorioAvl.FINAL_TEXTO;
			colocaNoRelatorio(str, raiz.altura + 2);
		}

		// Algoritmo de rotação
		k3.filhoEsquerdo = rotacaoSimplesDireita(k3.filhoEsquerdo, true);
		AvlNode<Type> rotacaoSimplesEsquerda = rotacaoSimplesEsquerda(k3, true);
		// --------------------

		// Relatório
		if (this.relatorio != null) {
			// Coloca no relaótrio essa página.
			// Mostra como ficou depois da retoação ter seido efetuada.
			this.auxStrRelatorio = "Rotação dupla esquerda realizada";
		}
		// Retorna a subárvore
		return rotacaoSimplesEsquerda;
	}

	/**
	 * Faz ROTACAO SIMPLIES direita em um nó
	 * 
	 * @param k2
	 *            nó de uma subárvore que será aplicado a rotação
	 * @param b
	 *            varável utilizada
	 * @return retorna o nó da subárvore depois de ter efetuado a rotação
	 */
	private AvlNode<Type> rotacaoSimplesDireita(AvlNode<Type> k2, boolean b) {

		// Relatorio
		if (this.relatorio != null && b == false) {
			// Coloca no relatorio essa página.
			// Mostra que a árvore está desbaanceada no elemento k3.
			colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k2);

			// Coloca no relatório essa página.
			// Mostra o tipo de rotação que deverá fazer
			String str = RotuloRelatorioAvl.ARVORE_DESBALANCEADA
					+ "Efetuar Rotação simples direita involvendo os elementos, " + k2.elemento + " "
					+ k2.filhoDireto.elemento + RotuloRelatorioAvl.FINAL_TEXTO;
			colocaNoRelatorio(str, raiz.altura + 2);
		}

		// Algoritmo de rotação
		AvlNode<Type> k1 = k2.filhoDireto;
		k2.filhoDireto = k1.filhoEsquerdo;
		k1.filhoEsquerdo = k2;
		k2.altura = Math.max(alturaDo(k2.filhoEsquerdo), alturaDo(k2.filhoDireto)) + 1;
		k1.altura = Math.max(alturaDo(k1.filhoEsquerdo), alturaDo(k1.filhoDireto)) + 1;
		// ---------------------

		// Relatorio
		if (this.relatorio != null && b == false) {
			// Coloca no relaótrio essa página.
			// Mostra como ficou depois da retoação ter seido efetuada.
			this.auxStrRelatorio = "Rotação simples direita realizada";
		}
		// Retorna a subárvore
		return k1;
	}

	private AvlNode<Type> rotacaoSimplesEsquerda(AvlNode<Type> k2, boolean b) {
		// Relatorio
		if (this.relatorio != null && b == false) {
			// Coloca no relatorio essa página.
			// Mostra que a árvore está desbaanceada no elemento k3.
			colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k2);

			// Coloca no relatório essa página.
			// Mostra o tipo de rotação que deverá fazer
			String str = RotuloRelatorioAvl.ARVORE_DESBALANCEADA
					+ "Efetuar Rotação simples esqueda involvendo os elementos: " + k2.elemento + " "
					+ k2.filhoEsquerdo.elemento + RotuloRelatorioAvl.FINAL_TEXTO;
			colocaNoRelatorio(str, this.raiz.altura + 2);

		}

		// Agortimo de rotação
		AvlNode<Type> k1 = k2.filhoEsquerdo;
		// Faz rotação
		k2.filhoEsquerdo = k1.filhoDireto;
		k1.filhoDireto = k2;
		// Atualiza a altura
		k2.altura = Math.max(alturaDo(k2.filhoEsquerdo), alturaDo(k2.filhoDireto)) + 1;
		k1.altura = Math.max(alturaDo(k1.filhoEsquerdo), alturaDo(k1.filhoDireto)) + 1;
		// k1 é o pai
		// --------------------

		// Relatório
		if (this.relatorio != null && b == false) {
			// Coloca no relaótrio essa página.
			// Mostra como ficou depois da retoação ter seido efetuada.
			this.auxStrRelatorio = "Rotação simples esquerda realizada";
		}
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
	 * 
	 * @return nó raiz
	 */
	public AvlNode<Type> getRaiz() {
		return raiz;
	}

	public Relatorio getRelatorio() {
		return this.relatorio;
	}

	/*
	 * Métodos privados
	 */

	/**
	 * Método auxiliar para colocar uma página no relatório. Este método associa
	 * automaticamente a árvore com o texto passado.
	 * 
	 * @param str
	 *            O texto a ser colcado no relatório
	 * @param altura
	 *            A altura da árvore que vai ser desenhada dentro de uma pane e
	 *            colocado depois no relatório associado a ou texto passado
	 */
	private void colocaNoRelatorio(String str, int altura) {
		// Monta o desenho da árvore e coloca em um Pane
		Arvore2D arvore2D = new Arvore2D();
		Pane desenho = arvore2D.arvorePreOrdem(preOrdem(), altura, 14);
		// --------------
		this.relatorio.inserirPagina(new Pagina(str, desenho));
	}

	/**
	 * Método auxiliar para colocar uma página no relatório. Este método associa
	 * automaticamente a árvore com o texto passado.
	 * 
	 * @param str
	 *            O texto a ser colcado no relatório
	 */
	private void colocaNoRelatorio(String str) {
		// Monta o desenho da árvore e coloca em um Pane
		colocaNoRelatorio(str, this.raiz.altura + 1);
	}

	/**
	 * Método que insere no relatório que a árvore está desbalanceada.
	 * 
	 * @param k3
	 *            elemento onde a árvore está desbalanceada.
	 */
	private void colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(AvlNode<Type> k3) {
		this.auxStrRelatorio += "\n" + RotuloRelatorioAvl.ARVORE_DESBALANCEADA + "no " + k3.elemento;
		colocaNoRelatorio(this.auxStrRelatorio, raiz.altura + 2);
	}
}
