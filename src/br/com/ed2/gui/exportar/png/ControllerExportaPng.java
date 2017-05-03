package br.com.ed2.gui.exportar.png;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.ed2.png.SalvarEstruturaPNG;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ControllerExportaPng implements Initializable{

	private File file;
	private Pane node;

	@FXML
	private TextField tfURL;

	public void selecionarPasta() {
		// Chama a classe que ira abrir a caixa do explorer para escholher a
		// pasta
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Escolha a pasta");
		// O caminho da pasta é armazenado em objeto File
		File file = directoryChooser.showDialog(new Stage());
		// Desfine o camiho da pasta escolhida dentro do textField
		tfURL.setText(file.getPath());
	}

	public void salvarPNGS() {
		// Se existir algum caminho na lbl, então tente criar o objeto do tipo
		// file com esse caminho
		if (tfURL.getText() != null) {
			this.file = new File(tfURL.getText());
			new SalvarEstruturaPNG().savarComoPNG(this.node);
			if(this.node == null) System.out.println("Null - Controller Exporta Png");
		}
	}

	public void setNode(Pane imagem) {
		System.out.println("Set node");
		this.node = imagem;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
