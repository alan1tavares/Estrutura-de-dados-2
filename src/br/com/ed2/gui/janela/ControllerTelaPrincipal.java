package br.com.ed2.gui.janela;

import avl.AVL;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.geometry.Insets;

/**
 * Classe controller da Tela principal. Tela que irá fazer a maiorias das
 * operações.
 * 
 * @author Alan Tavares
 *
 */
public class ControllerTelaPrincipal extends Application {

	@FXML
	private Button btInserir;
	@FXML
	private TextField tfInserir;
	@FXML
	private Button btDeletar;
	@FXML
	private Button btBuscar;
	@FXML
	private Label textoTitulo;
	
	//private Estrutura estruturaEscolhida;
	
	
	public void start(Stage primaryStage) throws Exception {
		String caminho = "/br/com/ed2/gui/janela/ViewTelaPrincipal.fxml";
		Parent root = FXMLLoader.load(getClass().getResource(caminho));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Método usado para ativar os botões de inserir, deletar e buscar.
	 */
	public void ativarBotoes() {
		btInserir.setDisable(false);
		btDeletar.setDisable(false);
		btBuscar.setDisable(false);
	}

	public void botaoInserir() {
		// Verifica se existe valores para serem inseridos
		if(tfInserir.getText() != null){
			// Separa os valores em um array
			String[] valores = tfInserir.getText().split(" ");
			// Vai inseindo cada valor na estrutura
			for (String string : valores) {
				int valor = Integer.parseInt(string);
				//this.estruturaEscolhida.inserir(valor);
			} //else{
				// Alert "insira alguma coisa".
			//}
		}
		
	}
	
	// Evento dos Menu
	public void menuAvl() {
		//this.estruturaEscolhida = new AVL();
		ativarBotoes();
		textoTitulo.setText("AVL");
		
	}
}
