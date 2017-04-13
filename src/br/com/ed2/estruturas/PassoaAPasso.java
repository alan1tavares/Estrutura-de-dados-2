package br.com.ed2.estruturas;

import br.com.ed2.estruturas.relatorio.Relatorio;

public interface PassoaAPasso<Type> extends InserirDeletarBuscar<Type>{
	public Relatorio getRelatorio();
}
