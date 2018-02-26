package tramas.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SplashController implements Initializable {
	@FXML
	private StackPane view;

	@FXML
	private ImageView imagenSplash;

	public MainController controller = new MainController();

	/**
	 * @author Claudio Roldán
	 * Se crea un nuevo Stage, el cual mantendrá el mismo nombre, ya que este será el Stage Principal
	 * 
	 */
	private static Stage primaryStage;

	public SplashController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SplashFXML.fxml"));
		loader.setController(this);
		loader.load();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**
		 * @author Claudio Roldán
		 * Llama a la clase anidada que hereda de Thread, donde su método run
		 * cargará a MainController después de 4secs. 
		 * 
		 */
		new SplashScreen().start();
	}
	public StackPane getView() {
		return view;
	}
	class SplashScreen extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(4000);

				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						primaryStage = new Stage();
						Scene scene = new Scene(controller.getView());
						// INICIALIZAR CURSORES
						scene.setCursor(new ImageCursor(GestorApp.getCursor()));
						scene.getStylesheets()
								.add(getClass().getResource("/tramas/resources/style.css").toExternalForm());
						primaryStage.setTitle("GestorTramas_v.2.1");
						primaryStage.getIcons().add(new Image("/tramas/resources/d20.png"));
						primaryStage.setFullScreenExitHint("");
						primaryStage.setScene(scene);
						primaryStage.setFullScreen(true);
						primaryStage.show();
						view.getScene().getWindow().hide();

					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return El stage principal de la aplicación, con su icono
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
}
