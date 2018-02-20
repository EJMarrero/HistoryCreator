package tramas.aventuras;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tramas.App.GestorApp;
import tramas.App.MainController;
import tramas.aventuras.tablero.TableroController;
import tramas.calculadora.controller.CalculadoraController;
import tramas.campania.CampaniaController;
import tramas.menu.MenuController;
import tramas.model.Aventura;
import tramas.model.Campania;
import tramas.model.Encuentro;
import tramas.model.Mapa;
import tramas.model.NPC;
import tramas.model.Nota;
import tramas.model.Tesoros;
import tramas.notasCampaña.NotasCampañaController;

public class AventurasController implements Initializable {
	
	//Referencia al controlador padre
	private MainController mainController;
	
	//Subcontroladores
	private CargarTableroController tableroController = new CargarTableroController();
	private TableroController pizarraController = new TableroController();
	
	//Modelo
	private ObjectProperty<Aventura> aventura = new SimpleObjectProperty<>(this, "aventura");
	private ObjectProperty<NPC> npcSeleccionado = new SimpleObjectProperty<>(this, "npcSeleccionado");
	private ObjectProperty<Encuentro> encuentroSeleccionado = new SimpleObjectProperty<>(this, "encuentroSeleccionado");
	private ObjectProperty<Nota> notaSeleccionada = new SimpleObjectProperty<>(this, "notaSeleccionada");
	private ObjectProperty<Mapa> tableroSeleccionado = new SimpleObjectProperty<>(this, "tableroSeleccionado");
	
	private ObjectProperty<Campania> campania = new SimpleObjectProperty<>(this, "campania");

	
	// View
	private Stage stageCrearTablero;
	private Scene scene;
	@FXML
	private TextField tituloAventuraText;
    @FXML
    private BorderPane view;

    @FXML
    private Button cambiarPortraitAventuraButton, borrarPortraitAventuraButton, aniadirNPCButton, verNPCButton, borrarNPCButton,
    aniadirTableroButton, borrarTableroButton, aniadirEncuentroButton, verEncuentroButton, borrarEncuentroButton, expandirImagenTableroButton,
    aniadirNotaAventuraButton, verNotaAventuraButton, borrarNotaAventuraButton, abrirTesoroButton, verTesoroButton, borrarTesoroButton,
    compendioButton,  onRollButton, guardarAventuraButon;

    
    @FXML
    private ListView<NPC> npcsListView;

    @FXML
    private ListView<Mapa> tableroListView;

    @FXML
    private ListView<Encuentro> encuentrosListView;

    @FXML
    private ListView<Nota> notasAventuraListView;
    @FXML
    private ImageView portraitImage, tableroImagen;

    @FXML
    private ListView<Tesoros> tesorosListView;

    
    

    public AventurasController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AventuraView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		scene = new Scene(view, 1650, 750);
//		view.setTop(menuController.getMenuPrincipal());
		
		tableroController.setMainController(this);
		
		pizarraController.setMainController(this);
		pizarraController.aventuraProperty().bindBidirectional(aventura);
		
		npcSeleccionado.bind(npcsListView.getSelectionModel().selectedItemProperty());
		encuentroSeleccionado.bind(encuentrosListView.getSelectionModel().selectedItemProperty());
		notaSeleccionada.bind(notasAventuraListView.getSelectionModel().selectedItemProperty());
		tableroSeleccionado.bind(tableroListView.getSelectionModel().selectedItemProperty());
		
		tableroListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					if(event.getClickCount() == 2) {
						tableroImagen.setImage(tableroSeleccionado.get().getImagen());
					}
				}
			}
			
		});
		
		//Botones desactivados en esta version
		compendioButton.setDisable(true);
		abrirTesoroButton.setDisable(true);
		verTesoroButton.setDisable(true);
		borrarTesoroButton.setDisable(true);
		verEncuentroButton.setDisable(true);
		verNPCButton.setDisable(true);
		
		aventura.addListener((o, ov, nv) -> onAventuraChanged(o, ov, nv));
		aventura.set(new Aventura());
	}
	
	

	
	

    private void onAventuraChanged(ObservableValue<? extends Aventura> o, Aventura ov, Aventura nv) {
		if(ov != null) {

			tituloAventuraText.textProperty().unbindBidirectional(ov.nombreProperty());
			npcsListView.itemsProperty().unbindBidirectional(ov.pnjsProperty());
			encuentrosListView.itemsProperty().unbindBidirectional(ov.encuentrosProperty());
			notasAventuraListView.itemsProperty().unbindBidirectional(ov.notasProperty());
			portraitImage.imageProperty().unbindBidirectional(ov.portraitProperty());
			tableroListView.itemsProperty().unbindBidirectional(ov.tablerosProperty());

		}
		if (nv != null) {
			tituloAventuraText.textProperty().bindBidirectional(nv.nombreProperty());
			npcsListView.itemsProperty().bindBidirectional(nv.pnjsProperty());
			encuentrosListView.itemsProperty().bindBidirectional(nv.encuentrosProperty());
			notasAventuraListView.itemsProperty().bindBidirectional(nv.notasProperty());
			portraitImage.imageProperty().bindBidirectional(nv.portraitProperty());
			tableroListView.itemsProperty().bindBidirectional(nv.tablerosProperty());

			
		}
	}

	@FXML
    void onAbrirTesoroButtonAction(ActionEvent event) {

    }

    @FXML
    void onAniadirEncuentroButtonAction(ActionEvent event) {
    	//TODO enlace a la interfaz de encuentros que sustituirá al dialog siguiente
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nuevo Encuentro");
		dialog.setHeaderText("Introduce el nombre del Encuentro");
		dialog.setContentText("Nombre:");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(name -> encuentrosListView.getItems().add(new Encuentro(name)));
    }

    @FXML
    void onAniadirNPCButtonAction(ActionEvent event) {
    	//TODO ventana a Compendio que sustituirá al dialog siguiente
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nuevo NPC");
		dialog.setHeaderText("Introduce el nombre del NPC");
		dialog.setContentText("Nombre:");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(name -> npcsListView.getItems().add(new NPC(name)));
    }

    @FXML
    void onAniadirNotaAventuraButtonAction(ActionEvent event) throws IOException {
    	NotasCampañaController controllerNotasCampania = new NotasCampañaController();
    	Nota nueva = controllerNotasCampania.show(GestorApp.getPrimaryStage());
		if (nueva.getTexto() !=null && nueva.getTitulo()!=null) {
			aventura.get().getNotas().add(nueva);
		}
    }

    @FXML
    void onAniadirTableroButtonAction(ActionEvent event) throws IOException {
    	stageCrearTablero = new Stage();
    	StackPane capa = new StackPane();
    	capa.setPrefWidth(300);
    	capa.setPrefHeight(300);
    	capa.getChildren().add(tableroController.getView());
    	capa.setStyle("-fx-background-color: rgba(0, 0, 0, 0.9); -fx-background-radius: 10; ");
    	stageCrearTablero.setScene(new Scene(capa));
    	stageCrearTablero.initStyle(StageStyle.UNDECORATED);
    	stageCrearTablero.initOwner(GestorApp.getPrimaryStage());
    	stageCrearTablero.show();
    	
    	
    }

    public void irATablero() {
    
    	
		pizarraController.getContenedorMapa().setImage(tableroImagen.getImage());
		pizarraController.show(GestorApp.getPrimaryStage());
		
    }

    @FXML
    void onBorrarEncuentroButtonAction(ActionEvent event) {

    }

    @FXML
    void onBorrarNPCButtonAction(ActionEvent event) {
		String nombre = npcSeleccionado.get().getNombre();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar NPC");
		alert.setHeaderText("Se dispone a eliminar al NPC '" + nombre + "'.");
		alert.setContentText("¿Desea eliminar a "+ nombre+" ?");
		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> resultado = alert.showAndWait();
		if (ButtonType.YES.equals(resultado.get())) {
			npcsListView.getItems().remove(npcSeleccionado.get());
		}
    }

    @FXML
    void onBorrarNotaAventuraButtonAction(ActionEvent event) {

    }

    @FXML
    void onBorrarPortraitAventuraButton(ActionEvent event) {
    	aventura.get().setPortrait(null);
    }

    @FXML
    void onBorrarTableroButtonAction(ActionEvent event) {
    	String nombre = tableroSeleccionado.get().getNombre();
    	
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar a tablero");
		alert.setHeaderText("Se dispone a eliminar el tablero '" + nombre + "'.");
		alert.setContentText("¿Desea eliminar el tablero?");
		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> resultado = alert.showAndWait();
		if (ButtonType.YES.equals(resultado.get())) {
			tableroListView.getItems().remove(tableroSeleccionado.get());
			tableroImagen.setImage(null);
		}
    }
    

    @FXML
    void onBorrarTesoroButtonAction(ActionEvent event) {

    }

    @FXML
    void onCambiarPortraitAventuraButtonAction(ActionEvent event) {
    	Stage stage = new Stage();
		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Cargar imagen");
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos de imagen(*.jpg o *.png)",
				"*.jpg", "*.png");
		fChooser.getExtensionFilters().add(extFilterJPG);
		File imageFile = fChooser.showOpenDialog(stage);
		
		if(imageFile != null) {
			Image image = new Image(imageFile.toURI().toString());
			aventura.get().setPortrait(image);
		}
    }

    @FXML
    void onCompendioButtonAction(ActionEvent event) {

    }

    @FXML
    void onExpandirImagenTableroButtonAction(ActionEvent event) {
    	irATablero();
    }
    


    @FXML
    void onRollButtonAction(ActionEvent event) throws IOException {
    	CalculadoraController controller = new CalculadoraController();
    	controller.show(GestorApp.getPrimaryStage());
    }

    @FXML
    void onVerEncuentroButtonAction(ActionEvent event) {

    }

    @FXML
    void onVerNPCButtonAction(ActionEvent event) {

    }

    @FXML
    void onVerNotaAventuraButtonAction(ActionEvent event) throws IOException {
		NotasCampañaController controllerNotasCampania = new NotasCampañaController();
		controllerNotasCampania.setNotaDevuelta(notaSeleccionada.get());
		Nota cargada = controllerNotasCampania.show(GestorApp.getPrimaryStage());
		notaSeleccionada.get().setTitulo(cargada.getTitulo());
		notaSeleccionada.get().setTexto(cargada.getTexto());
		notasAventuraListView.refresh();
    }

    @FXML
    void onVerTesoroButtonAction(ActionEvent event) {

    }
    
    @FXML
    void onGuardarAventuraButtonAction(ActionEvent event) throws IOException {
    	campania.get().getAventuras().add(aventura.get().clonar());
    	mainController.irACampania();
    }
    
    @FXML
    void onLimpiarAventuraButtonAction (ActionEvent event) throws IOException {
    	tituloAventuraText.setText("");
    	npcsListView.getItems().clear();
    	encuentrosListView.getItems().clear();
    	notasAventuraListView.getItems().clear();
    	portraitImage.setImage(null);
    	tableroListView.getItems().clear();
    	tableroImagen.setImage(null);
    	
    }
    
    @FXML
    void onBackAction(ActionEvent event) throws IOException {
    	mainController.irACampania();
    }
    
//	public void show(Stage parentStage) { 
//		scene.getStylesheets().add(getClass().getResource("styleAventura.css").toExternalForm());
//		parentStage.setScene(scene);
//		parentStage.setFullScreen(true);
////		parentStage.setMaximized(true);
//		parentStage.show();
//		
//	}

	public BorderPane getView() {
		return view;
	}

	public void setMainController(MainController mainController) {
		this.mainController=mainController;
		
	}

	public final ObjectProperty<Aventura> aventuraProperty() {
		return this.aventura;
	}
	

	public final Aventura getAventura() {
		return this.aventuraProperty().get();
	}
	

	public final void setAventura(final Aventura aventura) {
		this.aventuraProperty().set(aventura);
	}

	public Stage getStageCrearTablero() {
		return stageCrearTablero;
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
