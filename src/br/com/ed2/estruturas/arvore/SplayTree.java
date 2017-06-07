package br.com.ed2.estruturas.arvore;

import java.util.ArrayList;
import java.util.List;

import br.com.ed2.estruturas.PassoaAPasso;
import br.com.ed2.log.LogDot;
import br.com.ed2.relatorio.Relatorio;

/**
 * Algoritmo da árvore splay. Ela armazena o passo-a-passo em objeto do tipo
 * Relatorio.
 * 
 * @author Alan Tavares
 *
 * @param <Type>
 */
public class SplayTree<Type extends Comparable<? super Type>> implements PassoaAPasso<Type> {

	private AvlNode<Type> raiz;

	private Relatorio relatorio;
	private List<LogDot> log; // Dot

	private boolean flagRotacaoEfetuada = false;

	int nulli = 0;

	public SplayTree() {
		this.log = new ArrayList<>();
	}

	public SplayTree(boolean possuiRelatorio) {
		this.log = new ArrayList<>();
	}

	/**
	 * Método de inserção da splay. A inserção funciona como a de uma árvore
	 * binária.
	 */
	@Override
	public void inserir(Type elemento) {
		LogDot ld = new LogDot();
		this.log.add(ld); // Dot

		this.raiz = inserir(elemento, raiz);

		this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot

	}

	private AvlNode<Type> inserir(Type elemento, AvlNode<Type> t) {
		if (t == null) {
			this.log.get(this.log.size() - 1).setDescricao("Inserido: " + elemento + " na arvore."); // Dot
			return new AvlNode<Type>(elemento);
		}
		int resultadoComparado = elemento.compareTo(t.elemento);

		if (resultadoComparado < 0)
			t.filhoEsquerdo = inserir(elemento, t.filhoEsquerdo);
		else if (resultadoComparado > 0)
			t.filhoDireto = inserir(elemento, t.filhoDireto);
		else {
			this.log.get(this.log.size() - 1).setDescricao(elemento + "ja existe na árvore."); // Dot
		}

		t.altura = Math.max(AvlTree.alturaDo(t.filhoEsquerdo), AvlTree.alturaDo(t.filhoDireto)) + 1;
		return t;

	}

	@Override
	public void deletar(Type elemento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buscar(Type elemento) {

		LogDot ld = new LogDot(); // Dot
		this.log.add(ld); // Dot
		this.log.get(this.log.size() - 1).setDescricao("Buscar o elemento " + elemento + "."); // Dot
		this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot

		this.raiz = buscar(elemento, raiz);

		if (this.flagRotacaoEfetuada) {
			ld = new LogDot(); // Dot
			this.log.add(ld); // Dot
			this.log.get(this.log.size() - 1).setDescricao("Resultado."); // Dot
			this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
			this.flagRotacaoEfetuada = false;
		}

		if (this.raiz != null && this.raiz.elemento != elemento) {
			if (this.raiz.filhoEsquerdo.elemento == elemento) {
				// Zig

				ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				String descricao = "Efetuar zig envolvendo os elementos " + this.raiz.elemento + " "
						+ this.raiz.filhoEsquerdo.elemento; // Dot
				this.log.get(this.log.size() - 1).setDescricao(descricao + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot

				this.raiz = zig(this.raiz);

				ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao("Resultado."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
			} else if (this.raiz.filhoDireto.elemento == elemento) {
				// Zag

				ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				String descricao = "Efetuar zag elvolvendo os elementos " + this.raiz.elemento + " "
						+ this.raiz.filhoDireto.elemento; // Dot
				this.log.get(this.log.size() - 1).setDescricao(descricao + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot

				this.raiz = zag(raiz);

				ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao("Resultado."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot

			}
		}
	}

	private AvlNode<Type> buscar(Type elemento, AvlNode<Type> t) {
		if (t == null) {
			// Se o elemento não existe na árvore

			LogDot ld = new LogDot(); // Dot
			this.log.add(ld); // Dot
			this.log.get(this.log.size() - 1).setDescricao("Elemento " + elemento + " não existe."); // Dot

			return t;
		}
		if (t.elemento == elemento) {
			// Se o elemento for encontrado

			LogDot ld = new LogDot(); // Dot
			this.log.add(ld); // Dot
			this.log.get(this.log.size() - 1).setDescricao("Elemento " + elemento + " encontrado."); // Dot
			this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot

			return t;
		}

		// Pecorre até achar o elemento
		if (elemento.compareTo(t.elemento) < 0) {
			t.filhoEsquerdo = buscar(elemento, t.filhoEsquerdo);
			if (this.flagRotacaoEfetuada) {
				LogDot ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao("Resultado."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
				this.flagRotacaoEfetuada = false;
			}
		} else {
			t.filhoDireto = buscar(elemento, t.filhoDireto);
			if (this.flagRotacaoEfetuada) {
				LogDot ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao("Resultado."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
				this.flagRotacaoEfetuada = false;
			}
		}

		// Rotações

		if (t.filhoEsquerdo != null && t.altura >= 2) {
			AvlNode<Type> filhoEsquerdo = t.filhoEsquerdo;
			if (filhoEsquerdo.filhoEsquerdo != null && filhoEsquerdo.filhoEsquerdo.elemento == elemento) {
				// Zig-Zig

				String descricao = "Efetuar zig-zig envolvendo os elementos " + t.elemento + " "
						+ filhoEsquerdo.elemento + " " + filhoEsquerdo.filhoEsquerdo.elemento; // Dot
				LogDot ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao(descricao + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
				this.flagRotacaoEfetuada = true;

				return zig_zig(t);

			} else if (filhoEsquerdo.filhoDireto != null && filhoEsquerdo.filhoDireto.elemento == elemento) {
				// Zag-Zig

				String descricao = "Efetuar zag-zig envolvendo os elementos " + t.elemento + " "
						+ filhoEsquerdo.elemento + " " + filhoEsquerdo.filhoDireto.elemento; // Dot
				LogDot ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao(descricao + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
				this.flagRotacaoEfetuada = true;

				return zag_zig(t);
			}
		}

		if (t.filhoDireto != null && t.altura >= 2) {
			AvlNode<Type> filhoDireito = t.filhoDireto;
			if (filhoDireito.filhoDireto != null && filhoDireito.filhoDireto.elemento == elemento) {
				// Zag-Zag

				String descricao = "Efetuar zag-zag envolvendo os elementos " + t.elemento + " " + filhoDireito.elemento
						+ " " + filhoDireito.filhoDireto.elemento; // Dot
				LogDot ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao(descricao + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
				this.flagRotacaoEfetuada = true;

				return zag_zag(t);
			} else if (filhoDireito.filhoEsquerdo != null && filhoDireito.filhoEsquerdo.elemento == elemento) {
				// Zig-Zag

				String descricao = "Efetuar zig-zag elvolvendo os elementos " + t.elemento + " " + filhoDireito.elemento
						+ " " + filhoDireito.filhoEsquerdo.elemento; // Dot
				LogDot ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao(descricao + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
				this.flagRotacaoEfetuada = true;

				return zig_zag(t);
			}
		}

		return t;
	}

	private AvlNode<Type> zag(AvlNode<Type> t) {
		AvlNode<Type> x = t;
		AvlNode<Type> y = t.filhoDireto;

		// Rotação zag
		x.filhoDireto = y.filhoEsquerdo;
		y.filhoEsquerdo = x;

		atualizaAltura1Nivel(y, x);

		return y;
	}

	private AvlNode<Type> zig(AvlNode<Type> t) {
		AvlNode<Type> y = t;
		AvlNode<Type> x = t.filhoEsquerdo;

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

	private String dotArvore() {
		return dotArvore(this.raiz);
	}

	private String dotArvore(AvlNode<Type> no) {
		String arvoreDot = "";
		if (no != null) {
			if (no.filhoEsquerdo != null)
				arvoreDot += "\t" + no.elemento + " -> " + no.filhoEsquerdo.elemento + "\n"
						+ dotArvore(no.filhoEsquerdo);
			else {
				arvoreDot += "\tnull" + this.nulli + " [label=\"\",width=.1,style=invis];\n";
				arvoreDot += "\t" + no.elemento + " -> null" + this.nulli + " [style=invis];\n"
						+ dotArvore(no.filhoEsquerdo);
				this.nulli++;
			}
			if (no.filhoDireto != null)
				arvoreDot += "\t" + no.elemento + " -> " + no.filhoDireto.elemento + "\n" + dotArvore(no.filhoDireto);
			else {
				arvoreDot += "\tnull" + this.nulli + " [label=\"\",width=.1,style=invis];\n";
				arvoreDot += "\t" + no.elemento + " -> null" + this.nulli + " [style=invis];\n"
						+ dotArvore(no.filhoDireto);
				this.nulli++;
			}
			if (no == raiz && no.filhoEsquerdo == null && no.filhoDireto == null)
				arvoreDot += "\t" + no.elemento + "\n";
		}
		return arvoreDot;
	}

	public List<LogDot> getLog() {
		return log;
	}
}
