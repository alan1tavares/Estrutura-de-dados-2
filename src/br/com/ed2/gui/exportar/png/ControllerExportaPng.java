package br.com.ed2.gui.exportar.png;

import java.io.File;
import java.io.IOException;

import br.com.ed2.estruturas.relatorio.Relatorio;
import br.com.ed2.gui.relatorio.ControllerRelatorio;
import br.com.ed2.png.SalvarEstruturaPNG;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Exporta como PNG uma view
 * 
 * @author Alan Tavares
 */
public class ControllerExportaPng {

	private File file;
	private Relatorio relatorio;

	@FXML
	private TextField tfURL;

	/**
	 * Método que é chamado para escolher a pasta a onde vai ser salvo as
	 * imagens
	 */
	public void selecionarPasta() {
		// Chama a classe que ira abrir a caixa do explorer para escholher o
		// nome do arquivo a ser salvo
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Escolha a pasta");
		// O caminho da pasta é armazenado em objeto File
		this.file = fileChooser.showSaveDialog(new Stage());
		// Define o camiho da pasta escolhida dentro do textField
		tfURL.setText(file.getPath());
	}

	public void salvarPNGS() {

		String caminhoView = "/br/com/ed2/gui/relatorio/ViewRelatorioPNG.fxml";
		try {
			// Carrega a view do relatória mas não exibe ela
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource(caminhoView));
			// Coloca a view em um pane
			Pane pane = fxmlLoader.load();
			// controllerRelatorio armazena o controller da view que foi
			// carregada
			ControllerRelatorio controllerRelatorio = fxmlLoader.getController();
			// Povoa a view do relatorio
			controllerRelatorio.setRelatorio(this.relatorio);
			// Adiciona a Pane na scene
			Scene sc = new Scene(pane);

			// TODO
			controllerRelatorio.setTamanho(this.relatorio.getLarguraMaximaDaPagina(),
					this.relatorio.getAlturaMaximaDaPgina());
			System.out.println("Largura -> " + this.relatorio.getLarguraMaximaDaPagina());
			System.out.println(controllerRelatorio.getBorderPane().getMaxHeight()+100);

			// Se existir algum caminho na lbl, então tente criar o objeto do
			// tipo file com esse caminho
			if (tfURL.getText() != null) {
				int i = 0;

				while (i <= relatorio.totalDePaginas()) {
					controllerRelatorio.escolherPagina(i);
					this.file = new File(tfURL.getText() + i + ".png");
					// TODO descomentar depois
					//escolheTamanhoImagem(pane, controllerRelatorio);
					new SalvarEstruturaPNG().salvarComoPNG(sc, this.file);
					i++;
				}
				System.out.println("Finish");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param pane
	 * @param controllerRelatorio
	 */
	private void escolheTamanhoImagem(Pane pane, ControllerRelatorio controllerRelatorio) {
		double largura = controllerRelatorio.getPaneDesenhoEstrutura().getPrefWidth();
		double altura = controllerRelatorio.getPaneDesenhoEstrutura().getPrefHeight();
		// configura a altura e lagura

		/// pane.setMaxSize(largura + 1000, altura + 1000);
		// pane.setPrefSize(largura, altura + 200);

		controllerRelatorio.setTamanho(2000, altura + 200);

		// TODO apagar depois
		System.out.println(toString() + " -> Largura " + largura);
		System.out.println(toString() + " -> PrefLargura " + controllerRelatorio.getBorderPane().getWidth());
		System.out.println(toString() + " -> MaxWidth " + pane.getMaxWidth());
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}
}
