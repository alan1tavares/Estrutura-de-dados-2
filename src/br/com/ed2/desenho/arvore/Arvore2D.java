package br.com.ed2.desenho.arvore;

import br.com.ed2.gui.estrutura.Desenho;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Classe que ira montar uma árvore em 2D. Será passado uma string de elementos
 * e a classe se reponsabilizara de montar a árvore.
 * 
 * @author Alan Tavares
 *
 */
public class Arvore2D implements Desenho {

	private String[] todosOsNos;
	private Pane arvore;
	private Node2D raiz;
	private int larguraDaArvore;
	private int raioDasFolhas;

	/**
	 * Montar uma árvore a partir de uma lista de elementos em pre-ordem.
	 * 
	 * @param arvore
	 *            String de elementos em pre-ordem.
	 * @param altura
	 *            Altura da árvore a ser montada.
	 * @param raio
	 *            Raio do nó.
	 * @param x
	 *            Localização da raiz da árvore no eixo X.
	 * @param y
	 *            Localização da raiz da árvore no eixo Y.
	 * @return Retorna a árvore montada dentro de um pane.
	 */
	public Pane arvorePreOrdem(String arvore, int altura, int raio, int x, int y) {
		// Separa os elementos em um array de string
		this.todosOsNos = arvore.split(" ");
		this.raioDasFolhas = raio;
		int numeroDeFolhasNaMaiorAltura = new Double(Math.pow(2, altura - 1)).intValue();
		// Define a largura da árvore
		this.larguraDaArvore = raio * (numeroDeFolhasNaMaiorAltura * 2) - raio;
		// Cria o container onde será montada a árvore
		this.arvore = new Pane();
		// Dfine o tamnho da tela que tem a árvore dentro
		this.arvore.resize(this.larguraDaArvore*2, this.larguraDaArvore);

		// Chama um método que irá montar a árvore
		montarArvore(x, y);

		this.arvore.setLayoutX(getLarguraDaArvore());
		return this.arvore;
	}
	
	public Pane arvorePreOrdem(String arvore, int altura, int raio) {
		Pane p = arvorePreOrdem(arvore, altura, raio, 0, 0);
		//System.out.println(p.getHeight());
		return p;
	}
	

	/*
	 * Método privado que será chamado para montar a árvore. Se a classe não
	 * tiver os dados dos seus atributos possivelmente irá dar erro.
	 * 
	 * @param x Localização da raiz da árvore no eixo X
	 * 
	 * @param y Localização da raiz da árvore no eixo Y
	 */
	private void montarArvore(int x, int y) {
		for (String string : todosOsNos)
			posicionarNo(raiz, Integer.parseInt(string), x, y, this.larguraDaArvore);
	}

	/*
	 * Método que ira posicionar a árvore dentro de uma Pane. Esse método é
	 * recursivo. Internamente ele cria umas estrutra de uma árvore binária. Foi
	 * o jeito que eu encontrei para posicionar os elementos da árvore.
	 * 
	 * @param node2D O nó será posicionado a partir desse Nó.
	 * 
	 * @param valor O valor a ser colocado dentro do Nó.
	 * 
	 * @param x Localização do Nó pai no eixo X.
	 * 
	 * @param y Localização do Nó pai no eixo Y.
	 * 
	 * @param largura Largura da sub-árvore do nó pai.
	 */
	private void posicionarNo(Node2D node2D, int valor, int x, int y, int largura) {
		// Se a raiz não existir insere um nó nela
		if (this.raiz == null) {
			this.raiz = new Node2D(this.raioDasFolhas, valor);
			this.raiz.setCoordenadas(x, y);
			this.arvore.getChildren().add(raiz.desenho());
			// return para não ter que continuar com o resto do método
			return;
		}

		// Coordenadas usadas para posicionar a linha entre um nó e outro.
		int comecoDaLinhaX = x + this.raioDasFolhas;
		int comecoDaLinhaY = y + this.raioDasFolhas * 2;

		// Atualiza a coordenada do Nó a ser inserido no eixo Y.
		largura /= 2;
		y += 50;

		// Verifica qual lado da raiza é pra criar o nó.
		// Verifica se é do lado esquerdo, se não vai para o direito.
		if (valor < node2D.getValor()) {
			// Atualiza a coordenada do Nó a ser inserido no eixo X
			x -= largura;
			// Se o nó esquerdo não existir insere o Nó.
			// Se exstir chama o método novamente.
			if (node2D.getEsquerda() == null) {
				// Cria o nó, ajeita as coordenadas e insere no lado esquerdo.
				Node2D no = new Node2D(this.raioDasFolhas, valor);
				no.setCoordenadas(x, y);
				node2D.setEsquerda(no);

				// Coordenadas usadas para posicionar a linha entre um nó e
				// outro.
				int fimDaLinhaX = x + this.raioDasFolhas;
				int fimDaLinhaY = y + this.raioDasFolhas;
				// Cria a linha.
				Line line = new Line(comecoDaLinhaX, comecoDaLinhaY, fimDaLinhaX, fimDaLinhaY);
				// Adiciona a linha e o nó na árvore.
				this.arvore.getChildren().addAll(line, no.desenho());

			} else {
				posicionarNo(node2D.getEsquerda(), valor, x, y, largura);
			}
		} else {
			// Atualiza a coordenada do Nó a ser inserido no eixo X
			x += largura;
			// Se o nó direito não existir insere o Nó.
			// Se exstir chama o método novamente.
			if (node2D.getDireita() == null) {
				// Cria o nó, ajeita as coordenadas e insere no lado direito.
				Node2D no = new Node2D(this.raioDasFolhas, valor);
				no.setCoordenadas(x, y);
				node2D.setDireita(no);

				// Coordenadas usadas para posicionar a linha entre um nó e
				// outro.
				int x1 = x + this.raioDasFolhas;
				int y1 = y + this.raioDasFolhas;
				// Cria a linha.
				Line line = new Line(comecoDaLinhaX, comecoDaLinhaY, x1, y1);
				// Adiciona o nó e a linha na árvore.
				this.arvore.getChildren().addAll(line, no.desenho());

			} else
				posicionarNo(node2D.getDireita(), valor, x, y, largura);
		}
	}

	public int getLarguraDaArvore() {
		return larguraDaArvore;
	}

	@Override // Desenho da árvore dentro de um pane
	public Pane desenho() {
		return this.arvore;
	}
}
