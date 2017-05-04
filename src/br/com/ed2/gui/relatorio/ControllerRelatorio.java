package br.com.ed2.gui.relatorio;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.ed2.estruturas.relatorio.Relatorio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ControllerRelatorio implements Initializable {

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
	private static int indexPaginaAtual = 0;
	public static Relatorio relatorio;

	// Variáveis para teste
	// private String entrada = "46 39 22 5 25 36 3 45 1 43 8 28 57 47 10 21 48
	// 4 53 32";

	/**
	 * Evento do botão que avança para o proximo passo
	 */
	public void btProximo() {
		if (ControllerRelatorio.relatorio != null) {
			if (ControllerRelatorio.indexPaginaAtual < ControllerRelatorio.relatorio.totalDePaginas()) {
				ControllerRelatorio.indexPaginaAtual += 1;
				this.lbRelatorio.setText("" + ControllerRelatorio.relatorio
						.escolherPagina(ControllerRelatorio.indexPaginaAtual).getTexto());
				this.lbPaginaAtual.setText("" + ControllerRelatorio.indexPaginaAtual);
				this.paneDesenhoEstrutura.getChildren().clear();
				Pane desenho = ControllerRelatorio.relatorio.escolherPagina(ControllerRelatorio.indexPaginaAtual)
						.getImagem();
				this.paneDesenhoEstrutura.getChildren().add(desenho);

			}
			atualizaAlturaLarguraPaneEstrutura();
		}
	}

	/**
	 * Evento do botão que volta para o passo anterior
	 */
	public void btAnterior() {
		if (ControllerRelatorio.relatorio != null) {
			if (ControllerRelatorio.indexPaginaAtual > 0) {
				indexPaginaAtual -= 1;
				this.lbRelatorio.setText("" + ControllerRelatorio.relatorio
						.escolherPagina(ControllerRelatorio.indexPaginaAtual).getTexto());
				this.lbPaginaAtual.setText("" + ControllerRelatorio.indexPaginaAtual);
				this.paneDesenhoEstrutura.getChildren().clear();
				Pane desenho = ControllerRelatorio.relatorio.escolherPagina(ControllerRelatorio.indexPaginaAtual)
						.getImagem();
				this.paneDesenhoEstrutura.getChildren().add(desenho);

			}
			atualizaAlturaLarguraPaneEstrutura();
		}
	}

	// Método que será chamado quando a view for inicializada
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.lbRelatorio.setText("");
		if (ControllerRelatorio.relatorio != null) {

			// Atualiza a label que exibe o total de páginas
			this.lbTotalDePaginas.setText("" + ControllerRelatorio.relatorio.totalDePaginas());

			// Atualiza a label que mosta o número da página atual que está
			// sendo exbida
			if (ControllerRelatorio.indexPaginaAtual > ControllerRelatorio.relatorio.totalDePaginas())
				ControllerRelatorio.indexPaginaAtual = ControllerRelatorio.relatorio.totalDePaginas();
			this.lbPaginaAtual.setText("" + ControllerRelatorio.indexPaginaAtual);

			this.lbRelatorio.setText(
					ControllerRelatorio.relatorio.escolherPagina(ControllerRelatorio.indexPaginaAtual).getTexto());
			this.paneDesenhoEstrutura.getChildren().clear();
			this.paneDesenhoEstrutura.getChildren().add(
					ControllerRelatorio.relatorio.escolherPagina(ControllerRelatorio.indexPaginaAtual).getImagem());

			atualizaAlturaLarguraPaneEstrutura();

		}
	}

	/**
	 * Métudo utilizado para atualizar a janela. Se exisitir um relatorio a
	 * janela é atualizada para a pimeira página do relatório.
	 */
	public void atualizaJanela() {
		if (ControllerRelatorio.relatorio != null) {
			this.lbTotalDePaginas.setText("" + ControllerRelatorio.relatorio.totalDePaginas());
			this.lbPaginaAtual.setText(ControllerRelatorio.indexPaginaAtual + "");

			this.lbRelatorio.setText(
					ControllerRelatorio.relatorio.escolherPagina(ControllerRelatorio.indexPaginaAtual).getTexto());
			this.paneDesenhoEstrutura.getChildren().clear();
			this.paneDesenhoEstrutura.getChildren().add(
					ControllerRelatorio.relatorio.escolherPagina(ControllerRelatorio.indexPaginaAtual).getImagem());
		} else {
			System.out.println("Erro relatorio não foi instanciado");
		}
	}

	/**
	 * Retorna um border pane que á o nó raiz
	 * 
	 * @return BorderPane
	 */
	public BorderPane getBorderPane() {
		return borderPane;
	}

	/**
	 * Retorna o pane que está armazenada o desenho da estrutura
	 * 
	 * @return Pane
	 */
	public Pane getPaneDesenhoEstrutura() {
		return paneDesenhoEstrutura;
	}

	/*
	 * Métodos privados auxiliares
	 */

	/**
	 * Métsodo usado para atualizar a altura e largura do Pane que recebe as
	 * estruturas.
	 */
	private void atualizaAlturaLarguraPaneEstrutura() {
		Pane desenho = ControllerRelatorio.relatorio.escolherPagina(ControllerRelatorio.indexPaginaAtual).getImagem();
		this.paneDesenhoEstrutura.setPrefSize(desenho.getWidth() * 1.1, desenho.getHeight() * 1.1);
	}
}
