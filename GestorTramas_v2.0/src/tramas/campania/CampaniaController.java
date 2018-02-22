package tramas.campania;

/**
 * Implementacion del controlador CampaniaController.
 * Éste tendrá las siguientes especificaciones:
 * 
 * 	*ListView de Notas
 * 	*ListView de Aventuras
 * 	*ListView de Personajes
 * 	*Contenedor para el Mapa de la Campaña, que contendrá una imagen.
 * 	*Botón que ejecutará la calculadora d20.
 * 
 * @author Eduardo Marrero
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import tramas.App.GestorApp;
import tramas.App.MainController;
import tramas.calculadora.controller.CalculadoraController;
import tramas.calculadora.logica.Calculadora;
import tramas.campania.mapa.MapaCampaniaController;
import tramas.model.Aventura;
import tramas.model.Campania;
//import tramas.model.Mapa;
import tramas.model.Nota;
import tramas.model.Personaje;
import tramas.notasCampaña.NotasCampañaController;

public class CampaniaController implements Initializable {

	// controlador padre
	private MainController mainController;

	// Vista
	@FXML
	private BorderPane view;
	@FXML
	private VBox zonaMapa;
	@FXML
	private ImageView mapaImage;
	@FXML
	private ListView<Nota> notasCampaniaList;
	@FXML
	private ListView<Aventura> aventurasCampaniaList;
	@FXML
	private ListView<Personaje> personajesCampaniaList;
	@FXML
	private Button abrirNotasCampaniaButton, compendioButton, aniadirNotasCampaniaButton, borrarNotasCampaniaButton,
			guardarAventurasButton, aniadirAventurasButton, verAventurasButton, borrarAventurasButton,
			guardarMapaButton, expandirMapaButton, borrarMapaButton, aniadirMapaButton, importarPersonajesButton,
			aniadirPersonajesButton, verPersonajesButton, borrarPersonajesButton, tesorosButton;

	// SubControladores
	private MapaCampaniaController mapaController = new MapaCampaniaController();
	private NotasCampañaController notasCampañaController = new NotasCampañaController();

	// Modelo
	private ObjectProperty<Campania> campania = new SimpleObjectProperty<>(this, "campania");

	private ObjectProperty<Nota> notaSeleccionada = new SimpleObjectProperty<>(this, "notaSeleccionada");
	private ListProperty<Nota> listaNotas = new SimpleListProperty<>(this, "listaNotas");
	private ObjectProperty<Aventura> aventuraSeleccionada = new SimpleObjectProperty<>(this, "aventuraSeleccionada");
	private ObjectProperty<Personaje> personajeSeleccionado = new SimpleObjectProperty<>(this, "personajeSeleccionado");

	public CampaniaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CampaniaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		mapaController.setMainController(this);
		notasCampañaController.setMainController(this);

		notaSeleccionada.bind(notasCampaniaList.getSelectionModel().selectedItemProperty());
		aventuraSeleccionada.bind(aventurasCampaniaList.getSelectionModel().selectedItemProperty());
		personajeSeleccionado.bind(personajesCampaniaList.getSelectionModel().selectedItemProperty());

		abrirNotasCampaniaButton.disableProperty().bind(notaSeleccionada.isNull());
		borrarNotasCampaniaButton.disableProperty().bind(notaSeleccionada.isNull());

		guardarAventurasButton.disableProperty().bind(aventuraSeleccionada.isNull());
		verAventurasButton.disableProperty().bind(aventuraSeleccionada.isNull());
		borrarAventurasButton.disableProperty().bind(aventuraSeleccionada.isNull());

		importarPersonajesButton.disableProperty().bind(personajeSeleccionado.isNull());
		verPersonajesButton.disableProperty().bind(personajeSeleccionado.isNull());
		borrarPersonajesButton.disableProperty().bind(personajeSeleccionado.isNull());

		// BooleanBinding desactivarMapa =
		// campania.get().mapaCampaniaProperty().isNull();
		// guardarMapaButton.disableProperty().bind(desactivarMapa);
		// expandirMapaButton.disableProperty().bind(desactivarMapa);
		// borrarMapaButton.disableProperty().bind(desactivarMapa);

		EventHandler<Event> efectoButton = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Media sound = new Media(new File("botoneffecto.mp3").toURI().toString());
				MediaPlayer mediaPlayer = new MediaPlayer(sound);
				mediaPlayer.setVolume(0.2);
				mediaPlayer.play();
			}
		};

		aniadirNotasCampaniaButton.setOnMouseClicked(efectoButton);
		aniadirAventurasButton.setOnMouseClicked(efectoButton);
		aniadirPersonajesButton.setOnMouseClicked(efectoButton);
		aniadirMapaButton.setOnMouseClicked(efectoButton);

		campania.addListener((o, ov, nv) -> onCampaniaChanged(o, ov, nv));

	}

	/**
	 * Siempre que se carge una campaña nueva, este método desbindeará los elementos:
	 * 		*La lista de notas de Campaña
	 * 		*La lista de Aventuras
	 * 		*La lista de Personajes
	 * 		*El mapa de la campaña
	 * de la campaña vieja y los bindeará a la campaña nueva.
	 * @param o
	 * @param ov
	 * @param nv
	 */
	
	private void onCampaniaChanged(ObservableValue<? extends Campania> o, Campania ov, Campania nv) {
		if (ov != null) {
			notasCampaniaList.itemsProperty().unbindBidirectional(ov.notasProperty());
			aventurasCampaniaList.itemsProperty().unbindBidirectional(ov.aventurasProperty());
			personajesCampaniaList.itemsProperty().unbindBidirectional(ov.personajesProperty());
			mapaImage.imageProperty().unbindBidirectional(ov.mapaCampaniaProperty());
		}
		if (nv != null) {
			notasCampaniaList.itemsProperty().bindBidirectional(nv.notasProperty());
			aventurasCampaniaList.itemsProperty().bindBidirectional(nv.aventurasProperty());
			personajesCampaniaList.itemsProperty().bindBidirectional(nv.personajesProperty());
			// campania.get().mapaCampaniaProperty().bind(nv.mapaCampaniaProperty());
			mapaImage.imageProperty().bindBidirectional(nv.mapaCampaniaProperty());
		}
	}

	/**
	 * Este método añade una nueva aventura y llama al método IrAAventura() que 
	 * carga la vista de AventuraController desde su controlador padre (MainController)
	 * @param event
	 * @throws IOException
	 */

	@FXML
	void onAniadirAventurasButtonAction(ActionEvent event) throws IOException {
		mainController.irAAventura(new Aventura());
	}

	
	/**
	 * Este método añade una imagen a la zona de mapas de la campaña a través de la carga de un fichero con 
	 * extensión .jgp o .png desde el un directorio local.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void onAniadirMapaButtonAction(ActionEvent event) throws IOException {
		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Cargar imagen");
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos de imagen(*.jpg o *.png)",
				"*.jpg", "*.png");
		fChooser.getExtensionFilters().add(extFilterJPG);
		File imageFile = fChooser.showOpenDialog(GestorApp.getPrimaryStage());
		Image image = new Image(imageFile.toURI().toString());

		// ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// BufferedImage bf = ImageIO.read(imageFile);
		// ImageIO.write(bf, "jpg", bos);
		// byte[] imageBytes = bos.toByteArray();
		// Base64Encoder encoder = new Base64Serializer(null, null);

		// Reconstruye la imagen
		// BufferedImage bf = ImageIO.read(imageFile);
		// WritableImage wr = new WritableImage(bf.getWidth(), bf.getHeight());
		// PixelWriter pw = wr.getPixelWriter();
		// for (int i = 0; i < bf.getWidth(); i++) {
		// for (int j = 0; j < bf.getHeight(); j++) {
		// pw.setArgb(i, j, bf.getRGB(i,j));
		// }
		//
		// }
		campania.get().setMapaCampania(image);

	}

	/**
	 * Este método añade una nueva campaña 
	 * @param event
	 * @throws IOException
	 */
	
	@FXML
	void onAniadirNotasCampaniaButtonAction(ActionEvent event) throws IOException {
		NotasCampañaController controllerNotasCampania = new NotasCampañaController();
		Nota nueva = controllerNotasCampania.show(GestorApp.getPrimaryStage());
		if (nueva.getTexto() != null && nueva.getTitulo() != null) {
			// campaniaModel.getNotas().add(nueva);
			campania.get().getNotas().add(nueva);
		}
	}

	@FXML
	void onAbrirNotasCampaniaButtonAction(ActionEvent event) throws IOException {
		NotasCampañaController controllerNotasCampania = new NotasCampañaController();
		controllerNotasCampania.setNotaDevuelta(notaSeleccionada.get());
		Nota cargada = controllerNotasCampania.show(GestorApp.getPrimaryStage());
		notaSeleccionada.get().setTitulo(cargada.getTitulo());
		notaSeleccionada.get().setTexto(cargada.getTexto());
		notasCampaniaList.refresh();
	}

    @FXML
    void onRollButtonAction(ActionEvent event) throws IOException {
    	CalculadoraController controller = new CalculadoraController();
    	controller.show(GestorApp.getPrimaryStage());
    }
	
	@FXML
	void onAniadirPersonajesButtonAction(ActionEvent event) {

	}

	@FXML
	void onBorrarAventurasButtonAction(ActionEvent event) {
		String nombre = aventuraSeleccionada.get().getNombre();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar aventura");
		alert.setHeaderText("Se dispone a eliminar la aventura '" + nombre + "'.");
		alert.setContentText("¿Desea eliminar la aventura?");
		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> resultado = alert.showAndWait();
		if (ButtonType.YES.equals(resultado.get())) {
			aventurasCampaniaList.getItems().remove(aventuraSeleccionada.get());
		}
	}

	@FXML
	void onBorrarMapaButtonAction(ActionEvent event) {
		campania.get().setMapaCampania(null);
	}

	@FXML
	void onBorrarNotasCampaniaButtonAction(ActionEvent event) {
		String nombre = notaSeleccionada.get().getTitulo();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar nota de trasfondo");
		alert.setHeaderText("Se dispone a eliminar el trasfondo '" + nombre + "'.");
		alert.setContentText("¿Desea eliminar el trasfondo?");
		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> resultado = alert.showAndWait();
		if (ButtonType.YES.equals(resultado.get())) {
			notasCampaniaList.getItems().remove(notaSeleccionada.get());
		}
	}

	@FXML
	void onBorrarPersonajesButtonAction(ActionEvent event) {

	}

	@FXML
	void onCerrarMenuItemAction(ActionEvent event) {

	}

	@FXML
	void onCompendioButtonAction(ActionEvent event) {

	}

	@FXML
	void onExpandirMapaButtonAction(ActionEvent event) {
		// mapaController.setImagen(mapaImage.getImage());
		mapaController.getContenedorMapa().setImage(mapaImage.getImage());
		// System.out.println(mapaController.getImagen().toString());
		mapaController.show(GestorApp.getPrimaryStage());

	}

	@FXML
	void onGuardarAventurasButtonAction(ActionEvent event) {

	}

	@FXML
	void onGuardarMapaButtonActtion(ActionEvent event) {

	}

	@FXML
	void onGuardarMenuItemAction(ActionEvent event) {

	}

	@FXML
	void onImportarPersonajesButtonAction(ActionEvent event) {

	}

	@FXML
	void onNuevoMenuItemAction(ActionEvent event) {

	}

	@FXML
	void onTesorosButtonAction(ActionEvent event) {

	}

	@FXML
	void onVerAventurasButtonAction(ActionEvent event) {
		mainController.irAAventura(aventuraSeleccionada.get().clonar());
	}

	@FXML
	void onVerPersonajesButtonAction(ActionEvent event) {

	}
	


	public final ObjectProperty<Campania> campaniaProperty() {
		return this.campania;
	}

	public final Campania getCampania() {
		return this.campaniaProperty().get();
	}

	public final void setCampania(final Campania campania) {
		this.campaniaProperty().set(campania);
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public BorderPane getView() {
		return view;
	}

}
