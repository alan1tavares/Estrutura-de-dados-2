package br.com.ed2.estruturas.relatorio;

import br.com.ed2.desenho.arvore.Arvore2D;
import javafx.scene.layout.Pane;

public class RelatorioAvl extends Relatorio {
	public RotuloRelatorioAvl rotulo;

	@Override
	public void inserirTexto(String texto) {
		Pane imagem = montarImagen(texto); // Monta uma imagem dentro de um Pane.
		Pagina pagina = new Pagina(texto, imagem); // Curia a pagina.
		inserirPagina(pagina); // Insere uma página na lista.

		/*
		 * if(texto.indexOf(this.rotulo.ARVORE_DESBALANCEADA) < 0) {
		 * super.adicionar(texto); }
		 */
	}

	/*
	 * Método auxiliares
	 */

	/*
	 * Método que monta uma Imagem(dentro de um Pane) em relação ao texto.
	 */
	private Pane montarImagen(String texto) {
		/*
		 * if (texto.indexOf(rotulo.ENTRADA) != -1) { String entrada =
		 * texto.substring(texto.indexOf(rotulo.ENTRADA) +
		 * rotulo.ENTRADA.length(), texto.indexOf(rotulo.FINAL_TEXTO)); }
		 */

		if (texto.indexOf(rotulo.SAIDA) != -1) {
			return montarImagemDaSaida(texto);
		}

		return null;
	}

	private Pane montarImagemDaSaida(String texto) {
		int inicioStr;
		int fimStr;

		Arvore2D arvore = new Arvore2D(); // Cria um desenho de uma ávore vazia.

		// Pega os elemento para fazer o desenho da árvore.
		inicioStr = texto.indexOf(rotulo.SAIDA) + rotulo.SAIDA.length();
		fimStr = texto.indexOf(rotulo.ALTURA);
		String elementos = texto.substring(inicioStr, fimStr);
		// -----------------

		// Pega a altura do desenho.
		inicioStr = texto.indexOf(rotulo.ALTURA) + rotulo.ALTURA.length();
		fimStr = texto.indexOf(rotulo.FINAL_TEXTO);
		int altura = Integer.parseInt(texto.substring(inicioStr, fimStr));
		// -----------------

		// Coloca o desenho em um pane
		Pane p = arvore.arvorePreOrdem(elementos, altura, 20);
		p.setLayoutX(arvore.getLarguraDaArvore()); // Ajeita a coordenada y do
													// pane
		return p;
	}

}
