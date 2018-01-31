package tramas.aventuras;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tramas.App.GestorApp;
import tramas.main.MainController;
import tramas.model.Aventura;

public class AventurasController implements Initializable {
	
	//Referencia al controlador padre
	private MainController mainController;
	
	//Modelo
	private Aventura aventuraModel = new Aventura();
	private ObjectProperty<Aventura> aventura = new SimpleObjectProperty<>(this, "aventura");

	private Scene scene;
	@FXML
	private TextField tituloAventuraText;
    @FXML
    private BorderPane view;

    public AventurasController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AventuraView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		scene = new Scene(view);
		
		aventuraModel.nombreProperty().bind(tituloAventuraText.textProperty());
		
	}
	
	

	
	

    @FXML
    void onAbrirTesoroButtonAction(ActionEvent event) {

    }

    @FXML
    void onAniadirEncuentroButtonAction(ActionEvent event) {

    }

    @FXML
    void onAniadirNPCButtonAction(ActionEvent event) {

    }

    @FXML
    void onAniadirNotaAventuraButtonAction(ActionEvent event) {

    }

    @FXML
    void onAniadirTableroButtonAction(ActionEvent event) {

    }



    @FXML
    void onBorrarEncuentroButtonAction(ActionEvent event) {

    }

    @FXML
    void onBorrarNPCButtonAction(ActionEvent event) {

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
    void onVerNotaAventuraButtonAction(ActionEvent event) {

    }

    @FXML
    void onVerTesoroButtonAction(ActionEvent event) {

    }
    
    @FXML
    void onGuardarAventuraButtonAction(ActionEvent event) throws IOException {
    	mainController.getCampaniaModel().getAventuras().add(aventuraModel);
    	
    }
    
    @FXML
    void onBackAction(ActionEvent event) throws IOException {
    	mainController.show(GestorApp.getPrimaryStage());

    }
    
	public void show(Stage parentStage) { 
		scene.getStylesheets().add(getClass().getResource("styleAventura.css").toExternalForm());
		parentStage.setScene(scene);
		parentStage.setFullScreen(true);
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
