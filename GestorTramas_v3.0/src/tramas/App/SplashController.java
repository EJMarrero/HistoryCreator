package tramas.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SplashController implements Initializable {
	private Task<Void> tarea;

	@FXML
	private VBox view;

	@FXML
	private ImageView imagenSplash;

	@FXML
	private ProgressBar progressBar;

	public MainController controller = new MainController();

	private IntegerProperty progreso = new SimpleIntegerProperty(this, "progreso");

	/**
	 * @author Claudio Roldán Se crea un nuevo Stage, el cual mantendrá el mismo
	 *         nombre, ya que este será el Stage Principal
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
		 * Se bindea la barra de progreso a propiedad integer y se setea a 0
		 */
		progressBar.progressProperty().bind(progreso.divide(100.0));
		progreso.set(0);

		/**
		 * @author Claudio Roldán Llama a la clase anidada que hereda de Thread, donde
		 *         su método run cargará a MainController después de 4secs.
		 * 
		 */
		setIniciarProgressBar();

	}

	public VBox getView() {
		return view;
	}

	public void setIniciarProgressBar() {
		tarea = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				int MAX = 800;
				for (int i = 0; i < MAX; i++) {
					updateProgress(i, MAX);
					Thread.sleep(10L);
				}
				updateProgress(MAX, MAX);
				return null;
			}

		};
		progreso.bind(tarea.progressProperty().multiply(100));
		tarea.setOnSucceeded(e -> {
			new SplashScreen().start();
		});
		new Thread(tarea).start();

	}

	class SplashScreen extends Thread {
		@Override
		public void run() {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					primaryStage = new Stage();
					Scene scene = new Scene(controller.getView());
					// INICIALIZAR CURSORES
					scene.setCursor(new ImageCursor(GestorApp.getCursor()));
					scene.getStylesheets().add(getClass().getResource("/tramas/resources/style.css").toExternalForm());
					primaryStage.setTitle("GestorTramas_v.3.0");
					primaryStage.getIcons().add(new Image("/tramas/resources/d20.png"));
					primaryStage.setFullScreenExitHint("");
					primaryStage.setScene(scene);
					primaryStage.setMaximized(true);
					primaryStage.show();
					view.getScene().getWindow().hide();
				}

			});
		}
	}

	/**
	 * @return El stage principal de la aplicación, con su icono
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
}
