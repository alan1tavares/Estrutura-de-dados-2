package br.com.ed2.configuracao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Utiliza o padrão singleton.
 * 
 * @author Alan Tavares
 *
 */
public class Configuracao implements Serializable {
	private static Configuracao singletonConfiguracao;

	private String caminhoGraphvizDOT;
	private final static String ARQUIVO_CONFIG = "config.xml";

	private Configuracao() {

	}

	public static synchronized Configuracao getInstance() {
		if (singletonConfiguracao == null) {
			try {
				singletonConfiguracao = load();
			} catch (ClassNotFoundException | IOException e) {
				singletonConfiguracao = new Configuracao();
				e.printStackTrace();
			}
		}
		return singletonConfiguracao;
	}

	private void save() throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(".settings/" + ARQUIVO_CONFIG);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(this);
		objectOutputStream.close();
	}

	private static Configuracao load() throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(".settings/" + ARQUIVO_CONFIG);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		Configuracao config = (Configuracao) objectInputStream.readObject();
		objectInputStream.close();
		return config;
	}

	public void setCaminhoGraphvizDOT(String caminhoGraphvizDOT) throws IOException {
		this.caminhoGraphvizDOT = caminhoGraphvizDOT;
		save();
	}

	public String getCaminhoGraphvizDOT() {
		return caminhoGraphvizDOT;
	}
}
