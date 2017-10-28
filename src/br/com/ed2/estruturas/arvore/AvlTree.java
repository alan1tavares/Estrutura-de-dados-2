package br.com.ed2.estruturas.arvore;

import java.util.ArrayList;
import java.util.List;

import br.com.ed2.estruturas.PassoaAPasso;
import br.com.ed2.gui.desenho.arvore.Arvore2D;
import br.com.ed2.log.LogDot;
import br.com.ed2.relatorio.Pagina;
import br.com.ed2.relatorio.Relatorio;
import br.com.ed2.relatorio.RotuloRelatorioArvore;
import javafx.scene.layout.Pane;

public class AvlTree<Type extends Comparable<? super Type>> implements PassoaAPasso<Type> {
	private AvlNode<Type> raiz;

	private Relatorio relatorio;
	private List<LogDot> log; // Dot
	int nulli = 0; // Dot
	private boolean flagElementoInserido = false;
	private boolean flagFezRotacao = false;

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
		this.log = new ArrayList<LogDot>();
	}

	public AvlTree() {
	}

	// Métodos públicos

	@Override
	public void inserir(Type elemento) {
		this.raiz = inserir(elemento, raiz); // Tenta inseirir o elemento na

		if (this.flagFezRotacao == true) { // Dot
			LogDot ld = new LogDot(); // Dot
			this.log.add(ld); // Dot
			this.log.get(this.log.size() - 1).setDescricao("Resultado."); // Dot
			this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
			this.flagFezRotacao = false;
		}
		
		// Dot
		if (this.raiz.elemento == elemento) {
			LogDot ld = new LogDot(); // Dot
			this.log.add(ld); // Dot
			this.log.get(this.log.size() - 1).setDescricao(elemento + " inserido."); // Dot
			this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
			this.flagElementoInserido = false;
		}
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
		if (t == null) {
			this.flagElementoInserido = true;
			return new AvlNode<Type>(elemento);
		}

		int resultadoComparado = elemento.compareTo(t.elemento);

		// Insiro o no na esquera se o no for menor que a raiz
		// caso contrario insiro na direita
		if (resultadoComparado < 0)
			t.filhoEsquerdo = inserir(elemento, t.filhoEsquerdo);
		else if (resultadoComparado > 0)
			t.filhoDireto = inserir(elemento, t.filhoDireto);
		else {
			// Quando o elemento já existe na árvore.
			LogDot ld = new LogDot(); // Dot
			this.log.add(ld); // Dot
			this.log.get(this.log.size() - 1).setDescricao(elemento + " Ja existe."); // Dot
			this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
		}
		
		if (this.flagFezRotacao == true) { // Dot
			LogDot ld = new LogDot(); // Dot
			this.log.add(ld); // Dot
			this.log.get(this.log.size() - 1).setDescricao("Resultado."); // Dot
			this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
			this.flagFezRotacao = false;
		}

		// Dot
		if (this.flagElementoInserido == true) {
			LogDot ld = new LogDot(); // Dot
			this.log.add(ld); // Dot
			this.log.get(this.log.size() - 1).setDescricao(elemento + " inserido."); // Dot
			this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
			this.flagElementoInserido = false;
		}

		// Balanceia o no
		return balancear(t);
	}

	private AvlNode<Type> balancear(AvlNode<Type> t) {
		// Verifica se a árvore está pesando pra esquerda
		if (alturaDo(t.filhoEsquerdo) - alturaDo(t.filhoDireto) > ALLOWED_IMBALANCE) {
			// Verifica se a rotação ira ocorrer pro lado de fora
			// Se não é pro lado de dentro
			if (alturaDo(t.filhoEsquerdo.filhoEsquerdo) >= alturaDo(t.filhoEsquerdo.filhoDireto)) {

				LogDot ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao("Árvore desebalanceada no elemento " + t.elemento + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot

				ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao("Efetuar rotação simples esquerda com os elementos "
						+ t.elemento + " " + t.filhoEsquerdo.elemento + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
				this.flagFezRotacao = true;

				t = rotacaoSimplesEsquerda(t, false);
			} else {
				LogDot ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao("Árvore desebalanceada no elemento " + t.elemento); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot

				ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1)
						.setDescricao("Efetuar rotacação dupla esquerda com os elementos " + t.elemento + " "
								+ t.filhoEsquerdo.elemento + " " + t.filhoEsquerdo.filhoDireto.elemento + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
				this.flagFezRotacao = true; // Dot

				t = rotacaoDuplaEsquerda(t);
			}
		} else
		// Verifica se a árvore está pesando para direita
		if (alturaDo(t.filhoDireto) - alturaDo(t.filhoEsquerdo) > ALLOWED_IMBALANCE) {
			// Verifica se a rotação ira ocorrer pro lado de fora
			// Se não é pro lado de dentro
			if (alturaDo(t.filhoDireto.filhoDireto) >= alturaDo(t.filhoDireto.filhoEsquerdo)) {
				LogDot ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao("Árvore desebalanceada no elemento " + t.elemento + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot

				ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao("Efetuar rotação simples direita com os elementos "
						+ t.elemento + " " + t.filhoDireto.elemento + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
				this.flagFezRotacao = true; // Dot

				t = rotacaoSimplesDireita(t, false);
			} else {
				LogDot ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao("Árvore desebalanceada no elemento " + t.elemento + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot

				ld = new LogDot(); // Dot
				this.log.add(ld); // Dot
				this.log.get(this.log.size() - 1).setDescricao("Efetuar rotação dupla direita com os elementos "
						+ t.elemento + " " + t.filhoDireto.elemento + " " + t.filhoDireto.filhoEsquerdo.elemento + "."); // Dot
				this.log.get(this.log.size() - 1).setGrafo(dotArvore()); // Dot
				this.flagFezRotacao = true;

				t = rotacaoDuplaDireita(t);
			}
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
		// if (this.relatorio != null) {
		// // Coloca no relatorio essa página.
		// // Mostra que a árvore está desbaanceada no elemento k3.
		// colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k3);
		//
		// // Coloca no relatório essa página.
		// // Mostra o tipo de rotação que deverá fazer.
		// String str = "Efetuar Rotação dupla direita envolvendo os elementos,
		// " + k3.elemento + " "
		// + k3.filhoDireto.elemento + " " +
		// k3.filhoDireto.filhoEsquerdo.elemento
		// + RotuloRelatorioArvore.FINAL_TEXTO;
		// colocaNoRelatorio(str, this.raiz.altura + 2);
		// }

		// Algoritmo de rotação
		k3.filhoDireto = rotacaoSimplesEsquerda(k3.filhoDireto, true);
		AvlNode<Type> rotacaoSimplesDireita = rotacaoSimplesDireita(k3, true);
		// --------------------

		// Relatório
		// if (this.relatorio != null) {
		// // Coloca no relaótrio essa página.
		// // Mostra como ficou depois da retoação ter seido efetuada.
		// this.auxStrRelatorio = "Rotação dupla direita realizada";
		// }
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

		// // Relatorio
		// if (this.relatorio != null) {
		// // Coloca no relatorio essa página.
		// // Mostra que a árvore está desbaanceada no elemento k3.
		// colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k3);
		//
		// // Coloca no relatório essa página.
		// // Mostra o tipo de rotação que deverá fazer.
		// String str = RotuloRelatorioArvore.ARVORE_DESBALANCEADA
		// + "Efetuar Rotação dupla esquerda envolvendo nos elementos, " +
		// k3.elemento + " "
		// + k3.filhoEsquerdo.elemento + " " +
		// k3.filhoEsquerdo.filhoDireto.elemento
		// + RotuloRelatorioArvore.FINAL_TEXTO;
		// colocaNoRelatorio(str, raiz.altura + 2);
		// }

		// Algoritmo de rotação
		k3.filhoEsquerdo = rotacaoSimplesDireita(k3.filhoEsquerdo, true);
		AvlNode<Type> rotacaoSimplesEsquerda = rotacaoSimplesEsquerda(k3, true);
		// --------------------

		// Relatório
		// if (this.relatorio != null) {
		// // Coloca no relaótrio essa página.
		// // Mostra como ficou depois da retoação ter seido efetuada.
		// this.auxStrRelatorio = "Rotação dupla esquerda realizada";
		// }
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

		// // Relatorio
		// if (this.relatorio != null && b == false) {
		// // Coloca no relatorio essa página.
		// // Mostra que a árvore está desbaanceada no elemento k3.
		// colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k2);
		//
		// // Coloca no relatório essa página.
		// // Mostra o tipo de rotação que deverá fazer
		// String str = RotuloRelatorioArvore.ARVORE_DESBALANCEADA
		// + "Efetuar Rotação simples direita envolvendo os elementos, " +
		// k2.elemento + " "
		// + k2.filhoDireto.elemento + RotuloRelatorioArvore.FINAL_TEXTO;
		// colocaNoRelatorio(str, raiz.altura + 2);
		// }

		// Algoritmo de rotação
		AvlNode<Type> k1 = k2.filhoDireto;
		k2.filhoDireto = k1.filhoEsquerdo;
		k1.filhoEsquerdo = k2;
		k2.altura = Math.max(alturaDo(k2.filhoEsquerdo), alturaDo(k2.filhoDireto)) + 1;
		k1.altura = Math.max(alturaDo(k1.filhoEsquerdo), alturaDo(k1.filhoDireto)) + 1;
		// ---------------------

		// Relatorio
		// if (this.relatorio != null && b == false) {
		// // Coloca no relaótrio essa página.
		// // Mostra como ficou depois da retoação ter seido efetuada.
		// this.auxStrRelatorio = "Rotação simples direita realizada";
		// }
		// Retorna a subárvore
		return k1;
	}

	private AvlNode<Type> rotacaoSimplesEsquerda(AvlNode<Type> k2, boolean b) {
		// Relatorio
		// if (this.relatorio != null && b == false) {
		// // Coloca no relatorio essa página.
		// // Mostra que a árvore está desbaanceada no elemento k3.
		// colocaNoRelatoioQueAArvoreEstaDesbalanceadaNo(k2);
		//
		// // Coloca no relatório essa página.
		// // Mostra o tipo de rotação que deverá fazer
		// String str = RotuloRelatorioArvore.ARVORE_DESBALANCEADA
		// + "Efetuar Rotação simples esqueda envolvendo os elementos: " +
		// k2.elemento + " "
		// + k2.filhoEsquerdo.elemento + RotuloRelatorioArvore.FINAL_TEXTO;
		// colocaNoRelatorio(str, this.raiz.altura + 2);
		//
		// }

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

		// // Relatório
		// if (this.relatorio != null && b == false) {
		// // Coloca no relaótrio essa página.
		// // Mostra como ficou depois da retoação ter seido efetuada.
		// this.auxStrRelatorio = "Rotação simples esquerda realizada";
		// }
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
		this.auxStrRelatorio += "\n" + RotuloRelatorioArvore.ARVORE_DESBALANCEADA + "no " + k3.elemento;
		colocaNoRelatorio(this.auxStrRelatorio, raiz.altura + 2);
	}

	@Override
	public List<LogDot> getLog() {
		return this.log;
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
}
