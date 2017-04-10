package br.com.ed2.gui.relatorio;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.ed2.estruturas.avl.AvlTree;
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

	// Variáveis para teste
	private String entrada = "46 39 22 5 25 36 3 45 1 43 8 28 57 47 10 21 48 4 53 32";
	private Relatorio relatorio;

	public void btProximo() {
		if (this.relatorio != null) {
			if (this.indexPaginaAtual < this.relatorio.totalDePaginas()) {
				indexPaginaAtual += 1;
				this.lbRelatorio.setText("" + this.relatorio.escolherPagina(this.indexPaginaAtual).getTexto());
				this.lbPaginaAtual.setText("" + this.indexPaginaAtual);
				this.paneDesenhoEstrutura.getChildren().clear();
				Pane desenho = this.relatorio.escolherPagina(this.indexPaginaAtual).getImagem();
				this.paneDesenhoEstrutura.getChildren()
						.add(this.relatorio.escolherPagina(this.indexPaginaAtual).getImagem());

			}
			atualizaAlturaLarguraPaneEstrutura();
		}
	}

	public void btAnterior() {
		if (this.relatorio != null) {
			if (this.indexPaginaAtual > 0) {
				indexPaginaAtual -= 1;
				this.lbRelatorio.setText("" + this.relatorio.escolherPagina(this.indexPaginaAtual).getTexto());
				this.lbPaginaAtual.setText("" + this.indexPaginaAtual);
				this.paneDesenhoEstrutura.getChildren().clear();
				Pane desenho = this.relatorio.escolherPagina(this.indexPaginaAtual).getImagem();
				this.paneDesenhoEstrutura.getChildren()
						.add(this.relatorio.escolherPagina(this.indexPaginaAtual).getImagem());

			}
			atualizaAlturaLarguraPaneEstrutura();
		}
	}

	// Método que será chamado quando a view for inicializada
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.lbRelatorio.setText("");
		this.relatorio = new Relatorio();
		String[] strVetor = entrada.split(" ");
		AvlTree<Integer> avl = new AvlTree<>(true);

		for (String string : strVetor) {
			int valor = Integer.parseInt(string);
			avl.inserir(valor);
		}
		this.relatorio = avl.getRelatorio();

		// Atualizar as labels
		this.lbTotalDePaginas.setText("" + this.relatorio.totalDePaginas());
		this.lbPaginaAtual.setText("1");

		// Mostra a primeira página do relatório
		this.lbRelatorio.setText(this.relatorio.escolherPagina(this.indexPaginaAtual).getTexto());
		this.paneDesenhoEstrutura.getChildren().clear();
		this.paneDesenhoEstrutura.getChildren().add(this.relatorio.escolherPagina(this.indexPaginaAtual).getImagem());

		atualizaAlturaLarguraPaneEstrutura();
	}

	/**
	 * Méodo usado para atulizar a altura e largura do Pane que recebe as
	 * estruturas.
	 */
	private void atualizaAlturaLarguraPaneEstrutura() {
		Pane desenho = this.relatorio.escolherPagina(this.indexPaginaAtual).getImagem();
		this.paneDesenhoEstrutura.setPrefSize(desenho.getWidth() * 1.1, desenho.getHeight() * 1.1);
	}
}
