package br.com.ed2.estruturas.relatorio;

import br.com.ed2.desenho.arvore.Arvore2D;
import javafx.scene.layout.Pane;

public class RelatorioAvl extends Relatorio {
	public RotuloRelatorioAvl rotulo;

	@Override
	public void adicionar(String texto) {
		super.adicionar(texto);
		montarImagen(texto);
	}

	private void montarImagen(String texto) {
		if (texto.indexOf(rotulo.ENTRADA) != -1) {
			String entrada = texto.substring(texto.indexOf(rotulo.ENTRADA) + rotulo.ENTRADA.length(),
					texto.indexOf(rotulo.FINAL_TEXTO));
		}
		if (texto.indexOf(rotulo.SAIDA) != -1) {
			int inicioStr;
			int fimStr;

			Arvore2D arvore = new Arvore2D();

			// Pega os elemento para fazer o desenho da árvore
			inicioStr = texto.indexOf(rotulo.SAIDA) + rotulo.SAIDA.length();
			fimStr = texto.indexOf(rotulo.ALTURA);
			String elementos = texto.substring(inicioStr, fimStr);
			// -----------------

			// Pega a altura do desenho
			inicioStr = texto.indexOf(rotulo.ALTURA) + rotulo.ALTURA.length();
			fimStr = texto.indexOf(rotulo.FINAL_TEXTO);
			int altura = Integer.parseInt(texto.substring(inicioStr, fimStr));
			// -----------------

			//
			Pane p = arvore.arvorePreOrdem(elementos, altura, 20);
			p.setLayoutX(arvore.getLarguraDaArvore());
									
			this.desenhos.add(p);
			// Colocar a aestrutura da arvore em vez do panel
		}
	}

	public void adicionarEntrada(String texto) {
		adicionar(rotulo.ENTRADA + texto);
	}
}
