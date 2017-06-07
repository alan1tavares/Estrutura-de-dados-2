package br.com.ed2.relatorio;

import javafx.scene.layout.Pane;

/**
 * Classe que representa uma página do relatório. Essa página possui um texto e
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
	 *            Conteúdo de texto da página.
	 * @param imagem
	 *            Imagem que normalmente representa algo do texto. Essa imagem está dento de um Pane
	 */
	public Pagina(String texo, Pane imagem) {
		this.texto = texo;
		this.imagem = imagem;
	}

	/**
	 * Construtor que instancia uma página só com o texto.
	 * @param texo Conteúdo de texto da página.
	 */
	public Pagina(String texo) {
		this.texto = texo;
	}

	/**
	 * Retorna uma a imagem que está na página.
	 * @return Pane que que representa uma imagem que está na página.
	 */
	public Pane getImagem() {
		return imagem;
	}

	/**
	 * Retorna o texto da página
	 * @return Tem como retorno uma String que é o texto da página.
	 */
	public String getTexto() {
		return texto;
	}
}
