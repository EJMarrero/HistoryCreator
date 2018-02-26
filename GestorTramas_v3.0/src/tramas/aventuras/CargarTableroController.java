package tramas.aventuras;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tramas.model.Mapa;

public class CargarTableroController implements Initializable {

	// Referencia al controlador padre
	@SuppressWarnings("unused")
	private AventurasController aventurasController;

	@FXML
	StackPane view;
	@FXML
	private TextField nombreText;

	@FXML
	private ImageView miniaturaImage;

	@FXML
	private Button cargarImagenButton;

	@FXML
	private Button aceptarButton;

	@FXML
	private Button cancelarButton;
	
	//Tablero que se asignará en aventura.
	private Mapa tablero;
	

	public Mapa getTablero() {
		return tablero;
	}

	public void setTablero(Mapa tablero) {
		this.tablero = tablero;
	}

	public CargarTableroController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CargarTableroView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void onAceptarButtonAction(ActionEvent event) throws IOException {
		tablero = new Mapa();
		if (miniaturaImage.getImage() != null || nombreText.getText() != null) {
			tablero.setNombre(nombreText.getText());
			tablero.setImagen(miniaturaImage.getImage());
			onCancelarButtonAction(event);
		}
	}

	@FXML
	void onCancelarButtonAction(ActionEvent event) {
	    Stage stage = (Stage) cancelarButton.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void onCargarImagenButtonAction(ActionEvent event) {
		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Cargar imagen");
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos de imagen(*.jpg o *.png)",
				"*.jpg", "*.png");
		fChooser.getExtensionFilters().add(extFilterJPG);
		File imageFile = fChooser.showOpenDialog(aceptarButton.getScene().getWindow());
		if (imageFile != null) {
			Image image = new Image(imageFile.toURI().toString());
			miniaturaImage.setImage(image);
		}
	}

	public void setMainController(AventurasController aventurasController) {
		this.aventurasController = aventurasController;

	}

	public StackPane getView() {
		return view;
	}
}
