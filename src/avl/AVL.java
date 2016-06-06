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
		// Inserir na direita
		if (value > node.getValue()) {
			if (node.getRight() == null) {

				node.setRight(new Node(value));

				// Atualizar altura
				node.getRight().setAltura(1);
				if (node.getAltura() == 1)
					node.setAltura(2);

			} else {
				insertNode(node.getRight(), value);
				// Atualiza altura
				if (node.getRight().getAltura()+1 > node.getAltura())
					node.setAltura(node.getRight().getAltura() + 1);
			}
		}

		// Inserir na esquerda
		else if (value < node.getValue()) {
			if (node.getLeft() == null) {
				node.setLeft(new Node(value));
				
				node.getLeft().setAltura(1);
				
				if(node.getAltura() == 1)
					node.setAltura(2);
			}

			else{
				insertNode(node.getLeft(), value);
			
				// Atualiza altura
				if(node.getLeft().getAltura() + 1 > node.getAltura())
					node.setAltura(node.getLeft().getAltura() + 1);
			}
		}
	}

	// Deleta um no na arvore
	public void del() {

	}

	// Busca um nó na arvora
	public void search() {

	}

	// Balanceia a árvore
	public void balance() {

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
			System.out.print(n.getValue()+"["+n.getAltura()+"]" + " ");
			// Percorrer a sua subárvore esquerda em pré-ordem.
			pre_ordem(n.getLeft());
			// Percorrer a sua subárvore direita em pré-ordem.
			pre_ordem(n.getRight());
		}
	}

	public void atualizarAlturaDoNo(Node n) {

	}
}
