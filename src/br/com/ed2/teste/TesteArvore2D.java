package br.com.ed2.teste;

import avl.AVL;
import br.com.ed2.gui.arvore.Arvore2D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TesteArvore2D extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();

		Arvore2D arvore2d = new Arvore2D();

		String s = "50 17 12 9 14 23 19 72 54 67 76";
		root.getChildren().add(arvore2d.arvorePreOrdem(s, 4, 24, 400, 0));

		Scene scene = new Scene(root, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void insertAvl(AVL avl) {
		avl.insert(14);
		avl.insert(5);
	}

}
