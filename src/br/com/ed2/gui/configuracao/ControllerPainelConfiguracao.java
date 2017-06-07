package br.com.ed2.gui.configuracao;

import java.io.File;
import java.io.IOException;

import br.com.ed2.configuracao.Configuracao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerPainelConfiguracao {
	@FXML
	private TextField camihoGraphvizDot;

	public void aplicar() throws IOException {
		if (!this.camihoGraphvizDot.getText().isEmpty()) {
			Configuracao.getInstance().setCaminhoGraphvizDOT(this.camihoGraphvizDot.getText());

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("Configurações aplicada com sucesso!!");
			alert.showAndWait();
		}
	}

	public void cancelar() {
		System.out.println("Falta implementar");
	}

	public void selecionarArquivo(MouseEvent mouseEvent) {
		if (this.camihoGraphvizDot.getText().isEmpty() || mouseEvent.getClickCount() == 2) {
			// Chama a classe que ira abrir a caixa do explorer para escholher o
			// arquivo
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Escolha a pasta");
			// O caminho do arquivo é armazenado em objeto File
			File file = fileChooser.showOpenDialog(new Stage());
			// Define o camiho do arquivo escolhida dentro do textField
			this.camihoGraphvizDot.setText(file.getPath());
		}
	}

}
