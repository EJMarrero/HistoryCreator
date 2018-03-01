package tramas.menu;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;


import org.controlsfx.control.Notifications;
import org.w3c.dom.Document;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import tramas.App.GestorApp;
import tramas.App.MainController;
import tramas.App.SplashController;
import tramas.model.Campania;

public class MenuController implements Initializable {

	private MainController mainController;
	private Image check = new Image("/tramas/resources/check.png");
	private ImageView checkIV = new ImageView(check);

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
		checkIV.setFitHeight(60);
		checkIV.setFitWidth(60);
	}

	@FXML
	void onAbrirMenuItemAction(ActionEvent event) {
		Alert confirmacion = new Alert(AlertType.WARNING);
		confirmacion.initOwner(GestorApp.getPrimaryStage());
		confirmacion.setTitle("Nuevo Campaña");
		confirmacion.setHeaderText("¡Cuidado! Se dispone a abrir una campaña nueva.\n Se perderán los cambios realizados.");
		confirmacion.setContentText("¿Desea guardar la campaña y continuar?");
		confirmacion.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
			if (confirmacion.showAndWait().get().equals(ButtonType.YES)) {
				onGuardarMenuItemAction(event);
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
						Notifications notificationSaved = Notifications.create()
								.title("Ha abierto una campaña guardada")
								.text(fichero.getPath()+"cargado con éxito")
								.graphic(checkIV)
								.hideAfter(Duration.seconds(3))
								.position(Pos.BASELINE_RIGHT);
						notificationSaved.darkStyle();
						notificationSaved.show();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					// muestra un diálogo con el error
					Alert error = new Alert(AlertType.ERROR);
					error.initOwner(SplashController.getPrimaryStage());
					error.setTitle("Abrir campaña");
					error.setHeaderText("Error al abrir la campaña.");
					error.setContentText(e1.getMessage());
					error.showAndWait();
				}
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
				// se guarda la campaña en el fichero indicado
				campania.get().save(fichero);
				Notifications notificationSaved = Notifications.create()
						.title("¡Campaña guardada!")
						.text("Guardado en: "+fichero.getPath())
						.graphic(checkIV)
						.hideAfter(Duration.seconds(3))
						.position(Pos.BASELINE_RIGHT);
				notificationSaved.darkStyle();
				notificationSaved.show();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			// muestra un diálogo con el error
			Notifications notificationSaved = Notifications.create()
					.title("¡Error al guardar!")
					.text("No se ha podido guardar la campaña")
					.graphic(null)
					.hideAfter(Duration.seconds(3))
					.position(Pos.BASELINE_RIGHT);
			notificationSaved.darkStyle();
			notificationSaved.showError();
		}
		
	}

	@FXML
	void onNuevoMenuItemAction(ActionEvent event) {
		Alert confirmacion = new Alert(AlertType.WARNING);
		confirmacion.initOwner(SplashController.getPrimaryStage());
		confirmacion.setTitle("Nuevo Campaña");
		confirmacion.setHeaderText("Se dispone a crear una nueva campaña.\nSi tiene información sin guardar se perderá para siempre.");
		confirmacion.setContentText("¿Seguro que desea continuar?");
		confirmacion.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
		if (confirmacion.showAndWait().get().equals(ButtonType.YES)) {
			campania.set(new Campania());
			Notifications notificationSaved = Notifications.create()
					.title("¡Nueva Campaña!")
					.text("Ha iniciado una nueva campaña")
					.graphic(checkIV)
					.hideAfter(Duration.seconds(3))
					.position(Pos.BASELINE_RIGHT);
			notificationSaved.darkStyle();
			notificationSaved.show();
		}
	}

	@FXML
	void onSalirMenuItemAction(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void onImprimirMenuAction(ActionEvent event) {
		String nombreFichero = "";
		Alert confirmacion = new Alert(AlertType.WARNING);
		confirmacion.initOwner(SplashController.getPrimaryStage());
		confirmacion.setTitle("Nuevo Campaña");
		confirmacion.setHeaderText("Se dispone a exportar a pdf su campaña.\nAntes de poder hacerlo, es necesario guardar los datos actuales");
		confirmacion.setContentText("¿Desea guardar la campaña y continuar?");
		confirmacion.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
		if (confirmacion.showAndWait().get().equals(ButtonType.YES)) {
			try {
				// abre el diálogo para guardar un fichero
				FileChooser guardarDialog = new FileChooser();
				guardarDialog.setInitialDirectory(new File("."));
				guardarDialog.getExtensionFilters().add(new ExtensionFilter("Archivo XML (*.xml)", "*.xml"));
				File fichero = guardarDialog.showSaveDialog(SplashController.getPrimaryStage());
				// comprueba si se seleccionó un fichero en el diálogo (File) o se canceló
				// (null)
				if (fichero != null) {
					// se guarda la campaña en el fichero indicado
					campania.get().save(fichero);
					nombreFichero = fichero.getAbsolutePath();
					generarPDF(nombreFichero);
				}
				
				
			} catch (Exception e1) {
				e1.printStackTrace();
				// muestra un diálogo con el error
				Alert error = new Alert(AlertType.ERROR);
				error.initOwner(GestorApp.getPrimaryStage());
				error.setTitle("Guardar aventura");
				error.setHeaderText("Error al guardar la aventura.");
				error.setContentText(e1.getMessage());
				error.showAndWait();
			}
			
			
		}
		
	}
	
	private void generarPDF(String file) throws JRException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		Document document = JRXmlUtils.parse(JRLoader.getLocationInputStream(file));
		params.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);
		params.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, "yyyy-MM-dd");
		params.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, "#,##0.##");
		params.put(JRXPathQueryExecuterFactory.XML_LOCALE, Locale.ENGLISH);
		params.put(JRParameter.REPORT_LOCALE, Locale.US);
		
		JasperFillManager.fillReportToFile("reports/Prueba.jasper", params);
		
		JasperExportManager.exportReportToPdfFile("reports/Prueba.jrprint", "TuCampaña.pdf");
		Desktop.getDesktop().open(new File("TuCampaña.pdf"));
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
