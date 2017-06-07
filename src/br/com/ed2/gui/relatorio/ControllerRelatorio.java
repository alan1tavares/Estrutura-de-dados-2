package br.com.ed2.gui.relatorio;

import br.com.ed2.relatorio.Relatorio;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * 
 * Classe Controller da View relatorio. Ela tem o ojetivo de exibir as paginas
 * do Relatório. Esse relatório seria o passo a passo de alguma estrutra
 * específica, os passos seria as páginas.
 * 
 * @author Alan Tavares
 * 
 */
public class ControllerRelatorio {

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
	private Pane panePai;

	// Atributos da "Normais"
	// Indice que indica em qual passo está a View em relação ao que está no
	// relatório.
	private int indexPaginaAtual = 0;
	private Relatorio relatorio;
	// Tamanho da imagem dafault
	private final int ALTURA = 400;
	private final int LARGURA = 800;

	// Eventos de Botões da View {

	/**
	 * Evento do botão que avança para o proximo passo
	 */
	public boolean btProximo() {
		if (this.relatorio != null) {
			if (this.indexPaginaAtual < this.relatorio.totalDePaginas()) {
				this.indexPaginaAtual += 1;
				atualiza();
				return true;
			}
		}
		// Chegou ao fim
		return false;
	}

	/**
	 * Evento do botão que volta para o passo anterior
	 */
	public void btAnterior() {
		if (this.relatorio != null) {
			if (this.indexPaginaAtual > 0) {
				indexPaginaAtual -= 1;
				atualiza();
			}
		}
	}

	// } Fim dos eventos de Botões

	/**
	 * Escolhe o passo a ser exibido na view.
	 * 
	 * @param i
	 *            Indicação do passo a ser exibido.
	 * @return Retorna a pane que possui todos os nós da View
	 */
	public Pane escolherPagina(int i) {
		if (this.relatorio != null) {
			boolean iValido = i <= this.relatorio.totalDePaginas() && i >= 0;
			if (iValido) {
				this.indexPaginaAtual = i;
				atualiza();
			}
		}
		return getBorderPane();
	}

	/**
	 * Método utilizado para atualizar a janela. Se exisitir um relatorio a
	 * janela é atualizada.
	 */
	public void atualiza() {
		if (this.relatorio != null) {

			atualizaPagina();
			atualizaAlturaLarguraPaneEstrutura();

		} else {
			System.out.println("Erro relatorio não foi instanciado");
		}
	}

	/**
	 * Retorna um border pane que á o nó raiz
	 * 
	 * @return BorderPane
	 */
	public Pane getBorderPane() {
		return panePai;
	}

	/**
	 * Retorna o pane que está armazenada o desenho da estrutura
	 * 
	 * @return Pane
	 */
	public Pane getPaneDesenhoEstrutura() {
		return paneDesenhoEstrutura;
	}

	/**
	 * Define o relatorio que será exibido na View
	 * 
	 * @param relatorio
	 *            Objeto relatório que será definido para ser ebido na view
	 */
	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
		this.indexPaginaAtual = 0;
		atualiza();
	}

	// Métodos privados auxiliares

	/**
	 * Atualiza o desenho, a numeração e o texto. A atualização é relizada
	 * baseda no indexPaginaAtual
	 */
	private void atualizaPagina() {
		// Verifica se o indice atual está dentro dos limites, se não tiver
		// ecoclhe-se uma página dentro dos limites.
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
		// Adiciona a imagem daquele pane
		this.paneDesenhoEstrutura.getChildren().add(this.relatorio.escolherPagina(this.indexPaginaAtual).getImagem());

		// Atualiza o indicador da numeração das páginas
		this.lbTotalDePaginas.setText(this.relatorio.totalDePaginas() + "");
		this.lbPaginaAtual.setText(this.indexPaginaAtual + "");
	}
	
	/**
	 * 
	 * @param alltura
	 * @param largura
	 */
	public void setTamanho(double largura, double altura){
		this.panePai.setPrefSize(largura, altura);
		System.out.println(toString()+"-> largura "+this.panePai.getWidth());
	}

	/**
	 * Método usado para atualizar a altura e largura do Pane que recebe as
	 * estruturas.
	 */
	private void atualizaAlturaLarguraPaneEstrutura() {
		Pane desenho = this.relatorio.escolherPagina(this.indexPaginaAtual).getImagem();
		/*
		 * if (desenho.getWidth() >= LARGURA || desenho.getHeight() >= ALTURA)
		 */
		this.paneDesenhoEstrutura.setPrefSize(desenho.getWidth() * 1.1, desenho.getHeight() * 1.1);

		/*
		 * else this.paneDesenhoEstrutura.setPrefSize(LARGURA * 1.1, ALTURA *
		 * 1.1);
		 */
	}
}
