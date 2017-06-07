package br.com.ed2.teste;

import avl.AVL;
import br.com.ed2.gui.desenho.arvore.Arvore2D;
import br.com.ed2.gui.desenho.arvore.Node2D;
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
		//Node2D node2d = new Node2D(24, 5);

		String s = "50 17 12 9 14 23 19 72 54 67 76";
		root.getChildren().addAll(arvore2d.arvorePreOrdem(s, 4, 24, 600, 0));

		Scene scene = new Scene(root, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void insertAvl(AVL avl) {
		avl.insert(14);
		avl.insert(5);
	}

}
