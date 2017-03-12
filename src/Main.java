import br.com.ed2.gui.janela.ControllerTelaPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new ControllerTelaPrincipal().start(primaryStage);
	}

}
