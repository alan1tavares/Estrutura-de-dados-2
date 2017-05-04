package br.com.ed2.gui.exportar.png;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.ed2.gui.relatorio.ControllerRelatorio;
import br.com.ed2.png.SalvarEstruturaPNG;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerExportaPng implements Initializable {

	private File file;
	private Pane node;

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

		String caminhoView = "/br/com/ed2/gui/relatorio/ViewRelatorio.fxml";
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource(caminhoView));
			Pane pane = fxmlLoader.load();

			ControllerRelatorio controllerRelatorio = fxmlLoader.getController();
			double largura = controllerRelatorio.getPaneDesenhoEstrutura().getPrefWidth();
			double altura = controllerRelatorio.getPaneDesenhoEstrutura().getPrefHeight();
			Scene sc = new Scene(pane);

			pane.setMaxSize(largura+1000, altura+1000);
			pane.setPrefSize(largura, altura+200);

			// Se existir algum caminho na lbl, então tente criar o objeto do
			// tipo
			// file com esse caminho
			if (tfURL.getText() != null) {
				this.file = new File(tfURL.getText());
				new SalvarEstruturaPNG().savarComoPNG(sc, this.file);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setNode(Pane imagem) {
		this.node = imagem;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
