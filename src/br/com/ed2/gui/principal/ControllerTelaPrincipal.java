package br.com.ed2.gui.principal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.ed2.estruturas.PassoaAPasso;
import br.com.ed2.estruturas.avl.AvlTree;
import br.com.ed2.estruturas.relatorio.Relatorio;
import br.com.ed2.gui.relatorio.ControllerRelatorio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Classe controller da Tela principal. Tela que irá fazer a maiorias das
 * operações.
 * 
 * @author Alan Tavares
 *
 */
public class ControllerTelaPrincipal implements Initializable {

	// Botoes
	@FXML
	private Button btInserir;
	@FXML
	private Button btDeletar;
	@FXML
	private Button btBuscar;

	// Labels
	@FXML
	private Label textoTitulo;
	@FXML
	private Label totalDePassos;
	@FXML
	private Label passoAtual;
	@FXML
	private Label lbInformacaoPassoAPasso;

	// Outros
	@FXML
	private TextField tfInserir;
	@FXML
	private Pane containerDasEstruturas2D;

	private Relatorio relatorio;
	PassoaAPasso<Integer> estruturaEscolhida;

	/*
	 * Métodos
	 */

	public void botaoInserir() {
		// Verifica se existe valores para serem inseridos
		if (tfInserir.getText() != null) {

			// Separa os valores em um array
			String[] valores = tfInserir.getText().split(" ");
			// Vai inseindo cada valor na estrutura
			for (String string : valores) {
				int valor = Integer.parseInt(string);
				this.estruturaEscolhida.inserir(valor);
			} // else{
				// Alert "insira alguma coisa".
				// }

			this.relatorio = this.estruturaEscolhida.getRelatorio();
			ControllerRelatorio.relatorio = this.relatorio;
			inicializarJanelaDoRelaorio();

		}

	}

	/*
	 * Eventos do barra de menu
	 */

	// Barra de menu -> Estrutura -> AVL

	/**
	 * Ativa os botões de inserir, deletar e buscar. Muda o tituloa para AVL.
	 * Instancia a AVL como a estrutura escolhidas
	 */
	public void menuAvl() {
		ativarBotoes();
		textoTitulo.setText("AVL");

		// Incializa variáveis
		// Cria o modelo de árvore
		this.estruturaEscolhida = new AvlTree<>(true);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inicializarJanelaDoRelaorio();
	}

	/*
	 * Métodos auxiliakres
	 */

	// Método usado para ativar os botões de inserir, deletar e buscar.
	private void ativarBotoes() {
		btInserir.setDisable(false);
		btDeletar.setDisable(false);
		btBuscar.setDisable(false);
	}

	private void inicializarJanelaDoRelaorio() {
		String caminho = "/br/com/ed2/gui/relatorio/ViewRelatorio.fxml";
		try {
			Node bp = FXMLLoader.load(getClass().getResource(caminho));
			containerDasEstruturas2D.getChildren().clear();
			containerDasEstruturas2D.getChildren().add(bp);
			AnchorPane.setTopAnchor(bp, 0.0);
			AnchorPane.setLeftAnchor(bp, 0.0);
			AnchorPane.setRightAnchor(bp, 0.0);
			AnchorPane.setBottomAnchor(bp, 0.0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
