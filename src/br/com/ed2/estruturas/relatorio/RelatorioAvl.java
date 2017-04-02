package br.com.ed2.estruturas.relatorio;

import br.com.ed2.desenho.arvore.Arvore2D;

public class RelatorioAvl extends Relatorio{
	private final String INSERCAO = "Inserir: "; // 
	private final String SAIDA = "Saida: ";
	private final String ALTURA = "Altura: ";
	private final String FINAL_TEXTO = ".";
	
	@Override
	public void adicionar(String texto) {
		super.adicionar(texto);
		montarImagen(texto);
	}

	private void montarImagen(String texto) {
		texto.indexOf(INSERCAO);
		if(texto.indexOf(INSERCAO) != -1){
			
		} else if(texto.indexOf(SAIDA) != -1){
			Arvore2D arvore = new Arvore2D();
			String elementos = texto.substring(texto.indexOf(SAIDA), texto.indexOf(ALTURA));
			int altura = Integer.parseInt(texto.substring(texto.indexOf(ALTURA), texto.indexOf(FINAL_TEXTO)));
			arvore.arvorePreOrdem(elementos, altura, 20);
		}
	}
}
