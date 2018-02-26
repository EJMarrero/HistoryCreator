package tramas.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import tramas.App.GestorApp;
import tramas.aventuras.AventurasController;
import tramas.model.Aventura;
import tramas.model.Campania;
//import tramas.model.Mapa;
import tramas.model.Nota;
import tramas.model.Personaje;
import tramas.notasCampaña.NotasCampañaController;

public class MainController implements Initializable {

	//SubControladores
	private AventurasController aventuraController = new AventurasController();
	
	
	// Vista
	private Scene scene;
	@FXML
	private BorderPane view;
	@FXML
	private ListView<Nota> notasCampaniaList;
	@FXML
	private Button abrirNotasCampaniaButton;
	@FXML
	private Button aniadirNotasCampaniaButton;
	@FXML
	private Button borrarNotasCampaniaButton;
	@FXML
	private ListView<Aventura> aventurasCampaniaList;
	@FXML
	private Button guardarAventurasButton;
	@FXML
	private Button aniadirAventurasButton;
	@FXML
	private Button verAventurasButton;
	@FXML
	private Button borrarAventurasButton;
	@FXML
	private ImageView mapaImage;
	@FXML
	private Button guardarMapaButton;
	@FXML
	private Button expandirMapaButton;
	@FXML
	private Button borrarMapaButton;
	@FXML
	private Button aniadirMapaButton;
	@FXML
	private ListView<Personaje> personajesCampaniaList;
	@FXML
	private Button guardarPersonajesButton;
	@FXML
	private Button aniadirPersonajesButton;
	@FXML
	private Button verPersonajesButton;
	@FXML
	private Button borrarPersonajesButton;
	@FXML
	private Button tesorosButton;
	@FXML
	private Button compendioButton;

	public BorderPane getView() {
		return view;
	}
	



	// Modelo
	private Campania campaniaModel = new Campania();
	//private ObjectProperty<Campania> campania = new SimpleObjectProperty<>(this, "campania");
	private ObjectProperty<Nota> notaSeleccionada = new SimpleObjectProperty<>(this, "notaSeleccionada");
	private ObjectProperty<Aventura> aventuraSeleccionada = new SimpleObjectProperty<>(this, "aventuraSeleccionada");
	private ObjectProperty<Aventura> aventura = new SimpleObjectProperty<>(this, "aventura");
	private ObjectProperty<Personaje> personajeSeleccionado = new SimpleObjectProperty<>(this, "personajeSeleccionado");
	//private ObjectProperty<Mapa> mapaCampania = new SimpleObjectProperty<>(this, "mapaCampania");

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
		
		notasCampaniaList.itemsProperty().bind(campaniaModel.notasProperty());
		aventurasCampaniaList.itemsProperty().bind(campaniaModel.aventurasProperty());
		personajesCampaniaList.itemsProperty().bind(campaniaModel.personajesProperty());
//		mapaImage.imageProperty().bind(campaniaModel.mapaCampaniaProperty().get().imagenProperty());

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

		BooleanBinding desactivarMapa = campaniaModel.mapaCampaniaProperty().isNull();
		guardarMapaButton.disableProperty().bind(desactivarMapa);
		expandirMapaButton.disableProperty().bind(desactivarMapa);
		borrarMapaButton.disableProperty().bind(desactivarMapa);
		
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
		
//		pasa referencia a los subcontroladores para poder cambiar de scene
		aventuraController.setMainController(this);
		// bindea el controlador de la scene "Aventura" con el modelo
		aventuraController.aventuraProperty().bind(aventura);

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

	}

	@FXML
	void onAniadirNotasCampaniaButtonAction(ActionEvent event) throws IOException {
		NotasCampañaController controllerNotasCampania = new NotasCampañaController();
		Nota nueva = controllerNotasCampania.show(GestorApp.getPrimaryStage());
		if (nueva.getTexto() !=null && nueva.getTitulo()!=null) {
			campaniaModel.getNotas().add(nueva);
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

	}

	@FXML
	void onBorrarMapaButtonAction(ActionEvent event) {

	}

	@FXML
	void onBorrarNotasCampaniaButtonAction(ActionEvent event) {

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

	}

	@FXML
	void onVerPersonajesButtonAction(ActionEvent event) {

	}
	
	public void show(Stage parentStage) {
		scene = GestorApp.getScene();
		scene.getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
		parentStage.setScene(scene);
		parentStage.setFullScreen(true);
		parentStage.show();
		
		
	}
	

	public Campania getCampaniaModel() {
		return campaniaModel;
	}

	public void setCampaniaModel(Campania campaniaModel) {
		this.campaniaModel = campaniaModel;
	}

}
