package br.com.ed2.gui.principal;

import java.util.List;

import br.com.ed2.estruturas.InserirDeletarBuscar;
import br.com.ed2.estruturas.avl.AvlTree;
import br.com.ed2.estruturas.relatorio.Relatorio;
import br.com.ed2.estruturas.relatorio.RotuloRelatorioAvl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Classe controller da Tela principal. Tela que irá fazer a maiorias das
 * operações.
 * 
 * @author Alan Tavares
 *
 */
public class ControllerTelaPrincipal {

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
	private List<Pane> desenhos;
	private int indexDesenhos;

	InserirDeletarBuscar<Integer> estruturaEscolhida;

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

			this.relatorio = ((AvlTree<Integer>) this.estruturaEscolhida).getRelatorio();
			this.desenhos = this.relatorio.getDesenhos();
			this.indexDesenhos = 0;
			this.containerDasEstruturas2D.getChildren().clear();
			this.containerDasEstruturas2D.getChildren().add(this.desenhos.get(this.indexDesenhos));
			// Atualiza a largura e altura do container
			this.containerDasEstruturas2D.setPrefHeight(this.desenhos.get(this.desenhos.size() - 1).getHeight());
			this.containerDasEstruturas2D.setPrefWidth(this.desenhos.get(this.desenhos.size() - 1).getWidth());

			atulizaLbTotalPasso();
			atualizaPassoAPasso();
			
			System.out.println(this.relatorio.relatorioTextual());
		}

	}

	/*
	 * Eventos do barra de menu
	 */

	// Barra de menu -> Estrutura -> AVL

	/*
	 * Ativa os botões de inserir, deletar e buscar. Muda o tituloa para AVL.
	 * Instancia a AVL como a estrutura escolhhidas
	 */
	public void menuAvl() {
		ativarBotoes();
		textoTitulo.setText("AVL");

		// Incializa variáveis
		// Cria o modelo de árvore
		this.estruturaEscolhida = new AvlTree<>(true);
	}

	/*
	 * Essa parte possui os evenos de botoes
	 */

	// Método que avança um passo da estrutura.
	public void btProximo() {
		if (indexDesenhos < desenhos.size() - 1) {
			indexDesenhos += 1;
			containerDasEstruturas2D.getChildren().clear();
			Pane arvore = desenhos.get(indexDesenhos);
			containerDasEstruturas2D.getChildren().add(arvore);
		}
		atualizaPassoAPasso();
	}

	// Método que volta uma passo da estrutura.
	public void btAnterior() {
		if (indexDesenhos > 0) {
			indexDesenhos -= 1;
			containerDasEstruturas2D.getChildren().clear();
			containerDasEstruturas2D.getChildren().add(desenhos.get(indexDesenhos));
		}
		atualizaPassoAPasso();
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

	// Método usado para atualizar label que informa o numero total de passos
	// que tem a estrura.
	private void atulizaLbTotalPasso() {
		if (this.desenhos != null)
			this.totalDePassos.setText("0" + this.desenhos.size());
	}

	// Método usado para atualizar a label que informa o número do passo que
	// está a estrutura.
	private void atualizaPassoAPasso() {
		this.passoAtual.setText("0" + (this.indexDesenhos + 1));

		if (this.relatorio != null)
			// Autliza a lbl informando o que aconterceu nesse passo a passo
			this.lbInformacaoPassoAPasso
					.setText(this.relatorio.relatorioSemEsse(RotuloRelatorioAvl.SAIDA).get(this.indexDesenhos));
	}
}
