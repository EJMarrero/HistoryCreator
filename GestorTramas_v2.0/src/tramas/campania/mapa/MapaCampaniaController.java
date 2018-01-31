package tramas.campania.mapa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tramas.campania.CampaniaController;
import tramas.menu.MenuController;

public class MapaCampaniaController implements Initializable {
	
	//Referencia al controlador padre
	private CampaniaController mainController;
	private MenuController menuController = new MenuController();
	
	@FXML
	private BorderPane view;
	@FXML
	private ImageView contenedorMapa;
	
	public ImageView getContenedorMapa() {
		return contenedorMapa;
	}

	public void setContenedorMapa(ImageView contenedorMapa) {
		this.contenedorMapa = contenedorMapa;
	}

	private Image imagen;

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	private Scene scene;

	
	public MapaCampaniaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MapaCampaniaView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		scene = new Scene(view);
		view.setTop(menuController.getMenuPrincipal());
		contenedorMapa.setImage(imagen);
	}

	public void setMainController(CampaniaController mainController) {
		this.mainController=mainController;
		
	}
	
	public BorderPane getView() {
		return view;
	}
	
	public void show(Stage parentStage) {
//		scene.getStylesheets().add(getClass().getResource("styleAventura.css").toExternalForm());
		parentStage.setScene(scene);
		parentStage.setFullScreen(true);
		parentStage.setFullScreenExitHint("");
		parentStage.setResizable(false);
		parentStage.show();
		
	}

}
