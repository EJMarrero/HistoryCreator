package tramas.App;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
<<<<<<< HEAD:GestorTramas_v3.0/src/tramas/App/GestorApp.java
import javafx.stage.StageStyle;
=======
>>>>>>> 57f6c314fab1f308de7bc604d942dd096783c768:GestorTramas_v2.0/src/tramas/App/GestorApp.java

public class GestorApp extends Application {
	
	private static Image cursor;
	private static Stage primaryStage;
	
<<<<<<< HEAD:GestorTramas_v3.0/src/tramas/App/GestorApp.java
	public SplashController controllerSplash;
=======
	public MainController controller;
>>>>>>> 57f6c314fab1f308de7bc604d942dd096783c768:GestorTramas_v2.0/src/tramas/App/GestorApp.java
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		cursor = new Image("/tramas/resources/cursor.png");
		
		GestorApp.primaryStage = primaryStage;
		
<<<<<<< HEAD:GestorTramas_v3.0/src/tramas/App/GestorApp.java
		/**
		 * @author Claudio Roldán
		 * A partir de este punto, el MainApp cargar un SplashScreen, y este 
		 * a su vez, cargar el MainController mediante un hilo.
		 */
		controllerSplash = new SplashController();
		Scene scene = new Scene(controllerSplash.getView());
=======
		controller = new MainController();

		Scene scene = new Scene(controller.getView(), 1650, 750);
>>>>>>> 57f6c314fab1f308de7bc604d942dd096783c768:GestorTramas_v2.0/src/tramas/App/GestorApp.java
		
		// INICIALIZAR AUDIO 
		  
		/*Media sound = new Media(new File("audio.mp3").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.2);
		mediaPlayer.play();*/
		
		// INICIALIZAR CURSORES
		
		scene.setCursor(new ImageCursor(GestorApp.getCursor()));
		scene.getStylesheets().add(getClass().getResource("/tramas/resources/style.css").toExternalForm());

<<<<<<< HEAD:GestorTramas_v3.0/src/tramas/App/GestorApp.java
		primaryStage.centerOnScreen();
		primaryStage.initStyle(StageStyle.UNDECORATED);
=======
//		primaryStage.setMaxHeight(768);
//		primaryStage.setMaxWidth(1366);
//		primaryStage.setMaximized(true);
		
		primaryStage.setTitle("GestorTramas_v.2.0");		
		primaryStage.setFullScreenExitHint("");
>>>>>>> 57f6c314fab1f308de7bc604d942dd096783c768:GestorTramas_v2.0/src/tramas/App/GestorApp.java
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
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
