package br.com.ed2.gui.arvore;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Classe que ira representar uma árvore em duas dimensões.
 * 
 * @author Alan Tavares
 *
 */
public class Arvore2D {

	private String[] todosOsNos;
	private Pane arvore;
	private Node2D raiz;
	private int larguraDaArvore;
	private int raioDasFolhas;

	public Pane getArvore() {
		return arvore;
	}

	public Pane arvorePreOrdem(String arvore, int altura, int raio, int x, int y) {
		this.todosOsNos = arvore.split(" ");
		this.raioDasFolhas = raio;
		int numeroDeFolhasNaMaiorAltura = new Double(Math.pow(2, altura - 1)).intValue();
		this.larguraDaArvore = raio * (numeroDeFolhasNaMaiorAltura * 2) - raio;
		this.arvore = new Pane();

		for (String string : todosOsNos) 
			inserir(raiz, Integer.parseInt(string), x, y, this.larguraDaArvore);
			
		return this.arvore;
	}

	private void inserir(Node2D node2D, int valor, int x, int y, int h) {
		// Inserindo um no na raiz
		if (this.raiz == null) {
			this.raiz = new Node2D(this.raioDasFolhas, valor);
			this.raiz.desenho().setTranslateX(x);
			this.raiz.desenho().setTranslateY(y);
			this.arvore.getChildren().add(raiz.desenho());
			return;
		}
		//Apagar isso
		int x2 = x + this.raioDasFolhas;
		int y2 = y + this.raioDasFolhas*2;
		//-----------
		
		h /= 2;
		y += h;

		if (valor < node2D.getValor()) {
			x -= h;
			if (node2D.getEsquerda() == null) {
				Node2D aux = new Node2D(this.raioDasFolhas, valor);
				aux.desenho().setTranslateX(x);
				aux.desenho().setTranslateY(y);
				node2D.setEsquerda(aux);

				
				//Apagar isso
				int x1 = x + this.raioDasFolhas;
				int y1 = y + this.raioDasFolhas;
				Line line = new Line(x2,y2, x1, y1);
				//
								
				this.arvore.getChildren().addAll(line, aux.desenho());				
				
			} else {
				inserir(node2D.getEsquerda(), valor, x, y,h);
			}
		} else {
			x += h;
			if (node2D.getDireita() == null) {
				Node2D aux = new Node2D(this.raioDasFolhas, valor);
				aux.desenho().setTranslateX(x);
				aux.desenho().setTranslateY(y);
				node2D.setDireita(aux);
				
				//Apagar isso
				int x1 = x + this.raioDasFolhas;
				int y1 = y + this.raioDasFolhas;
				Line line = new Line(x2,y2, x1, y1);
				//
				
				this.arvore.getChildren().addAll(line,aux.desenho());
				
				
			} else
				inserir(node2D.getDireita(), valor, x, y,h);
		}
	}
}
