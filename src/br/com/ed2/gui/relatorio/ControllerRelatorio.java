package br.com.ed2.gui.relatorio;

import br.com.ed2.estruturas.relatorio.Relatorio;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * 
 * Classe Controller da View relatorio. Ela tem o ojetivo de exibir as paginas
 * do Relat�rio. Esse relat�rio seria o passo a passo de alguma estrutra
 * espec�fica, os passos seria as p�ginas.
 * 
 * @author Alan Tavares
 * 
 */
public class ControllerRelatorio /* implements Initializable */ {

	// Atributos utilizado na view

	@FXML
	private Label lbRelatorio;
	@FXML
	private Label lbPaginaAtual;
	@FXML
	private Label lbTotalDePaginas;
	@FXML
	private Pane paneDesenhoEstrutura;
	@FXML
	private BorderPane borderPane;

	// Atributos da "Normais"
	// Indice que indica em qual passo est� a View em rela��o ao que est� no
	// relat�rio.
	private int indexPaginaAtual = 0;
	private Relatorio relatorio;

	// Vari�veis para teste
	// private String entrada = "46 39 22 5 25 36 3 45 1 43 8 28 57 47 10 21 48
	// 4 53 32";

	// Eventos de Bot�es da View {

	/**
	 * Evento do bot�o que avan�a para o proximo passo
	 */
	public void btProximo() {
		if (this.relatorio != null) {
			if (this.indexPaginaAtual < this.relatorio.totalDePaginas()) {
				this.indexPaginaAtual += 1;
				atualizaPagina();
			}
			atualizaAlturaLarguraPaneEstrutura();
		}
	}

	/**
	 * Evento do bot�o que volta para o passo anterior
	 */
	public void btAnterior() {
		if (this.relatorio != null) {
			if (this.indexPaginaAtual > 0) {
				indexPaginaAtual -= 1;
				atualizaPagina();

			}
			atualizaAlturaLarguraPaneEstrutura();
		}
	}

	// } Fim dos eventos de Bot�es

	/**
	 * Escolhe o passo a ser exibido na view.
	 * 
	 * @param i
	 *            Indica��o do passo a ser exibido.
	 * @return Retorna a pane que possui todos os n�s da View
	 */
	public Pane escolherPagina(int i) {
		if (this.relatorio != null) {
			boolean iValido = i < this.relatorio.totalDePaginas() && i >= 0;
			if (iValido) {
				this.indexPaginaAtual = i;
				atualiza();
			}
		}
		return getBorderPane();
	}

	/**
	 * M�todo utilizado para atualizar a janela. Se exisitir um relatorio a
	 * janela � atualizada.
	 */
	public void atualiza() {
		if (this.relatorio != null) {

			atualizaPagina();
			atualizaAlturaLarguraPaneEstrutura();

		} else {
			System.out.println("Erro relatorio n�o foi instanciado");
		}
	}

	/**
	 * Retorna um border pane que � o n� raiz
	 * 
	 * @return BorderPane
	 */
	public BorderPane getBorderPane() {
		return borderPane;
	}

	/**
	 * Retorna o pane que est� armazenada o desenho da estrutura
	 * 
	 * @return Pane
	 */
	public Pane getPaneDesenhoEstrutura() {
		return paneDesenhoEstrutura;
	}

	/**
	 * Define o relatorio que ser� exibido na View
	 * 
	 * @param relatorio
	 *            Objeto relat�rio que ser� definido para ser ebido na view
	 */
	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
		this.indexPaginaAtual = 0;
		atualiza();
	}

	// M�todos privados auxiliares

	/**
	 * Atualiza o desenho, a numera��o e o texto. A atualiza��o � relizada
	 * baseda no indexPaginaAtual
	 */
	private void atualizaPagina() {
		// Verifica se o indice atual est� dentro dos limites, se n�o tiver
		// ecoclhe-se uma p�gina dentro dos limites.
		boolean indexMaior = this.indexPaginaAtual > this.relatorio.totalDePaginas();
		boolean indexMenor = this.indexPaginaAtual < 0;
		if (indexMenor)
			this.indexPaginaAtual = 0;
		else if (indexMaior)
			this.indexPaginaAtual = this.relatorio.totalDePaginas();

		// Atualiza o texto do passo a passo
		this.lbRelatorio.setText(this.relatorio.escolherPagina(this.indexPaginaAtual).getTexto());

		// Atualiza o desenho
		this.paneDesenhoEstrutura.getChildren().clear();
		this.paneDesenhoEstrutura.getChildren().add(this.relatorio.escolherPagina(this.indexPaginaAtual).getImagem());

		// Atualiza o indicador da numera��o das p�ginas
		this.lbTotalDePaginas.setText(this.relatorio.totalDePaginas() + "");
		this.lbPaginaAtual.setText(this.indexPaginaAtual + "");
	}

	/**
	 * M�todo usado para atualizar a altura e largura do Pane que recebe as
	 * estruturas.
	 */
	private void atualizaAlturaLarguraPaneEstrutura() {
		Pane desenho = this.relatorio.escolherPagina(this.indexPaginaAtual).getImagem();
		this.paneDesenhoEstrutura.setPrefSize(desenho.getWidth() * 1.1, desenho.getHeight() * 1.1);
	}
}
