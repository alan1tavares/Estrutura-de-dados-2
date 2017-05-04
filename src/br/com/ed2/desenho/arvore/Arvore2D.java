package br.com.ed2.desenho.arvore;

import br.com.ed2.gui.estrutura.Desenho;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Classe que ira montar uma �rvore em 2D. Ser� passado uma string de elementos
 * e a classe se reponsabilizara de montar a �rvore.
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
	 * Montar uma �rvore a partir de uma lista de elementos em pre-ordem.
	 * 
	 * @param arvore
	 *            String de elementos em pre-ordem.
	 * @param altura
	 *            Altura da �rvore a ser montada.
	 * @param raio
	 *            Raio do n�.
	 * @param x
	 *            Localiza��o da raiz da �rvore no eixo X.
	 * @param y
	 *            Localiza��o da raiz da �rvore no eixo Y.
	 * @return Retorna a �rvore montada dentro de um pane.
	 */
	public Pane arvorePreOrdem(String arvore, int altura, int raio, int x, int y) {
		// Separa os elementos em um array de string
		this.todosOsNos = arvore.split(" ");
		this.raioDasFolhas = raio;
		int numeroDeFolhasNaMaiorAltura = new Double(Math.pow(2, altura - 1)).intValue();
		// Define a largura da �rvore
		this.larguraDaArvore = raio * (numeroDeFolhasNaMaiorAltura * 2) - raio;
		// Cria o container onde ser� montada a �rvore
		this.arvore = new Pane();
		// Dfine o tamnho da tela que tem a �rvore dentro
		this.arvore.resize(this.larguraDaArvore*2, this.larguraDaArvore);

		// Chama um m�todo que ir� montar a �rvore
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
	 * M�todo privado que ser� chamado para montar a �rvore. Se a classe n�o
	 * tiver os dados dos seus atributos possivelmente ir� dar erro.
	 * 
	 * @param x Localiza��o da raiz da �rvore no eixo X
	 * 
	 * @param y Localiza��o da raiz da �rvore no eixo Y
	 */
	private void montarArvore(int x, int y) {
		for (String string : todosOsNos)
			posicionarNo(raiz, Integer.parseInt(string), x, y, this.larguraDaArvore);
	}

	/*
	 * M�todo que ira posicionar a �rvore dentro de uma Pane. Esse m�todo �
	 * recursivo. Internamente ele cria umas estrutra de uma �rvore bin�ria. Foi
	 * o jeito que eu encontrei para posicionar os elementos da �rvore.
	 * 
	 * @param node2D O n� ser� posicionado a partir desse N�.
	 * 
	 * @param valor O valor a ser colocado dentro do N�.
	 * 
	 * @param x Localiza��o do N� pai no eixo X.
	 * 
	 * @param y Localiza��o do N� pai no eixo Y.
	 * 
	 * @param largura Largura da sub-�rvore do n� pai.
	 */
	private void posicionarNo(Node2D node2D, int valor, int x, int y, int largura) {
		// Se a raiz n�o existir insere um n� nela
		if (this.raiz == null) {
			this.raiz = new Node2D(this.raioDasFolhas, valor);
			this.raiz.setCoordenadas(x, y);
			this.arvore.getChildren().add(raiz.desenho());
			// return para n�o ter que continuar com o resto do m�todo
			return;
		}

		// Coordenadas usadas para posicionar a linha entre um n� e outro.
		int comecoDaLinhaX = x + this.raioDasFolhas;
		int comecoDaLinhaY = y + this.raioDasFolhas * 2;

		// Atualiza a coordenada do N� a ser inserido no eixo Y.
		largura /= 2;
		y += 50;

		// Verifica qual lado da raiza � pra criar o n�.
		// Verifica se � do lado esquerdo, se n�o vai para o direito.
		if (valor < node2D.getValor()) {
			// Atualiza a coordenada do N� a ser inserido no eixo X
			x -= largura;
			// Se o n� esquerdo n�o existir insere o N�.
			// Se exstir chama o m�todo novamente.
			if (node2D.getEsquerda() == null) {
				// Cria o n�, ajeita as coordenadas e insere no lado esquerdo.
				Node2D no = new Node2D(this.raioDasFolhas, valor);
				no.setCoordenadas(x, y);
				node2D.setEsquerda(no);

				// Coordenadas usadas para posicionar a linha entre um n� e
				// outro.
				int fimDaLinhaX = x + this.raioDasFolhas;
				int fimDaLinhaY = y + this.raioDasFolhas;
				// Cria a linha.
				Line line = new Line(comecoDaLinhaX, comecoDaLinhaY, fimDaLinhaX, fimDaLinhaY);
				// Adiciona a linha e o n� na �rvore.
				this.arvore.getChildren().addAll(line, no.desenho());

			} else {
				posicionarNo(node2D.getEsquerda(), valor, x, y, largura);
			}
		} else {
			// Atualiza a coordenada do N� a ser inserido no eixo X
			x += largura;
			// Se o n� direito n�o existir insere o N�.
			// Se exstir chama o m�todo novamente.
			if (node2D.getDireita() == null) {
				// Cria o n�, ajeita as coordenadas e insere no lado direito.
				Node2D no = new Node2D(this.raioDasFolhas, valor);
				no.setCoordenadas(x, y);
				node2D.setDireita(no);

				// Coordenadas usadas para posicionar a linha entre um n� e
				// outro.
				int x1 = x + this.raioDasFolhas;
				int y1 = y + this.raioDasFolhas;
				// Cria a linha.
				Line line = new Line(comecoDaLinhaX, comecoDaLinhaY, x1, y1);
				// Adiciona o n� e a linha na �rvore.
				this.arvore.getChildren().addAll(line, no.desenho());

			} else
				posicionarNo(node2D.getDireita(), valor, x, y, largura);
		}
	}

	public int getLarguraDaArvore() {
		return larguraDaArvore;
	}

	@Override // Desenho da �rvore dentro de um pane
	public Pane desenho() {
		return this.arvore;
	}
}
