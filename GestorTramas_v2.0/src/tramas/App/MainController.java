package tramas.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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

		view = new BorderPane();

		campania.set(new Campania());
		irACampania();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		view.setTop(menuController.getMenuPrincipal());
//		irACampania();
	}
	
	public void irAAventura(Aventura aventura) {
		aventurasController.setAventura(aventura);
		view.setCenter(aventurasController.getView());
	}
	
	public void irACampania() {
		view.setTop(menuController.getMenuPrincipal());
		view.setCenter(campaniaController.getView());
	}
	
	public BorderPane getView() {
		return view;
	}

}
