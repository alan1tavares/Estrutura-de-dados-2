package br.com.ed2.teste;

import br.com.ed2.gui.arvore.Node2D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CirculoTeste extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Node2D circulo = new Node2D(24, 100);
		circulo.setX(100);
		
		Pane root = new Pane();
		root.getChildren().add(circulo.desenho());
		
		Scene scene = new Scene(root, 300, 250);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
