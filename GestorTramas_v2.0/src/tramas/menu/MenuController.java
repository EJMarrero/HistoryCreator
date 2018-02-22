package tramas.menu;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import tramas.App.GestorApp;
import tramas.App.MainController;
import tramas.campania.CampaniaController;
import tramas.model.Campania;

public class MenuController implements Initializable {

	private MainController mainController;

	// modelo
	private ObjectProperty<Campania> campania = new SimpleObjectProperty<>(this, "campania", new Campania());

	@FXML
	private MenuBar menuPrincipal;

	public MenuController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@FXML
	void onAbrirMenuItemAction(ActionEvent event) {
		try {
			// abre el diálogo para abrir un fichero
			FileChooser abrirDialog = new FileChooser();
			abrirDialog.setInitialDirectory(new File("."));
			abrirDialog.getExtensionFilters().add(new ExtensionFilter("Archivo XML (*.xml)", "*.xml"));
			File fichero = abrirDialog.showOpenDialog(GestorApp.getPrimaryStage());
			// comprueba si se seleccionó un fichero en el diálogo (File) o se canceló
			// (null)
			if (fichero != null) {
				campania.set(Campania.load(fichero));
				mainController.irACampania();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			// muestra un diálogo con el error
			Alert error = new Alert(AlertType.ERROR);
			error.initOwner(GestorApp.getPrimaryStage());
			error.setTitle("Abrir historial");
			error.setHeaderText("Error al abrir un historial.");
			error.setContentText(e1.getMessage());
			error.showAndWait();
		}
	}

	@FXML
	void onGuardarMenuItemAction(ActionEvent event) {
		try {
			// abre el diálogo para guardar un fichero
			FileChooser guardarDialog = new FileChooser();
			guardarDialog.setInitialDirectory(new File("."));
			guardarDialog.getExtensionFilters().add(new ExtensionFilter("Archivo XML (*.xml)", "*.xml"));
			File fichero = guardarDialog.showSaveDialog(GestorApp.getPrimaryStage());
			// comprueba si se seleccionó un fichero en el diálogo (File) o se canceló
			// (null)
			if (fichero != null) {
				// se guarda el historial en el fichero indicado
				campania.get().save(fichero);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			// muestra un diálogo con el error
			Alert error = new Alert(AlertType.ERROR);
			error.initOwner(GestorApp.getPrimaryStage());
			error.setTitle("Guardar historial");
			error.setHeaderText("Error al guardar un historial.");
			error.setContentText(e1.getMessage());
			error.showAndWait();
		}
	}

	@FXML
	void onNuevoMenuItemAction(ActionEvent event) {

	}

	@FXML
	void onSalirMenuItemAction(ActionEvent event) {

	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public MenuBar getMenuPrincipal() {
		return menuPrincipal;
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
