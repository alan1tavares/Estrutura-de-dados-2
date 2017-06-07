package br.com.ed2.relatorio;

import javafx.scene.layout.Pane;

/**
 * Classe que representa uma p�gina do relat�rio. Essa p�gina possui um texto e
 * uma imagem (Pane) que representa esse texto.
 * 
 * @author Alan Tavares
 *
 */
public class Pagina {
	private String texto;
	private Pane imagem;

	/**
	 * Construtor que instancia a classe com um texo e uma imagem.
	 * 
	 * @param texo
	 *            Conte�do de texto da p�gina.
	 * @param imagem
	 *            Imagem que normalmente representa algo do texto. Essa imagem est� dento de um Pane
	 */
	public Pagina(String texo, Pane imagem) {
		this.texto = texo;
		this.imagem = imagem;
	}

	/**
	 * Construtor que instancia uma p�gina s� com o texto.
	 * @param texo Conte�do de texto da p�gina.
	 */
	public Pagina(String texo) {
		this.texto = texo;
	}

	/**
	 * Retorna uma a imagem que est� na p�gina.
	 * @return Pane que que representa uma imagem que est� na p�gina.
	 */
	public Pane getImagem() {
		return imagem;
	}

	/**
	 * Retorna o texto da p�gina
	 * @return Tem como retorno uma String que � o texto da p�gina.
	 */
	public String getTexto() {
		return texto;
	}
}
