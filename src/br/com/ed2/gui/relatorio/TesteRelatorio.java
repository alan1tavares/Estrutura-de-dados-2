package br.com.ed2.gui.relatorio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TesteRelatorio extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String caminho = "/br/com/ed2/gui/relatorio/ViewRelatorio.fxml";
		Parent root = FXMLLoader.load(getClass().getResource(caminho));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
