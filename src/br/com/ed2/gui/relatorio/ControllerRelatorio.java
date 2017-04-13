package br.com.ed2.gui.relatorio;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.ed2.estruturas.relatorio.Relatorio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

	// Atributos da "Normais"
	private int indexPaginaAtual;
	public static Relatorio relatorio;

	// Variáveis para teste
	// private String entrada = "46 39 22 5 25 36 3 45 1 43 8 28 57 47 10 21 48
	// 4 53 32";

	/**
	 * Evento do botão que avança para o proximo passo
	 */
	public void btProximo() {
		if (ControllerRelatorio.relatorio != null) {
			if (this.indexPaginaAtual < ControllerRelatorio.relatorio.totalDePaginas()) {
				indexPaginaAtual += 1;
				this.lbRelatorio
						.setText("" + ControllerRelatorio.relatorio.escolherPagina(this.indexPaginaAtual).getTexto());
				this.lbPaginaAtual.setText("" + this.indexPaginaAtual);
				this.paneDesenhoEstrutura.getChildren().clear();
				Pane desenho = ControllerRelatorio.relatorio.escolherPagina(this.indexPaginaAtual).getImagem();
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
			if (this.indexPaginaAtual > 0) {
				indexPaginaAtual -= 1;
				this.lbRelatorio
						.setText("" + ControllerRelatorio.relatorio.escolherPagina(this.indexPaginaAtual).getTexto());
				this.lbPaginaAtual.setText("" + this.indexPaginaAtual);
				this.paneDesenhoEstrutura.getChildren().clear();
				Pane desenho = ControllerRelatorio.relatorio.escolherPagina(this.indexPaginaAtual).getImagem();
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

			// Atualizar as labels
			this.lbTotalDePaginas.setText("" + ControllerRelatorio.relatorio.totalDePaginas());
			this.lbPaginaAtual.setText("0");

			// Mostra a primeira página do relatório
			this.lbRelatorio.setText(ControllerRelatorio.relatorio.escolherPagina(this.indexPaginaAtual).getTexto());
			this.paneDesenhoEstrutura.getChildren().clear();
			this.paneDesenhoEstrutura.getChildren()
					.add(ControllerRelatorio.relatorio.escolherPagina(this.indexPaginaAtual).getImagem());

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
			this.lbPaginaAtual.setText("0");

			this.lbRelatorio.setText(ControllerRelatorio.relatorio.escolherPagina(this.indexPaginaAtual).getTexto());
			this.paneDesenhoEstrutura.getChildren().clear();
			this.paneDesenhoEstrutura.getChildren()
					.add(ControllerRelatorio.relatorio.escolherPagina(this.indexPaginaAtual).getImagem());
		} else {
			System.out.println("Erro relatorio não foi instanciado");
		}
	}

	/*
	 * Métodos privados auxiliares
	 */

	/**
	 * Méodo usado para atualizar a altura e largura do Pane que recebe as
	 * estruturas.
	 */
	private void atualizaAlturaLarguraPaneEstrutura() {
		Pane desenho = ControllerRelatorio.relatorio.escolherPagina(this.indexPaginaAtual).getImagem();
		this.paneDesenhoEstrutura.setPrefSize(desenho.getWidth() * 1.1, desenho.getHeight() * 1.1);
	}
}
