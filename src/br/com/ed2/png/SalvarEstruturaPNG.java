package br.com.ed2.png;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;


public class SalvarEstruturaPNG {
	
	private File file;
	
	public void salvarComoPNG(Scene scene, File file) {
		if (scene != null && file != null) {
			WritableImage snapshot = scene.snapshot(null);
			this.file = file;

			try {
				ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
			} catch (IOException e) {
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
				e.printStackTrace();
			}
		} else
			System.out.println("nulo");
	}
}
