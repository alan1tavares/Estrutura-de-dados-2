package br.com.ed2.log;

public class LogDot {
	private String log;
	private String tipoDeGrafo = "digraph name";
	private String descricao;
	private String grafo;

	public LogDot() {
	}

	public LogDot(String descricao, String grafo) {
		setLog(descricao, grafo);
	}

	private void setLog(String descricao, String grafo) {
		String label = "\tL;\n\tL[label = " + "\"" + descricao + "\"" + ", shape = none];\n";
		this.log = tipoDeGrafo + "{\n" + label + grafo + "\n}";
	}

	public String getLog() {
		return log;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
		setLog(this.descricao, this.grafo);
	}

	public void setGrafo(String grafo) {
		this.grafo = grafo;
		setLog(this.descricao, this.grafo);
	}

}
