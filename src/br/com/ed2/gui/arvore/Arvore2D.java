package br.com.ed2.gui.arvore;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Arvore2D {
	private String[] noArrays;
	private Pane arvore;
	private int totalDeFolhas;
	private int largura;
	private int raioDasFolhas;

	// Arvore
	Node2D raiz;

	public Pane arvorePreOrdem(String arvore, int altura, int raio, int x, int y) {
		this.noArrays = arvore.split(" ");
		this.totalDeFolhas = new Double(Math.pow(2, altura - 1)).intValue();
		this.raioDasFolhas = raio;
		this.largura = raio * (totalDeFolhas * 2) - raio;
		System.out.println(largura);
		this.arvore = new Pane();
		
		inserir(raiz, Integer.parseInt(noArrays[0]), x, y);
		for (String string : noArrays) {
			if(noArrays[0] == string) continue;
			inserir(raiz, Integer.parseInt(string), x, y);
		}

		return this.arvore;
	}

	int f = this.largura;
	private void inserir(Node2D node2D, int valor, int x, int y) {
		// Inserindo um no na raiz
		
		if (this.raiz == null) {
			this.raiz = new Node2D(this.raioDasFolhas, valor);
			this.raiz.setX(x);
			this.raiz.setY(y);
			this.arvore.getChildren().add(raiz.desenho());
			return;
		}
		y += (y / 2);

		if (valor < node2D.getValor()) {
			x -= this.largura / 2;
			if (node2D.getEsquerda() == null) {
				Node2D aux = new Node2D(this.raioDasFolhas, valor);
				aux.setX(x);
				aux.setY(y);
				System.out.println(y + ", " + valor);
				node2D.setEsquerda(aux);
				this.arvore.getChildren().add(aux.desenho());
			} else {
				inserir(node2D.getEsquerda(), valor, x, y);
			}
		} else {
			x += this.largura / 2;
			if (node2D.getDireita() == null) {
				Node2D aux = new Node2D(this.raioDasFolhas, valor);
				aux.setX(x);
				aux.setY(y);
				node2D.setDireita(aux);
				this.arvore.getChildren().add(aux.desenho());
			} else
				inserir(node2D.getDireita(), valor, x, y);
		}
	}
}
