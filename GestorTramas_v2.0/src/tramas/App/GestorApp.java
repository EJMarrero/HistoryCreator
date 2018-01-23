package tramas.App;

import java.io.File;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tramas.main.MainController;

public class GestorApp extends Application {
	
	private static Image cursor;
	private static Stage primaryStage;
	public MainController controller;
	public static Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		cursor = new Image("/tramas/resources/cursor.png");
		GestorApp.primaryStage = primaryStage;
		controller = new MainController();

		// CARGAR FONT
		Font.loadFont(GestorApp.class.getResource("IrishUncialfabeta-Bold.ttf").toExternalForm(), 10);

		primaryStage.setTitle("GestorTramas_v.2.0");
		scene = new Scene(controller.getView(), 1050, 650);
		
		// INICIALIZAR AUDIO 
		  
		/*Media sound = new Media(new File("audio.mp3").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.2);
		mediaPlayer.play();*/
		
		// INICIALIZAR CURSORES
		scene.setCursor(new ImageCursor(GestorApp.getCursor()));

		scene.getStylesheets().add(getClass().getResource("/tramas/main/mainStyle.css").toExternalForm());
		primaryStage.setFullScreenExitHint("");
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static Image getCursor() {
		return cursor;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}



	public static Scene getScene() {
		return scene;
	}

	public static void main(String[] args) {
		launch(args);

	}

}
