package br.com.ed2.arquivo.dot;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import br.com.ed2.log.LogDot;

public class FileDot {
	private String caminhoDaSaida;
	private String nomeDoArquivo;
	private LogDot log;

	public FileDot(String caminhoDaSaida, String nomeDoArquivo, LogDot log) {
		this.caminhoDaSaida = caminhoDaSaida;
		this.nomeDoArquivo = nomeDoArquivo;
		this.log = log;
	}

	public String gerarArquivo() throws IOException {
		String caminhoSaida = this.caminhoDaSaida+""+this.nomeDoArquivo+".gz";
		FileOutputStream fileOutputStream = new FileOutputStream(caminhoSaida);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
		bufferedWriter.write(this.log.getLog());
		bufferedWriter.close();
		return caminhoSaida;
	}
}
