package br.com.ed2.gui.janela;

import java.io.IOException;
import java.util.List;

import br.com.ed2.desenho.arvore.Arvore2D;
import br.com.ed2.estruturas.InserirDeletarBuscar;
import br.com.ed2.estruturas.avl.AvlTree;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Classe controller da Tela principal. Tela que irá fazer a maiorias das
 * operações.
 * 
 * @author Alan Tavares
 *
 */
public class ControllerTelaPrincipal {

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
	@FXML
	private Pane containerDasEstruturas2D = new AnchorPane();
	
	private List<Pane> desenhos;
	private int indexDesenhos;
	
	InserirDeletarBuscar<Integer> estruturaEscolhida;

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
				this.estruturaEscolhida.inserir(valor);
			} //else{
				// Alert "insira alguma coisa".
			//}
					
			this.desenhos = ((AvlTree<Integer>) this.estruturaEscolhida).getRelatorio().getDesenhos();
			this.indexDesenhos = 0;
			this.containerDasEstruturas2D.getChildren().add(this.desenhos.get(this.indexDesenhos));
		}
		
	}
	
	// Evento dos Menu
	public void menuAvl() {	
		ativarBotoes();
		textoTitulo.setText("AVL");

		// Incializa variáveis
		// Cria o modelo de árvore
		this.estruturaEscolhida = new AvlTree<>(true);
	}
	
	// Eventos de botao
	public void btProximo(){
		if(indexDesenhos < desenhos.size()-1){
			indexDesenhos += 1;
			containerDasEstruturas2D.getChildren().clear();
			containerDasEstruturas2D.getChildren().add(desenhos.get(indexDesenhos));
			System.out.println(desenhos.get(indexDesenhos));
		}
	}
	
	public void btAnterior(){
		if(indexDesenhos > 0){
			indexDesenhos -= 1;
			containerDasEstruturas2D.getChildren().clear();
			containerDasEstruturas2D.getChildren().add(desenhos.get(indexDesenhos));
			System.out.println(desenhos.get(indexDesenhos));
		}
	}
}
