package br.com.ed2.estruturas;

import java.util.List;

import br.com.ed2.log.LogDot;
import br.com.ed2.relatorio.Relatorio;

public interface PassoaAPasso<Type> extends InserirDeletarBuscar<Type> {
	public Relatorio getRelatorio();

	public List<LogDot> getLog();
}
