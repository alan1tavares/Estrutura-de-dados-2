package br.com.ed2.estruturas.relatorio;

import java.util.ArrayList;
import java.util.List;

import br.com.ed2.gui.estrutura.Desenho;
import javafx.scene.layout.Pane;

/**
 * Classe feita para criar um relatório que armazene as interações das
 * estruturas
 * 
 * @author Alan Tavares
 *
 */
public class Relatorio {
	protected List<Pane> desenhos; // São os desenhos
	protected List<String> texto; // As interações efetuadas

	public Relatorio() {
		this.texto = new ArrayList<>();
		this.desenhos = new ArrayList<>();
	}

	/**
	 * Adiciona um texto ao relatório
	 * 
	 * @param texto
	 *            um texto que é colocado no relatório
	 */
	public void adicionar(String texto) {
		this.texto.add(texto);
	}
	
	public String getTexto(){
		String str = "";
		for (String s : texto) {
			str += s + "\n";
		}
		return str;
	}
	
	public List<Pane> getDesenhos() {
		return desenhos;
	}
}
