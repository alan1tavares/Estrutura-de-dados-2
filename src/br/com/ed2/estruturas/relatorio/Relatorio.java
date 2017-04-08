package br.com.ed2.estruturas.relatorio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe feita para criar um relat�rio que armazena todas as intera��es feito
 * nas estruras. Elas possui usa uma Lista de P�ginas. Nesse arraylist �
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
	 * Insere uma p�gina a esse relat�rio. Se o objeto passado for nulo, ira
	 * lan�ar uma exception.
	 * 
	 * @param pagina
	 *            a ser inserida
	 */
	public void inserirPagina(Pagina pagina) {
		isNull(pagina);
		this.paginas.add(pagina); // Insere a p�gina na lista.
	}

	/**
	 * Cria uma p�gina com esse texto e insere no relat�rio. Esse texto n�o
	 * possuir� uma imagem representando ele. Se o objeto for nulo ira lan�ar
	 * uma exception.
	 * 
	 * @param texto
	 *            Coloca esse texto no relat�rio
	 */
	public void inserirTexto(String texto) {
		isNull(texto);
		Pagina pagina = new Pagina(texto); // Cria a p�gina que possui s� texto.
		inserirPagina(pagina); // Insere a p�gina na lista.

	}

	/*
	 * M�todos auxiliares
	 */

	/*
	 * M�todo auxiliar que verifica se um objeto � nulo se sim, ir� lan�ar uma
	 * exception.
	 */
	private void isNull(Object o) {
		if (o == null)
			throw new IllegalArgumentException("Objeto passado foi um valo nulo");
	}
}
