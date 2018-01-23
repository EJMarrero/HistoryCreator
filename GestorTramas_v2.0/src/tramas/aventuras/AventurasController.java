package tramas.aventuras;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tramas.App.GestorApp;
import tramas.main.MainController;
import tramas.menu.MenuController;
import tramas.model.Aventura;
import tramas.model.Encuentro;
import tramas.model.Mapa;
import tramas.model.NPC;
import tramas.model.Nota;
import tramas.model.Tesoros;
import tramas.notasCampaña.NotasCampañaController;

public class AventurasController implements Initializable {
	
	//Referencia al controlador padre
	private MainController mainController;
	
	//Modelo
	private Aventura aventuraModel = new Aventura();
	private ObjectProperty<Aventura> aventura = new SimpleObjectProperty<>(this, "aventura");
	private ObjectProperty<NPC> npcSeleccionado = new SimpleObjectProperty<>(this, "npcSeleccionado");
	private ObjectProperty<Encuentro> encuentroSeleccionado = new SimpleObjectProperty<>(this, "encuentroSeleccionado");
	private ObjectProperty<Nota> notaSeleccionada = new SimpleObjectProperty<>(this, "notaSeleccionada");

	

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
    private VBox NPCVBox;

    @FXML
    private ListView<NPC> npcsListView;

    @FXML
    private ListView<Mapa> tableroListView;

    @FXML
    private ListView<Encuentro> encuentrosListView;

    @FXML
    private ListView<Nota> notasAventuraListView;

    @FXML
    private ListView<Tesoros> tesorosListView;

    private MenuController menuController = new MenuController();
    

    public AventurasController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AventuraView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		scene = new Scene(view, 1050, 650);
		
		view.setTop(menuController.getMenuPrincipal());
		
		aventuraModel.nombreProperty().bind(tituloAventuraText.textProperty());
		
		npcsListView.itemsProperty().bind(aventuraModel.pnjsProperty());
		npcSeleccionado.bind(npcsListView.getSelectionModel().selectedItemProperty());
		
		encuentrosListView.itemsProperty().bind(aventuraModel.encuentrosProperty());
		encuentroSeleccionado.bind(encuentrosListView.getSelectionModel().selectedItemProperty());
		
		notasAventuraListView.itemsProperty().bind(aventuraModel.notasProperty());
		notaSeleccionada.bind(notasAventuraListView.getSelectionModel().selectedItemProperty());
		
		
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
			aventuraModel.getNotas().add(nueva);
		}
    }

    @FXML
    void onAniadirTableroButtonAction(ActionEvent event) {

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

    }

    @FXML
    void onBorrarTableroButtonAction(ActionEvent event) {

    }

    @FXML
    void onBorrarTesoroButtonAction(ActionEvent event) {

    }

    @FXML
    void onCambiarPortraitAventuraButtonAction(ActionEvent event) {

    }

    @FXML
    void onCompendioButtonAction(ActionEvent event) {

    }

    @FXML
    void onExpandirImagenTableroButtonAction(ActionEvent event) {

    }
    


    @FXML
    void onRollButtonAction(ActionEvent event) {

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
    	mainController.getCampania().getAventuras().add(aventuraModel);
    	
    }
    
    @FXML
    void onBackAction(ActionEvent event) throws IOException {
    	mainController.show(GestorApp.getPrimaryStage());

    }
    
	public void show(Stage parentStage) { 
		scene.getStylesheets().add(getClass().getResource("styleAventura.css").toExternalForm());
		parentStage.setScene(scene);
		parentStage.setFullScreenExitHint("");
		parentStage.setResizable(false);
		parentStage.show();
		
	}
	



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
	

}
