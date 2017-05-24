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
		this.auxStrRelatorio = "";

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

	private boolean achou = false;

	@Override
	public void buscar(Type elemento) {
		// Relatorio{
		colocaNoRelatorio("Buscar o elemento " + elemento);
		// }

		this.raiz = buscar(elemento, raiz);

		// Relatorio{
		if (this.auxStrRelatorio != "")
			colocaNoRelatorio(this.auxStrRelatorio);
		this.auxStrRelatorio = "";
		// }

		if (this.raiz != null && this.raiz.elemento != elemento) {
			if (this.raiz.filhoEsquerdo.elemento == elemento) {

				// Relatorio{
				String str = "Efetuar zig envolvendo os elementos " + this.raiz.elemento + " "
						+ this.raiz.filhoEsquerdo.elemento;
				colocaNoRelatorio(str);
				// }

				this.raiz = zig(this.raiz);
			} else if (this.raiz.filhoDireto.elemento == elemento) {

				// Relatorio{
				String str = "Efetuar zag elvolvendo os elementos " + this.raiz.elemento + " "
						+ this.raiz.filhoDireto.elemento;
				colocaNoRelatorio(str);
				// }

				this.raiz = zag(raiz);
			}

			// Relatorio{
			if (this.auxStrRelatorio != "")
				colocaNoRelatorio(this.auxStrRelatorio);
			this.auxStrRelatorio = "";
			// }

		}

		// Relatorio{
		colocaNoRelatorio("Resultado");
		// }
	}

	private AvlNode<Type> buscar(Type elemento, AvlNode<Type> t) {
		// Se o elemento for encontrado
		if (t == null) {
			// Relatorio{
			colocaNoRelatorio("Elemento " + elemento + " não encontrado");
			// }
			this.achou = false;
			return t;
		}
		// Se o elemento for encontrado
		if (t.elemento == elemento) {
			// Relatorio{
			colocaNoRelatorio("Elemento " + elemento + " encontrado");
			// }
			this.achou = true;
			return t;
		}

		// Pecorre até achar o elemento
		if (elemento.compareTo(t.elemento) < 0) {
			t.filhoEsquerdo = buscar(elemento, t.filhoEsquerdo);
		} else {
			t.filhoDireto = buscar(elemento, t.filhoDireto);
		}

		if (t.filhoEsquerdo != null && t.altura >= 2) {
			AvlNode<Type> filhoEsquerdo = t.filhoEsquerdo;
			if (filhoEsquerdo.filhoEsquerdo != null && filhoEsquerdo.filhoEsquerdo.elemento == elemento) {

				// Relatorio {
				if (achou == true) {
					String str = "Efetuar zig-zig envolvendo os elementos " + t.elemento + " " + filhoEsquerdo.elemento
							+ " " + filhoEsquerdo.filhoEsquerdo.elemento;
					colocaNoRelatorio(str);
				}
				// }

				return zig_zig(t);

			} else if (filhoEsquerdo.filhoDireto != null && filhoEsquerdo.filhoDireto.elemento == elemento) {

				// Relatorio
				if (achou == true) {
					String str = "Efetuar zag-zig envolvendo os elementos " + t.elemento + " " + filhoEsquerdo.elemento
							+ " " + filhoEsquerdo.filhoDireto.elemento;
					colocaNoRelatorio(str);
				}
				// }

				return zag_zig(t);
			}

			// Relatoiro
			if (this.auxStrRelatorio != "")
				colocaNoRelatorio(this.auxStrRelatorio);
			this.auxStrRelatorio = "";
			// }
		}

		if (t.filhoDireto != null && t.altura >= 2) {
			AvlNode<Type> filhoDireito = t.filhoDireto;
			if (filhoDireito.filhoDireto != null && filhoDireito.filhoDireto.elemento == elemento) {

				// Relatorio{
				if (achou == true) {
					String str = "Efetuar zag-zag envolvendo os elementos " + t.elemento + " " + filhoDireito.elemento
							+ " " + filhoDireito.filhoDireto.elemento;
					colocaNoRelatorio(str);
				}
				// }

				return zag_zag(t);
			} else if (filhoDireito.filhoEsquerdo != null && filhoDireito.filhoEsquerdo.elemento == elemento) {

				// Relatorio {
				if (achou == true) {
					String str = "Efetuar zig-zag elvolvendo os elementos " + t.elemento + " " + filhoDireito.elemento
							+ " " + filhoDireito.filhoEsquerdo.elemento;
					colocaNoRelatorio(str);
				}
				// }

				return zig_zag(t);
			}

			// Relatoiro
			if (this.auxStrRelatorio != "")
				colocaNoRelatorio(this.auxStrRelatorio);
			this.auxStrRelatorio = "";
			// }
		}

		return t;
	}

	private AvlNode<Type> zag(AvlNode<Type> t) {
		AvlNode<Type> x = t;
		AvlNode<Type> y = t.filhoDireto;

		// Relatorio {
		this.auxStrRelatorio = "Efetuado rotação zag";
		// }

		// Rotação zag
		x.filhoDireto = y.filhoEsquerdo;
		y.filhoEsquerdo = x;

		atualizaAltura1Nivel(y, x);

		return y;
	}

	private AvlNode<Type> zig(AvlNode<Type> t) {
		AvlNode<Type> y = t;
		AvlNode<Type> x = t.filhoEsquerdo;

		// Relatorio {
		this.auxStrRelatorio = "Efetuado rotação zig";
		// }

		// Rotação Zig
		y.filhoEsquerdo = x.filhoDireto;
		x.filhoDireto = y;

		atualizaAltura1Nivel(y, x);

		return x;
	}

	private AvlNode<Type> zig_zag(AvlNode<Type> t) {
		AvlNode<Type> x = t;
		AvlNode<Type> z = t.filhoDireto;
		AvlNode<Type> y = t.filhoDireto.filhoEsquerdo;

		// Relatorio{
		this.auxStrRelatorio = "Efetuado a rotação zig-zag";
		// }

		// Rotação zig-zag
		x.filhoDireto = y.filhoEsquerdo;
		z.filhoEsquerdo = y.filhoDireto;
		y.filhoEsquerdo = x;
		y.filhoDireto = z;

		atualizaAltura2Niveis(z, y, x);

		return y;
	}

	private AvlNode<Type> zag_zig(AvlNode<Type> t) {
		AvlNode<Type> z = t;
		AvlNode<Type> x = t.filhoEsquerdo;
		AvlNode<Type> y = t.filhoEsquerdo.filhoDireto;

		// Relatorio
		this.auxStrRelatorio = "Efetuado rotação zag-zig";
		// }

		// Rotação zag-zig
		x.filhoDireto = y.filhoEsquerdo;
		z.filhoEsquerdo = y.filhoDireto;
		y.filhoEsquerdo = x;
		y.filhoDireto = z;

		atualizaAltura2Niveis(z, y, x);

		return y;
	}

	private AvlNode<Type> zag_zag(AvlNode<Type> t) {
		AvlNode<Type> x = t;
		AvlNode<Type> y = t.filhoDireto;
		AvlNode<Type> z = t.filhoDireto.filhoDireto;

		// Relatorio {
		this.auxStrRelatorio = "Efetuado rotação zag-zag";
		// }

		// Faz a rotação zag-zag
		x.filhoDireto = y.filhoEsquerdo;
		y.filhoEsquerdo = x;
		y.filhoDireto = z.filhoEsquerdo;
		z.filhoEsquerdo = y;

		atualizaAltura2Niveis(z, y, x);

		return z;
	}

	private AvlNode<Type> zig_zig(AvlNode<Type> t) {
		AvlNode<Type> z = t;
		AvlNode<Type> y = t.filhoEsquerdo;
		AvlNode<Type> x = t.filhoEsquerdo.filhoEsquerdo;

		// Relatorio
		this.auxStrRelatorio = "Efetuado rotação zig-zig";
		// }

		// Faz a rotação
		z.filhoEsquerdo = y.filhoDireto;
		y.filhoDireto = z;
		y.filhoEsquerdo = x.filhoDireto;
		x.filhoDireto = y;

		atualizaAltura2Niveis(z, y, x);

		return x;
	}

	/**
	 * @param z
	 * @param y
	 * @param x
	 */
	private void atualizaAltura2Niveis(AvlNode<Type> z, AvlNode<Type> y, AvlNode<Type> x) {
		atualizaAltura1Nivel(y, x);
		z.altura = Math.max(AvlTree.alturaDo(z.filhoEsquerdo), AvlTree.alturaDo(z.filhoDireto)) + 1;
	}

	/**
	 * @param y
	 * @param x
	 */
	private void atualizaAltura1Nivel(AvlNode<Type> y, AvlNode<Type> x) {
		x.altura = Math.max(AvlTree.alturaDo(x.filhoEsquerdo), AvlTree.alturaDo(x.filhoDireto)) + 1;
		y.altura = Math.max(AvlTree.alturaDo(y.filhoEsquerdo), AvlTree.alturaDo(y.filhoDireto)) + 1;
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
