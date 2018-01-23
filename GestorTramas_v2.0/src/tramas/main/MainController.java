package tramas.main;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.apache.axis.encoding.ser.Base64Serializer;

import javafx.beans.binding.BooleanBinding;
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
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tramas.App.GestorApp;
import tramas.aventuras.AventurasController;
import tramas.editorImagen.EditorImagen;
import tramas.mapacampania.MapaCampaniaController;
import tramas.menu.MenuController;
import tramas.model.Aventura;
import tramas.model.Campania;
//import tramas.model.Mapa;
import tramas.model.Nota;
import tramas.model.Personaje;
import tramas.notasCampaña.NotasCampañaController;

public class MainController implements Initializable {
	
	// Vista
	private Stage stage;
	private Scene scene;
	@FXML
	private BorderPane view;
	@FXML
	private ImageView mapaImage;
	@FXML
	private ListView<Nota> notasCampaniaList;

	@FXML
	private ListView<Aventura> aventurasCampaniaList;

	@FXML
	private ListView<Personaje> personajesCampaniaList;
	
	@FXML
	private Button abrirNotasCampaniaButton, compendioButton, aniadirNotasCampaniaButton, borrarNotasCampaniaButton, guardarAventurasButton,
	aniadirAventurasButton, verAventurasButton, borrarAventurasButton, guardarMapaButton, expandirMapaButton, borrarMapaButton,
	aniadirMapaButton, guardarPersonajesButton, aniadirPersonajesButton, verPersonajesButton, borrarPersonajesButton, tesorosButton;

	public BorderPane getView() {
		return view;
	}
	
	//SubControladores
	private AventurasController aventuraController = new AventurasController();
	private MenuController menuController = new MenuController();
	private MapaCampaniaController mapaController = new MapaCampaniaController();
	private NotasCampañaController notasCampañaController = new NotasCampañaController();
	
	// Modelo
//	private Campania campaniaModel = new Campania();
	private ObjectProperty<Campania> campania = new SimpleObjectProperty<>(this, "campania");
	private ObjectProperty<Nota> notaSeleccionada = new SimpleObjectProperty<>(this, "notaSeleccionada");
	private ListProperty<Nota> listaNotas = new SimpleListProperty<>(this, "listaNotas");
	private ObjectProperty<Aventura> aventuraSeleccionada = new SimpleObjectProperty<>(this, "aventuraSeleccionada");
	private ObjectProperty<Personaje> personajeSeleccionado = new SimpleObjectProperty<>(this, "personajeSeleccionado");


	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
		aventuraController.setMainController(this);
		mapaController.setMainController(this);
		notasCampañaController.setMainController(this);
		menuController.setMainController(this);
		
		view.setTop(menuController.getMenuPrincipal());
		
//		notasCampaniaList.itemsProperty().bind(listaNotas);
		
		
//		notasCampaniaList.itemsProperty().bind(campania.get().notasProperty());
//		aventurasCampaniaList.itemsProperty().bind(campaniaModel.aventurasProperty());
//		personajesCampaniaList.itemsProperty().bind(campaniaModel.personajesProperty());
//		mapaImage.imageProperty().bind(campaniaModel.mapaCampaniaProperty().get().imagenProperty());
//		campaniaModel.mapaCampaniaProperty().bind(mapaImage.imageProperty());



		notaSeleccionada.bind(notasCampaniaList.getSelectionModel().selectedItemProperty());
		aventuraSeleccionada.bind(aventurasCampaniaList.getSelectionModel().selectedItemProperty());
		personajeSeleccionado.bind(personajesCampaniaList.getSelectionModel().selectedItemProperty());

		abrirNotasCampaniaButton.disableProperty().bind(notaSeleccionada.isNull());
		borrarNotasCampaniaButton.disableProperty().bind(notaSeleccionada.isNull());

		guardarAventurasButton.disableProperty().bind(aventuraSeleccionada.isNull());
		verAventurasButton.disableProperty().bind(aventuraSeleccionada.isNull());
		borrarAventurasButton.disableProperty().bind(aventuraSeleccionada.isNull());

		guardarPersonajesButton.disableProperty().bind(personajeSeleccionado.isNull());
		verPersonajesButton.disableProperty().bind(personajeSeleccionado.isNull());
		borrarPersonajesButton.disableProperty().bind(personajeSeleccionado.isNull());

//		BooleanBinding desactivarMapa = campania.get().mapaCampaniaProperty().isNull();
//		guardarMapaButton.disableProperty().bind(desactivarMapa);
//		expandirMapaButton.disableProperty().bind(desactivarMapa);
//		borrarMapaButton.disableProperty().bind(desactivarMapa);
		
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
		campania.set(new Campania());

	}



	private void onCampaniaChanged(ObservableValue<? extends Campania> o, Campania ov, Campania nv) {
		if (ov != null) {
			notasCampaniaList.itemsProperty().unbind();
			aventurasCampaniaList.itemsProperty().unbind();
			personajesCampaniaList.itemsProperty().unbind();
			mapaImage.imageProperty().unbind();
		}
		if (nv != null) {
			notasCampaniaList.itemsProperty().bind(nv.notasProperty());
			aventurasCampaniaList.itemsProperty().bind(nv.aventurasProperty());
			personajesCampaniaList.itemsProperty().bind(nv.personajesProperty());
//			campania.get().mapaCampaniaProperty().bind(nv.mapaCampaniaProperty());
			mapaImage.imageProperty().bind(nv.mapaCampaniaProperty());
		}
	}

	@FXML
	void onAbrirMenuItemAction(ActionEvent event) {
		
	}

	@FXML
	void onAniadirAventurasButtonAction(ActionEvent event) throws IOException {
		
		aventuraController.show(GestorApp.getPrimaryStage());
	}

	@FXML
	void onAniadirMapaButtonAction(ActionEvent event) throws IOException {
		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Cargar imagen");
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos de imagen(*.jpg o *.png)", "*.jpg", "*.png");
		fChooser.getExtensionFilters().add(extFilterJPG);
		File imageFile = fChooser.showOpenDialog(stage);
		Image image = new Image(imageFile.toURI().toString());
		
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		BufferedImage bf = ImageIO.read(imageFile);
//		ImageIO.write(bf, "jpg", bos);
//		byte[] imageBytes = bos.toByteArray();
//		Base64Encoder encoder = new Base64Serializer(null, null);
		
		//Reconstruye la imagen
//		BufferedImage bf = ImageIO.read(imageFile);
//		WritableImage wr = new WritableImage(bf.getWidth(), bf.getHeight());
//		PixelWriter pw = wr.getPixelWriter();
//		for (int i = 0; i < bf.getWidth(); i++) {
//			for (int j = 0; j < bf.getHeight(); j++) {
//				pw.setArgb(i, j, bf.getRGB(i,j));
//			}
//			
//		}
		campania.get().setMapaCampania(image);
		
		
		
	}

	@FXML
	void onAniadirNotasCampaniaButtonAction(ActionEvent event) throws IOException {
		NotasCampañaController controllerNotasCampania = new NotasCampañaController();
		Nota nueva = controllerNotasCampania.show(GestorApp.getPrimaryStage());
		if (nueva.getTexto() !=null && nueva.getTitulo()!=null) {
//			campaniaModel.getNotas().add(nueva);
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
<<<<<<< HEAD
=======
		//mapaImage.setImage(null);
		//Hola caraculo
>>>>>>> 4b90d6bc2fc082e8693c1f082f4efcacd7731c5f
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
//		mapaController.setImagen(mapaImage.getImage());
		mapaController.getContenedorMapa().setImage(mapaImage.getImage());
		System.out.println(mapaImage.getImage().toString());
//		System.out.println(mapaController.getImagen().toString());
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
	void onGuardarPersonajesButtonAction(ActionEvent event) {

	}

	@FXML
	void onNuevoMenuItemAction(ActionEvent event) {

	}

	@FXML
	void onTesorosButtonAction(ActionEvent event) {

	}

	@FXML
	void onVerAventurasButtonAction(ActionEvent event) {
		aventuraController.show(GestorApp.getPrimaryStage());
	}

	@FXML
	void onVerPersonajesButtonAction(ActionEvent event) {

	}
	
	public void show(Stage parentStage) {
		scene = GestorApp.getScene();
		scene.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
		parentStage.setScene(scene);
		parentStage.setFullScreen(false);
		parentStage.show();
		
		
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
	

}
