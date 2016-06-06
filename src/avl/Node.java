package avl;

public class Node {
	// Atributos
	private Node left;
	private Node right;
	
	private int altura;
	
	private int value;

	// Construtor
	public Node(int value){
		this.value = value;
	}
	
	// Metodos
	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int compareTo(){
		return 0;
	}
}
