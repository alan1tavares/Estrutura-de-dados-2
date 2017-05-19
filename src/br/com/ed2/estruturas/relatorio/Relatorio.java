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

	/**
	 * Retorna uma p�gina a partir da numera��o dela.
	 * 
	 * @param numero
	 *            Numera��o da p�gina.
	 * @return Pagina escolhida.
	 */
	public Pagina escolherPagina(int numero) {
		if (this.paginas == null)
			return null;
		if (numero <= totalDePaginas())
			return this.paginas.get(numero);
		return null;
	}

	/**
	 * Retorna o total de p�ginas do relat�rio.
	 * 
	 * @return Total de p�ginas.
	 */
	public int totalDePaginas() {
		return this.paginas.size() - 1;
	}

	/**
	 * Retorna todas a p�ginas do relat�rio.
	 * 
	 * @return Retorna as p�ginas do relat�rio.
	 */
	public List<Pagina> getPaginas() {
		return paginas;
	}

	/**
	 * Retorna a maior largura da p�gina que est� no relat�rio.
	 * 
	 * @return Maior largura da p�gina.
	 */
	public double getLarguraMaximaDaPagina() {
		double largura = 0.0;
		for (Pagina p : this.paginas) {
			if (p.getImagem().getWidth() > largura)
				largura = p.getImagem().getWidth();
		}
		return largura;
	}

	/**
	 * Retorna a maior altura da p�gina que est� no relat�rio
	 * 
	 * @return Maior altura da p�gina.
	 */
	public double getAlturaMaximaDaPgina() {
		double altura = 0.0;
		for (Pagina p : this.paginas) {
			if (p.getImagem().getHeight() > altura)
				altura = p.getImagem().getHeight();
		}
		return altura;
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
