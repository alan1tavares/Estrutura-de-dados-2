package br.com.ed2.png;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SalvarEstruturaPNG {
	public void savarComoPNG(Pane node) {
		if (node != null) {
			WritableImage snapshot = node.getScene().snapshot(null);

			File file = new File("teste.png");

			try {
				ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
			} catch (IOException e) {
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
				e.printStackTrace();
			}
		} else System.out.println("nulo");
	}
}
