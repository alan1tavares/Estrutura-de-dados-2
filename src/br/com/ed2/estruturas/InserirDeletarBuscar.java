package br.com.ed2.estruturas;

public interface InserirDeletarBuscar<Type> {
	public void inserir(Type elemento);
	public void deletar(Type elemento);
	public void buscar(Type elemento);
}
