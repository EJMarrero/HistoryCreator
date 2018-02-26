package tramas.App;

/**
 * Implementaci�n del controlador principal de todo el proyecto
 * �ste tendr� las siguientes especificaciones:
 * 
 * 1.- Un men� compartido con las diferentes vistas del programa, cuyos controladores 
 * ser�n inicializados aqu�.
 * 2.- Funciones que cargar�n en el centro del correspondiente BorderPane las vistas
 * de los controladores a los que hacen referencia.
 * 
 * @author Eduardo Marrero
 */

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
	
	/**
	 * El constructor del MainController inicializar� todo controlador que
	 * dependa directamente de la Campa�a, cargar� una property observable de tipo
	 * Campania y lanzar� la vista de dicha campa�a.
	 * @throws IOException
	 */
	
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
		
	}
	
	/**
	 * De la lista de aventuras que recoger� el controlador CampaniaController, 
	 * este m�todo establecer� para aventurasController cu�l ha sido elegida.
	 * Adem�s establecer� la vista en el centro del BorderPane.
	 * @param aventura
	 */
	public void irAAventura(Aventura aventura) {
		aventurasController.setAventura(aventura);
		view.setCenter(aventurasController.getView());
	}
	
	/**
	 * Este m�todo colocar� en el top del BorderPane el MenuBar de la clase
	 * menuController.
	 * Adem�s establecer� en el centro del BorderPane la vista del controlador
	 * CampaniaController
	 */
	
	public void irACampania() {
		view.setTop(menuController.getMenuPrincipal());
		view.setCenter(campaniaController.getView());
	}
	
	/**
	 * 
	 * @return Retorna la vista del BorderPane de este controlador (MainController)
	 */
	
	public BorderPane getView() {
		return view;
	}

}
