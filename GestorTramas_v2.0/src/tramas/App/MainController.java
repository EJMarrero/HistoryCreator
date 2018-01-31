package tramas.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import tramas.aventuras.AventurasController;
import tramas.campania.CampaniaController;
import tramas.menu.MenuController;
import tramas.model.Aventura;
import tramas.model.Campania;

public class MainController implements Initializable {
	
	// modelo 
	private ObjectProperty<Campania> campania = new SimpleObjectProperty<>(this, "campania");
	
	// sub-controllers
	private MenuController menuController;
	private CampaniaController campaniaController;
	private AventurasController aventurasController;
	
	// view
	@FXML
	private BorderPane view;
	
	public MainController() throws IOException {
		menuController = new MenuController();
		menuController.setMainController(this);
		menuController.campaniaProperty().bindBidirectional(campania);
		
		campaniaController = new CampaniaController();
		campaniaController.setMainController(this);
		campaniaController.campaniaProperty().bindBidirectional(campania);
		
		aventurasController = new AventurasController();
		aventurasController.setMainController(this);
		aventurasController.campaniaProperty().bindBidirectional(campania);
		//CARGAR EL FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainControllerView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		campania.set(new Campania());
		view.setTop(menuController.getMenuPrincipal());
		view.setCenter(campaniaController.getView());
	}
	
	public void irAAventura(Aventura aventura) {
		aventurasController.setAventura(aventura);
		view.setCenter(aventurasController.getView());
	}
	
	public void irACampania() {
		view.setCenter(campaniaController.getView());
	}
	
	public BorderPane getView() {
		return view;
	}

}
