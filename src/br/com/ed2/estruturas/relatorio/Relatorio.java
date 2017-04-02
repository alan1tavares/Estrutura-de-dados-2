package br.com.ed2.estruturas.relatorio;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;

/**
 * Classe feita para criar um relat�rio que armazene as intera��es das
 * estruturas
 * 
 * @author Alan Tavares
 *
 */
public class Relatorio {
	protected List<Pane> figuras; // S�o os desenhos
	protected List<String> texto; // As intera��es efetuadas

	public Relatorio() {
		this.texto = new ArrayList<>();
		this.figuras = new ArrayList<>();
	}

	/**
	 * Adiciona um texto ao relat�rio
	 * 
	 * @param texto
	 *            um texto que � colocado no relat�rio
	 */
	public void adicionar(String texto) {
		this.texto.add(texto);
	}
}
