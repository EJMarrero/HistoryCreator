package tramas.notasCampaña;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tramas.campania.CampaniaController;
import tramas.editorImagen.EditorImagen;
import tramas.model.Nota;
import tramas.model.adapter.ImageUtils;

public class NotasCampañaController implements Initializable {

	// Referencia al controlador padre

	// Referencia al controlador padre
	@SuppressWarnings("unused")
	private CampaniaController mainController;

	// Modelo
	private Nota notaNueva;
	private Nota notaDevuelta = new Nota();

	private ObjectProperty<Nota> notaGuardada = new SimpleObjectProperty<>(this, "notaGuardada");

	// Vista
	@FXML
	private BorderPane view;
	@FXML
	private HTMLEditor cajaEditor;
	private Rectangle rectangle;
	private File aux;

	@FXML
	private TextField tituloText;
	@FXML
	private BorderPane imagenPane;

	@FXML
	private Button guardarNotaButton, cargarImagenButton, agregarNotaButton, limpiarButton;

	private Stage stage;

	public NotasCampañaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NotasCampañaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tituloText.textProperty().bindBidirectional(notaDevuelta.tituloProperty());

		// AÑADIR BOTON A BARRA DE TOOLBAR DE HTML EDITOR
		// Node node = cajaEditor.lookup(".top-toolbar");
		//
		// if (node instanceof ToolBar) {
		// ToolBar bar = (ToolBar) node;
		// ImageView graphic = new ImageView(new Image("/tramas/resources/imagen.png",
		// 15, 40, true,true));
		// Button imagenButton = new Button("", graphic);
		// bar.getItems().add(imagenButton);
		// imagenButton.setOnAction(new EventHandler<ActionEvent>() {
		// @Override
		// public void handle(ActionEvent event) {
		// try {
		// onCargarImagenButtonAction(event);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// });
		// }
	}

	public Nota show(Stage parentStage) {
		stage = new Stage();
		stage.initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/tramas/resources/style.css").toExternalForm());
		if (parentStage != null) {
			stage.initOwner(parentStage);
		}
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Nueva nota de Campaña");
		stage.setResizable(true);
		stage.setScene(scene);
		cajaEditor.setHtmlText(notaDevuelta.getTexto());
		notaGuardada.set(notaDevuelta);
		stage.showAndWait();
		return notaDevuelta;
	}

	@FXML
	void onCargarImagenButtonAction(ActionEvent event) throws IOException {
		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Cargar imagen");
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos de imagen(*.jpg o *.png)",
				"*.jpg", "*.png");
		fChooser.getExtensionFilters().add(extFilterJPG);
		File imageFile = fChooser.showOpenDialog(stage);

		rectangle = EditorImagen.redimensionarArchivo(imageFile);
		imagenPane.setCenter(rectangle);
		aux = imageFile;

	}

	@FXML
	void onAgregarNotaButtonAction(ActionEvent event) throws FileNotFoundException {
		Image image = new Image(aux.toURI().toString());
		// cajaEditor.setHtmlText(cajaEditor.getHtmlText()+"<img src=\""+ aux.toURI()+ "
		// \"width=\""+rectangle.getWidth()+"\"+ height=\""+rectangle.getHeight()+"\"
		// >");
		cajaEditor.setHtmlText(
				cajaEditor.getHtmlText() + "<img src=\"" + "data:image/png;base64," + ImageUtils.encodeImage(image)
						+ " \"width=\"" + rectangle.getWidth() + "\"+ height=\"" + rectangle.getHeight() + "\" >");
		imagenPane.setCenter(null);
	}

	@FXML
	void onLimpiarButtonAction(ActionEvent event) {
		imagenPane.setCenter(null);
	}

	@FXML
	void onGuardarNotaButtonAction(ActionEvent event) {
		notaNueva = new Nota();
		notaNueva.setTexto(cajaEditor.getHtmlText());
		notaNueva.setTitulo(tituloText.getText());
		Nota.copiar(notaNueva, notaDevuelta);
		stage.close();
	}

	@FXML
	void onAtrasButton(ActionEvent event) {
		stage.close();
	}

	public BorderPane getView() {
		return view;
	}

	public Nota getNotaDevuelta() {
		return notaDevuelta;
	}

	public void setNotaDevuelta(Nota notaDevuelta) {
		this.notaDevuelta.setTexto(notaDevuelta.getTexto());
		this.notaDevuelta.setTitulo(notaDevuelta.getTitulo());
	}

	public void setMainController(CampaniaController mainController) {
		this.mainController = mainController;
	}

	public final ObjectProperty<Nota> notaGuardadaProperty() {
		return this.notaGuardada;
	}

	public final Nota getNotaGuardada() {
		return this.notaGuardadaProperty().get();
	}

	public final void setNotaGuardada(final Nota notaGuardada) {
		this.notaGuardadaProperty().set(notaGuardada);
	}

}
