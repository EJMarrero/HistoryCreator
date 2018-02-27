package tramas.App;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GestorApp extends Application {

	private static Image cursor;
	private static Stage primaryStage;

	public SplashController controllerSplash;

	@Override
	public void start(Stage primaryStage) throws Exception {
		//hola
		cursor = new Image("/tramas/resources/cursor.png");

		GestorApp.primaryStage = primaryStage;

		/**
		 * @author Claudio Roldán A partir de este punto, el MainApp cargar un
		 *         SplashScreen, y este a su vez, cargar el MainController mediante un
		 *         hilo.
		 */
		controllerSplash = new SplashController();
		Scene scene = new Scene(controllerSplash.getView());

		// INICIALIZAR AUDIO

		/*
		 * Media sound = new Media(new File("audio.mp3").toURI().toString());
		 * MediaPlayer mediaPlayer = new MediaPlayer(sound); mediaPlayer.setVolume(0.2);
		 * mediaPlayer.play();
		 */

		// INICIALIZAR CURSORES

		scene.setCursor(new ImageCursor(GestorApp.getCursor()));
		scene.getStylesheets().add(getClass().getResource("/tramas/resources/style.css").toExternalForm());

		primaryStage.centerOnScreen();
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("GestorTramas_v.3.0");
		primaryStage.setScene(scene);
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
