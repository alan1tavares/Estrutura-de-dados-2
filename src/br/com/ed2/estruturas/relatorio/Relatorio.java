package br.com.ed2.estruturas.relatorio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe feita para criar um relatório que armazena todas as interações feito
 * nas estruras. Elas possui usa uma Lista de Páginas. Nesse arraylist é
 * aramazenado textos e imagens que representa esses textos.
 * 
 * @author Alan Tavares
 *
 */
public class Relatorio {
	protected List<Pagina> paginas;

	// Construtor que inicializa a Lista
	public Relatorio() {
		this.paginas = new ArrayList<>();
	}

	/**
	 * Insere uma página a esse relatório. Se o objeto passado for nulo, ira
	 * lançar uma exception.
	 * 
	 * @param pagina
	 *            a ser inserida
	 */
	public void inserirPagina(Pagina pagina) {
		isNull(pagina);
		this.paginas.add(pagina); // Insere a página na lista.
	}

	/**
	 * Cria uma página com esse texto e insere no relatório. Esse texto não
	 * possuirá uma imagem representando ele. Se o objeto for nulo ira lançar
	 * uma exception.
	 * 
	 * @param texto
	 *            Coloca esse texto no relatório
	 */
	public void inserirTexto(String texto) {
		isNull(texto);
		Pagina pagina = new Pagina(texto); // Cria a página que possui só texto.
		inserirPagina(pagina); // Insere a página na lista.

	}

	/*
	 * Métodos auxiliares
	 */

	/*
	 * Método auxiliar que verifica se um objeto é nulo se sim, irá lançar uma
	 * exception.
	 */
	private void isNull(Object o) {
		if (o == null)
			throw new IllegalArgumentException("Objeto passado foi um valo nulo");
	}
}
