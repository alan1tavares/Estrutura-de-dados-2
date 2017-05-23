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
		else
			this.auxStrRelatorio += "\nElemento ja existe na Árvore";

		t.altura = Math.max(AvlTree.alturaDo(t.filhoEsquerdo), AvlTree.alturaDo(t.filhoDireto)) + 1;
		return t;

	}

	@Override
	public void deletar(Type elemento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buscar(Type elemento) {
		this.raiz = buscar(elemento, raiz);
		
	}

	private AvlNode<Type> buscar(Type elemento, AvlNode<Type> t) {
		if(t == null || t.elemento == elemento)
			return t;
		
		// Pecorre até achar o elemento
		if(elemento.compareTo(t.elemento) < 0)
			t.filhoEsquerdo = buscar(elemento, t.filhoEsquerdo);
		else
			t.filhoDireto = buscar(elemento, t.filhoDireto);
		
		if(t.filhoEsquerdo != null && t.filhoEsquerdo.altura == 2){
			AvlNode<Type> filhoEsquerdo = t.filhoEsquerdo;
			if(filhoEsquerdo.filhoEsquerdo != null && filhoEsquerdo.filhoEsquerdo.elemento == elemento)
				return zig_zig(t);
			if(filhoEsquerdo.filhoDireto != null && filhoEsquerdo.filhoDireto.elemento == elemento)
				return zag_zig(t);
		}
		
		if(t.filhoDireto != null && t.filhoDireto.altura == 2){
			AvlNode<Type> filhoDireito = t.filhoDireto;
			if(filhoDireito.filhoDireto != null && filhoDireito.filhoDireto.elemento == elemento)
				return zag_zag(t);
			if(filhoDireito.filhoEsquerdo != null && filhoDireito.filhoEsquerdo.elemento == elemento)
				return zig_zag(t);
		}
		
		return t;
	}

	private AvlNode<Type> zag_zag(AvlNode<Type> t) {
		AvlNode<Type> x = t;
		AvlNode<Type> y = t.filhoDireto;
		AvlNode<Type> z = t.filhoDireto.filhoDireto;
		// Faz a rotação
		x.filhoDireto = y.filhoEsquerdo;
		y.filhoEsquerdo = x;
		y.filhoDireto = z.filhoEsquerdo;
		z.filhoEsquerdo = y;		
		
		return z;
	}

	private AvlNode<Type> zig_zig(AvlNode<Type> t) {
		AvlNode<Type> z = t;
		AvlNode<Type> y = t.filhoEsquerdo;
		AvlNode<Type> x = t.filhoEsquerdo.filhoEsquerdo;
		// Faz a rotação
		z.filhoEsquerdo = y.filhoDireto;
		y.filhoDireto = z;
		y.filhoEsquerdo = x.filhoDireto;
		x.filhoDireto = y;
		
		return x;
	}

	@Override
	public Relatorio getRelatorio() {
		return this.relatorio;
	}

	// Cuida da vizualização

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
}
