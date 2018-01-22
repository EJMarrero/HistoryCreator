package tramas.editorImagen;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import tramas.notasCampaña.DragResizeMod;

public class EditorImagen {

	private static Image image;
	private static Rectangle rectangle = new Rectangle(100,100);
	
	
	
	public static Rectangle redimensionarArchivo (File imageFile) {
		image = new Image(imageFile.toURI().toString());
		DragResizeMod.makeResizable(rectangle, null);
		rectangle.setFill(new ImagePattern(image));
		return rectangle;
		
	}
	
}
