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
	
	/* M�todo usado para criaar do circulo do N�.
	 * @param raio double - Raio do circulo a ser criado.
	 * @return Circle - Retorno o desenho do circulo.
	 */
	private Circle criarCirculo(double raio){
		Paint paint = Color.AQUAMARINE;
		return new Circle(raio, paint);
	}	
	
	/* M�todo usado para configurar o texto dentro do N�.
	 * @param tamanhoDaFonte int - Valor do tamnho da fonte do texto.
	 * @param valor int - Valor que vai ser colocado dentro do circulo.
	 */
	private void configurarTexto(int tamanahoDaFonte, int valor) {
		this.valor = new Text(""+valor);
		this.valor.setFont(new Font(tamanahoDaFonte));
	}
	
	/**
	 * Metodo que retorna o desenho do N�.
	 * @return retorna um objeto do tipo Node, que � um container que est� o N�.
	 */
	public Node desenho() {
		return desenho;
	}
	
	/**
	 * M�to que retorna o valor que foi colocado dentro do N�. 
	 * @return o inteiro que est� dentro do N�
	 */
	public int getValor(){
		return Integer.parseInt(valor.getText());
	}

	/**
	 * Usado para saber qual o n� est� a direitra deste. Se n�o tiver retorna null.
	 * @return Retorna o n� que est� a direita. Se n�o tiver retorna null.
	 */
	public Node2D getEsquerda() {
		return esquerda;
	}

	/**
	 * Define um n� a ser posicionado a esuqerda deste n�.
	 * @param esquerda um N� que vai ser colocado na esquerda.
	 */
	public void setEsquerda(Node2D esquerda) {
		this.esquerda = esquerda;
	}

	/**
	 * Usado para saber qual o n� est� a direitra deste. Se n�o tiver retorna null.
	 * @return Retorna o n� que est� a direita. Se n�o tiver retorna null.
	 */
	public Node2D getDireita() {
		return direita;
	}

	/**
	 * Define o n� que vai ser posiciona a direita deste n�.
	 * @param direita um n� que vai ser colocado na diretira.
	 */
	public void setDireita(Node2D direita) {
		this.direita = direita;
	}
		
}
