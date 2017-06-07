package br.com.ed2.gui.principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ed2.Main;
import br.com.ed2.configuracao.Configuracao;
import br.com.ed2.estruturas.PassoaAPasso;
import br.com.ed2.estruturas.arvore.AvlTree;
import br.com.ed2.estruturas.arvore.SplayTree;
import br.com.ed2.gui.exportar.png.ControllerExportaPng;
import br.com.ed2.gui.relatorio.ControllerRelatorio;
import br.com.ed2.log.LogDot;
import br.com.ed2.relatorio.Relatorio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
	private TextField tfBuscar;
	@FXML
	private Pane containerDasEstruturas2D;
	@FXML
	private Pane conitanerPai;

	private Relatorio relatorio;
	PassoaAPasso<Integer> estruturaEscolhida;
	private Node bp;
	// Onde vai ser carregado a view do relatorio
	private FXMLLoader fxmlLoaderRelatorio;

	// Eventos de botões

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
			inicializarJanelaDoRelaorio();
			ControllerRelatorio controller = fxmlLoaderRelatorio.getController();
			controller.setRelatorio(this.relatorio);

		}

	}

	public void botaoBuscar() {
		// TODO Teste
		SplayTree st = (SplayTree) this.estruturaEscolhida;
		List<LogDot> log = st.getLog();
		for (LogDot logDot : log) {
			System.out.println(logDot.getLog());
		}

		// Verifica se existe valores para serem inseridos
		if (tfBuscar.getText() != null) {
			String string = tfBuscar.getText();
			int valor = Integer.parseInt(string);
			this.estruturaEscolhida.buscar(valor);

			this.relatorio = this.estruturaEscolhida.getRelatorio();
			inicializarJanelaDoRelaorio();
			ControllerRelatorio controller = fxmlLoaderRelatorio.getController();
			controller.setRelatorio(this.relatorio);
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

	public void menuSplay() {
		ativarBotoes();
		textoTitulo.setText("Splay");

		// Incializa variáveis
		// Cria o modelo de árvore
		this.estruturaEscolhida = new SplayTree<>(true);
	}

	// Barra de menu -> Arquivo/Exporta/PNG
	/**
	 * Chama a janela que ira salvar os passos da estrutura em vários arquivos
	 * png em uma pasta escolhida. Cada passo será uma imagem
	 */
	public void menuExportaPNG() {
		// Carrega a view
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource("/br/com/ed2/gui/exportar/png/ViewExportaPng.fxml"));
		try {
			AnchorPane menuExportaPNG = (AnchorPane) fxmlLoader.load();

			// Cria o palco
			Stage stage = new Stage();
			// Cria cena
			Scene scene = new Scene(menuExportaPNG);
			stage.setScene(scene);
			// Impede da jane ser redimensionada
			stage.setResizable(false);
			// Desabilito a janela pricipal
			this.conitanerPai.setDisable(true);
			// Pega o controller
			ControllerExportaPng controllerPNG = fxmlLoader.getController();
			//
			controllerPNG.setRelatorio(relatorio);
			// Mostra a janela de exportar png e espero ela ser fechada e
			// retornar para o chamador
			stage.showAndWait();
			// Abilito a janela principal
			this.conitanerPai.setDisable(false);
			// Carrega o controller da janela

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Barra de Menu -> Arquivo/Configuração
	/**
	 * Chama a janela de configuração
	 * 
	 * @throws IOException
	 */
	public void configuracao() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource("/br/com/ed2/gui/configuracao/Configuracao.fxml"));

		AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(anchorPane);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inicializarJanelaDoRelaorio();
	}

	/*
	 * Métodos auxiliares
	 */

	// Método usado para ativar os botões de inserir, deletar e buscar.
	private void ativarBotoes() {
		btInserir.setDisable(false);
		btDeletar.setDisable(false);
		btBuscar.setDisable(false);
	}

	private void inicializarJanelaDoRelaorio() {
		// Caminho da view
		String caminho = "/br/com/ed2/gui/relatorio/ViewRelatorio.fxml";
		// Carrega a view
		this.fxmlLoaderRelatorio = new FXMLLoader(getClass().getResource(caminho));

		try {
			bp = this.fxmlLoaderRelatorio.load();
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
