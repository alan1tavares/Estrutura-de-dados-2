package br.com.ed2.estruturas.arvore;

import br.com.ed2.desenho.arvore.Arvore2D;
import br.com.ed2.estruturas.PassoaAPasso;
import br.com.ed2.estruturas.relatorio.Pagina;
import br.com.ed2.estruturas.relatorio.Relatorio;
import br.com.ed2.estruturas.relatorio.RotuloRelatorioArvore;
import javafx.scene.layout.Pane;

public class SplayTree<Type extends Comparable<? super Type>> implements PassoaAPasso<Type> {

	private AvlNode<Type> raiz;

	private Relatorio relatorio;

	private String auxStrRelatorio = "";

	public SplayTree() {
	}

	public SplayTree(boolean possuiRelatorio) {
		if (possuiRelatorio == true)
			this.relatorio = new Relatorio();
	}

	@Override
	public void inserir(Type elemento) {
		this.auxStrRelatorio = RotuloRelatorioArvore.ENTRADA + elemento + RotuloRelatorioArvore.FINAL_TEXTO;
		this.raiz = inserir(elemento, raiz);
		if (this.relatorio != null)
			colocaNoRelatorio(auxStrRelatorio);

	}

	private AvlNode<Type> inserir(Type elemento, AvlNode<Type> t) {
		if (t == null)
			return new AvlNode<Type>(elemento);
		int resultadoComparado = elemento.compareTo(t.elemento);

		if (resultadoComparado < 0)
			t.filhoEsquerdo = inserir(elemento, t.filhoEsquerdo);
		else if (resultadoComparado > 0)
			t.filhoDireto = inserir(elemento, t.filhoDireto);
		// else
		// TODO os elementos s�o iguais

		t.altura = Math.max(AvlTree.alturaDo(t.filhoEsquerdo), AvlTree.alturaDo(t.filhoDireto)) + 1;
		return t;

	}

	@Override
	public void deletar(Type elemento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buscar(Type elemento) {
		// TODO Auto-generated method stub

	}

	@Override
	public Relatorio getRelatorio() {
		return this.relatorio;
	}

	// Cuida da vizualiza��o

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
}
