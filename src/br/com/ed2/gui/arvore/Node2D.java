package br.com.ed2.gui.arvore;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Node2D{
	private Text valor;
	private Circle circulo;
	private Node desenho;
	
	// Arvore
	private Node2D esquerda;
	private Node2D direita;
	
	
	public Node2D(double raio, int valor) {
		this.circulo = criarCirculo(raio);
		configurarTexto(raio, valor);	
		
		this.desenho = new StackPane(this.circulo, this.valor);
	}
	
	public Node desenho() {
		return desenho;
	}

	public void setDesenho(Node desenho) {
		this.desenho = desenho;
	}
	
	public void setX(int value){
		this.desenho.setTranslateX(value);
	}
	
	public void setY (int value){
		this.desenho.setTranslateY(value);
	}

	private void configurarTexto(double raio, int valor) {
		this.valor = new Text(""+valor);
		this.valor.setFont(new Font(raio));
	}
	
	private Circle criarCirculo(double raio){
		Paint paint = Color.AQUAMARINE;
		return new Circle(raio, paint);
	}
	
	public int getValor(){
		return Integer.parseInt(valor.getText());
	}

	public Node2D getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(Node2D esquerda) {
		this.esquerda = esquerda;
	}

	public Node2D getDireita() {
		return direita;
	}

	public void setDireita(Node2D direita) {
		this.direita = direita;
	}
	
}
