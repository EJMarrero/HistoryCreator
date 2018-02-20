package tramas.App;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GestorApp extends Application {
	
	private static Image cursor;
	private static Stage primaryStage;
	
	public MainController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		cursor = new Image("/tramas/resources/cursor.png");
		
		GestorApp.primaryStage = primaryStage;
		
		controller = new MainController();

		Scene scene = new Scene(controller.getView(), 1650, 750);
		
		// INICIALIZAR AUDIO 
		  
		/*Media sound = new Media(new File("audio.mp3").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.2);
		mediaPlayer.play();*/
		
		// INICIALIZAR CURSORES
		scene.setCursor(new ImageCursor(GestorApp.getCursor()));
		scene.getStylesheets().add(getClass().getResource("/tramas/resources/style.css").toExternalForm());

//		primaryStage.setMaxHeight(768);
//		primaryStage.setMaxWidth(1366);
//		primaryStage.setMaximized(true);
		
		primaryStage.setTitle("GestorTramas_v.2.0");		
		primaryStage.setFullScreenExitHint("");
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
		
	}
	
	public static Image getCursor() {
		return cursor;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);

	}

}
