package br.com.ed2.gui.arvore;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Classe usada para criar os nos de uma arvore.
 * Esses nos sao compostos por uma circulo que possui uma cor de fundo e um texto dentro do circulo.
 * @author Alan Tavares
 *
 */
public class Node2D{
	private Text valor;
	private Circle circulo;
	private Node desenho;
	private int raio;
	
	// Arvore
	private Node2D esquerda;
	private Node2D direita;
	
	/**
	 * Construtor que cria de forma direta o no.
	 * @param raio int - Raio do circulo.
	 * @param valor int - Valor que ira vir dentro do circulo.
	 */
	public Node2D(int raio, int valor) {
		this.circulo = criarCirculo(raio);
		configurarTexto(raio, valor);	
		
		//Apagar isso
		Rectangle rectangle = new Rectangle(valor, valor);
		//-----------
		
		this.desenho = new StackPane(this.circulo, this.valor);
	}
	
	/* Método usado para criaar do circulo do Nó.
	 * @param raio double - Raio do circulo a ser criado.
	 * @return Circle - Retorno o desenho do circulo.
	 */
	private Circle criarCirculo(double raio){
		Paint paint = Color.AQUAMARINE;
		return new Circle(raio, paint);
	}	
	
	/* Método usado para configurar o texto dentro do Nó.
	 * @param tamanhoDaFonte int - Valor do tamnho da fonte do texto.
	 * @param valor int - Valor que vai ser colocado dentro do circulo.
	 */
	private void configurarTexto(int tamanahoDaFonte, int valor) {
		this.valor = new Text(""+valor);
		this.valor.setFont(new Font(tamanahoDaFonte));
	}
	
	/**
	 * Metodo que retorna o desenho do Nó.
	 * @return retorna um objeto do tipo Node, que é um container que está o Nó.
	 */
	public Node desenho() {
		return desenho;
	}
	
	/**
	 * Méto que retorna o valor que foi colocado dentro do Nó. 
	 * @return o inteiro que está dentro do Nó
	 */
	public int getValor(){
		return Integer.parseInt(valor.getText());
	}

	/**
	 * Usado para saber qual o nó está a direitra deste. Se não tiver retorna null.
	 * @return Retorna o nó que está a direita. Se não tiver retorna null.
	 */
	public Node2D getEsquerda() {
		return esquerda;
	}

	/**
	 * Define um nó a ser posicionado a esuqerda deste nó.
	 * @param esquerda um Nó que vai ser colocado na esquerda.
	 */
	public void setEsquerda(Node2D esquerda) {
		this.esquerda = esquerda;
	}

	/**
	 * Usado para saber qual o nó está a direitra deste. Se não tiver retorna null.
	 * @return Retorna o nó que está a direita. Se não tiver retorna null.
	 */
	public Node2D getDireita() {
		return direita;
	}

	/**
	 * Define o nó que vai ser posiciona a direita deste nó.
	 * @param direita um nó que vai ser colocado na diretira.
	 */
	public void setDireita(Node2D direita) {
		this.direita = direita;
	}
		
}
