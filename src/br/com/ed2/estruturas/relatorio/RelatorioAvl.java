package br.com.ed2.estruturas.relatorio;

import br.com.ed2.desenho.arvore.Arvore2D;

public class RelatorioAvl extends Relatorio{
	public RotuloRelatorioAvl rotulo;
	
	@Override
	public void adicionar(String texto) {
		super.adicionar(texto);
		montarImagen(texto);
	}

	private void montarImagen(String texto) {
		if(texto.indexOf(rotulo.SAIDA) != -1){
			Arvore2D arvore = new Arvore2D();
			String elementos = texto.substring(texto.indexOf(rotulo.SAIDA), texto.indexOf(rotulo.ALTURA));
			int altura = Integer.parseInt(texto.substring(texto.indexOf(rotulo.ALTURA), texto.indexOf(rotulo.FINAL_TEXTO)));
			arvore.arvorePreOrdem(elementos, altura, 20);
		}
	}
	
	public void adicionarEntrada(String texto){
		adicionar(rotulo.ENTRADA+texto);
	}
}
