package br.com.ed2.arquivo.dot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.ed2.configuracao.Configuracao;
import br.com.ed2.graphviz.Graphviz;
import br.com.ed2.log.LogDot;

public class ExportaPNGSDot {
	private List<LogDot> log;
	private List<String> caminhoDosArquivosASeremExportado;

	public ExportaPNGSDot(List<LogDot> log) {
		this.log = log;
		gerarLogText();
	}

	public void gerarPNGS(String caminhoDaSaida) {
		String caminhoDot = Configuracao.getInstance().getCaminhoGraphvizDOT();
		if (caminhoDot != null) {
			Graphviz graphviz = new Graphviz(caminhoDot);
			int i = 0;
			for (String entrada : caminhoDosArquivosASeremExportado) {
				graphviz.set(entrada, caminhoDaSaida + i);
				try {
					graphviz.salvarArquivo();
				} catch (IOException e) {
					System.out.println("Não foi possível salvar a imagem");
					e.printStackTrace();
				}
				i++;
			}
		}
	}

	private void gerarLogText() {
		this.caminhoDosArquivosASeremExportado = new ArrayList<>();
		int i = 0;
		for (LogDot logDot : log) {
			FileDot fileDot = new FileDot(".tmp/", "log" + i, logDot);
			try {
				this.caminhoDosArquivosASeremExportado.add(fileDot.gerarArquivo());
			} catch (IOException e) {
				System.out.println("Arquivo não foi salvo");
				e.printStackTrace();
			}
			i++;
		}
	}
}