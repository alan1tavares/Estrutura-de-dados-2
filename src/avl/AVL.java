package avl;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AVL {
	// Atributos
	private Node root;

	private int totalElementos;

	// Métodos

	// Insere um novo no na arvore
	public int insert(int value) {
		// Passo a passo
		System.out.println("------AVL------");
		System.out.println("Inserir (" + value + ") ->");
		// ------------//

		// Se nao tiver uma raiz
		if (root == null) {
			root = new Node(value);
			root.setAltura(1);
			totalElementos++;
		}

		// Se tiver uma raiz
		int r = insertNode(root, value);
		root = balancear(root);

		// Passo a passo
		imprimir();
		System.out.println("---------------");
		// ------------//

		return r;
	}

	// Metodo auxiliar para inserir um no
	// retorno = 0 -> deu certo
	// retorno = -1 -> o valor a ser inserido ha existe
	private int insertNode(Node node, int value) {
		int retorno = 0;

		// Se ja possui esse elemento
		if (value == node.getValue()){
			System.out.println(value + " ja existe na arvore");
			return -1;
		}

		// Inserir pela direita
		if (value > node.getValue()) {
			if (node.getRight() == null) {

				node.setRight(new Node(value)); // insere o no na direita
				node.getRight().setAltura(1); // atualiza altura

				// --------//
				totalElementos++;
			} else
				retorno = insertNode(node.getRight(), value);
		} // Fim do inseirir na direita

		// Inserir pela esquerda
		else if (value < node.getValue()) {
			if (node.getLeft() == null) {

				node.setLeft(new Node(value)); // insere o no na esquerda
				node.getLeft().setAltura(1); // alutaliza a altura

				// -------//
				totalElementos++;
			}

			else
				retorno = insertNode(node.getLeft(), value);
		} // Fim do inserir na esquerda

		// Atualizar a altura
		node.setAltura(maiorAltura(node.getLeft(), node.getRight()) + 1);

		// Verificar e fazer balanceamento
		node.setLeft(balancear(node.getLeft()));
		node.setRight(balancear(node.getRight()));

		return retorno;
	}

	private Node balancear(Node node) {
		if (node == null)
			return node;
		if (fatorBalanceamento(node) == 2) {
			
			// Passo a passo
			System.out.println("Arvore antes -> ");
			imprimir();
			//------------//
			
			// Rotacao a direita
			if (fatorBalanceamento(node.getLeft()) == 1) {
				
				// Passo a passo
				System.out.println("RS (" + node.getValue() + ")");
				// ------------ //
				
				node = fazerRotacaoDireita(node);
			}

			// Rotacao dupla a direita
			else if (fatorBalanceamento(node.getLeft()) == -1) {
				// Passo a passo
				System.out.println("RD (" + node.getValue() + ")");
				// ------------//
				node = fazerRotacaoEsquerdaDireita(node);
			}
			
			//Passo a passo
			System.out.println("Arvore depois -> ");
			imprimir();
			//-----------//
		} else {

			if (fatorBalanceamento(node) == -2) {
				
				// Passo a passo
				System.out.print("Arvore antes -> ");
				pre_ordem(root);
				System.out.println();
				//------------//
				
				// Rotacao a esquerda
				if (fatorBalanceamento(node.getRight()) == -1) {
					// Passo a passo
					System.out.println("RS (" + node.getValue() + ")");
					// -----------//
					node = fazerRotacaoEsquerda(node);
				}
				// Rotacao dupla a esquerda
				else if (fatorBalanceamento(node.getRight()) == 1) {
					// Passo a passo
					System.out.println("RD (" + node.getValue() + ")");
					// --------------//
					node = fazerRotacaoDireitaEsquerda(node);
				}
				
				//Passo a passo
				System.out.print("Arvore depois -> ");
				pre_ordem(root);
				
			}
		} // Fim do else

		node.setAltura(maiorAltura(node.getLeft(), node.getRight()) + 1);
		return node;
	}

	// Rotação a direita
	private Node fazerRotacaoDireita(Node node) {
		Node raiz = node.getLeft();
		node.setLeft(raiz.getRight());
		raiz.setRight(node);
		// Atualizar altura
		raiz.setAltura(maiorAltura(raiz.getLeft(), raiz.getRight()) + 1);
		node.setAltura(maiorAltura(node.getLeft(), node.getRight()) + 1);
		return raiz;
	}

	// Rotacao a esquerda
	private Node fazerRotacaoEsquerda(Node node) {
		Node raiz = node.getRight();
		node.setRight(raiz.getLeft());
		raiz.setLeft(node);
		// Atualiza altura
		raiz.setAltura(maiorAltura(raiz.getLeft(), raiz.getRight()) + 1);
		node.setAltura(maiorAltura(node.getLeft(), node.getRight()) + 1);
		return raiz;
	}

	// Rotacao dupla a direita
	private Node fazerRotacaoEsquerdaDireita(Node node) {
		node.setLeft(fazerRotacaoEsquerda(node.getLeft()));
		return fazerRotacaoDireita(node);
	}

	// Rotacao duplae a esquerda
	private Node fazerRotacaoDireitaEsquerda(Node node) {
		node.setRight(fazerRotacaoDireita(node.getRight()));
		return fazerRotacaoEsquerda(node);
	}

	// Deleta um no na arvore
	public void del() {

	}

	// Busca um nó na arvora
	public void search() {

	}

	// Compara a altura de dois nos e retorna a altura maio
	public int maiorAltura(Node a, Node b) {
		if (altura(a) >= altura(b))
			return altura(a);
		return altura(b);
	}

	// Retorna o fator de balanceamento de um no
	public int fatorBalanceamento(Node node) {
		if (node == null)
			return 0;
		return altura(node.getLeft()) - altura(node.getRight());

	}

	private int altura(Node node) {
		if (node == null)
			return 0;
		return node.getAltura();
	}

	public int getAltura() {
		return altura(root);
	}

	/**
	 * Metodos de exibicao da avl
	 */

	// Exibir a avl
	public void imprimir() {
		pre_ordem(root);
		System.out.println();
	}

	// Exibi a avl em pos-ordem
	public void pos_ordem(Node n) {
		if (n != null) {
			pos_ordem(n.getLeft());
			pos_ordem(n.getRight());
			System.out.print(n.getValue() + " ");
		}
	}

	// Exibi a avl em pre-ordem
	public void pre_ordem(Node n) {
		if (n != null) {
			// Visita a raiz.
			System.out.print(n.getValue() + "[" + n.getAltura() + "]" + " ");
			// Percorrer a sua subárvore esquerda em pré-ordem.
			pre_ordem(n.getLeft());
			// Percorrer a sua subárvore direita em pré-ordem.
			pre_ordem(n.getRight());
		}
	}

	public int getTotalElementos() {
		return totalElementos;
	}

	// Retorna uma lista de elements que possui na arvore
	public ArrayList<Integer> getListaElementos() {
		ArrayList<Integer> lista = new ArrayList<>(); // Lista de inteiros
		lista.addAll(getListaElementos(this.root)); // Adiciona todos os
													// elementos da avl na lista
		return lista; // Retorna a lista
	}

	// Metodo auxiliar do getListaElementos
	private ArrayList<Integer> getListaElementos(Node n) {
		ArrayList<Integer> lista = new ArrayList<>();
		// Se o no existir
		if (n != null) {
			lista.add(n.getValue());// Adiciona o valor de 'n' na lista
			// Adiciona na lista os elementos que estao a esquerda
			lista.addAll(getListaElementos(n.getLeft()));
			// Adiciona na lista os elementos que estao a direita
			lista.addAll(getListaElementos(n.getRight()));
		}

		return lista;
	}
}
