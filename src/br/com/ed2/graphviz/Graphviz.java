package br.com.ed2.graphviz;

import java.io.IOException;

public class Graphviz {
	private String caminhoDoDot;

	private String arquivoDeEntrada;
	private String arquivoDeSaida;

	private String tParam = "-Tjpg";
	private String tOParam = "-o";

	String[] cmd = new String[5];

	public Graphviz(String caminhoDoDot) {
		this.caminhoDoDot = caminhoDoDot;
		atualizaParametros();
	}
	
	public void set(String arquivoDeEntrada, String arquivoDeSaida){
		this.arquivoDeEntrada = arquivoDeEntrada;
		this.arquivoDeSaida = arquivoDeSaida+".jpg";
		atualizaParametros();
		System.out.println(this.arquivoDeEntrada);
		System.out.println(this.arquivoDeSaida);
	}

	public void salvarArquivo() throws IOException {
		Runtime rt = Runtime.getRuntime();
		rt.exec(cmd);
	}
	
	private void atualizaParametros(){
		cmd[0] = caminhoDoDot;
		cmd[1] = tParam;
		cmd[2] = arquivoDeEntrada;
		cmd[3] = tOParam;
		cmd[4] = arquivoDeSaida;
	}

}
