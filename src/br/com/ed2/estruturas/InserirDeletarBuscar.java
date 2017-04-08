package br.com.ed2.estruturas;

public interface InserirDeletarBuscar<Type> {
	/**
	 * Insere elemento em uma alguma estrutura.
	 * 
	 * @param elemento
	 *            Algum elemento a ser inserido.
	 */
	public void inserir(Type elemento);

	public void deletar(Type elemento);

	public void buscar(Type elemento);
}
