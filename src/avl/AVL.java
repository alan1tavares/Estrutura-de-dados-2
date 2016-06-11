package avl;

public class AVL {
	// Atributos
	private Node root;

	// Métodos

	// Insere um novo no na arvore
	public void insert(int value) {

		// Se nao tiver uma raiz
		if (root == null) {
			root = new Node(value);
			root.setAltura(1);
		}

		// Se tiver uma raiz
		insertNode(root, value);
	}

	private void insertNode(Node node, int value) {
		// Inserir pela direita
		if (value > node.getValue()) {
			if (node.getRight() == null) {

				node.setRight(new Node(value)); // insere o no na direita
				node.getRight().setAltura(1); // atualiza altura

			} else
				insertNode(node.getRight(), value);
		} // Fim do inseirir na direita

		// Inserir pela esquerda
		else if (value < node.getValue()) {
			if (node.getLeft() == null) {

				node.setLeft(new Node(value)); // insere o no na esquerda
				node.getLeft().setAltura(1); // alutalia a altura
			}

			else
				insertNode(node.getLeft(), value);
		} // Fim do inserir na esquerda

		// Atualizar a altura
		node.setAltura(maiorAltura(node.getLeft(), node.getRight()) + 1);
	}

	// Rotação a direita
	private void fazerRotacaoDireita(Node node){
		Node raiz = node.getLeft();
		node.setLeft(raiz.getRight());
		raiz.setRight(node);
		// Atualizar altura
		raiz.setAltura(maiorAltura(raiz.getLeft(), raiz.getRight()) + 1);
		node.setAltura(maiorAltura(node.getLeft(), node.getRight()) + 1);
	}
	
	// Rotacao a esquerda
	private void fazerRotacaoEsquerda(Node node){
		Node raiz = node.getRight();
		node.setRight(raiz.getLeft());
		raiz.setLeft(node);
		// Atualiza altura
		raiz.setAltura(maiorAltura(raiz.getLeft(), raiz.getRight()) + 1);
		node.setAltura(maiorAltura(node.getLeft(), node.getRight()) + 1);
	}
	
	// Rotacao a esquerda-direita
	private void fazerRotacaoEsquerdaDireita(Node node){
		fazerRotacaoEsquerda(node.getLeft());
		fazerRotacaoDireita(node);
	}
	
	// Rotacao a direita-esquerda
	private void fazerRotacaoDireitaEsquerda(Node node){
		fazerRotacaoDireita(node.getRight());
		fazerRotacaoEsquerda(node);
	}

	// Deleta um no na arvore
	public void del() {

	}

	// Busca um nó na arvora
	public void search() {

	}

	public void imprimir() {
		pre_ordem(root);
	}

	public void pos_ordem(Node n) {
		if (n != null) {
			pos_ordem(n.getLeft());
			pos_ordem(n.getRight());
			System.out.print(n.getValue() + " ");
		}
	}

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

	// Compara a altura de dois nos e retorna a altura maio
	public int maiorAltura(Node a, Node b) {
		if(altura(a) >= altura(b))
			return altura(a);
		return altura(b);
	}
	
	// Retorna o fator de balanceamento de um no
	public int fatorBalanceamento(Node node){
		return altura(node.getLeft()) - altura(node.getRight());
		
	}
	
	private int altura (Node node){
		if (node == null)
			return 0;
		return node.getAltura();
	}
}
