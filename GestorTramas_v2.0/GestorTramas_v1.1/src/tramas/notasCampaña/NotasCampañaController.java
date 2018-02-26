package tramas.notasCampaña;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tramas.editorImagen.EditorImagen;
import tramas.model.Nota;

public class NotasCampañaController implements Initializable {
	
	//Subcontroladores
	
	//Modelo
	private Nota notaNueva;
	private Nota notaDevuelta = new Nota();
	
	
	
	//Vista
	@FXML
	private BorderPane view;
	@FXML
	private HTMLEditor cajaEditor;
	private Rectangle rectangle;
	private File aux;
	
	@FXML
	private TextField tituloText;
	@FXML
	private BorderPane imagenPane;

    @FXML
    private Button guardarNotaButton, cargarImagenButton, agregarNotaButton, limpiarButton;

	private Stage stage;
	
	
	public NotasCampañaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NotasCampañaView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tituloText.textProperty().bindBidirectional(notaDevuelta.tituloProperty());
		
		
		
		//AÑADIR BOTON A BARRA DE TOOLBAR DE HTML EDITOR
//		Node node = cajaEditor.lookup(".top-toolbar");
//		
//		if (node instanceof ToolBar) {
//			ToolBar bar = (ToolBar) node;
//			ImageView graphic = new ImageView(new Image("/tramas/resources/imagen.png", 15, 40, true,true));
//			Button imagenButton = new Button("", graphic);
//			bar.getItems().add(imagenButton);
//			imagenButton.setOnAction(new EventHandler<ActionEvent>() {
//				@Override
//				public void handle(ActionEvent event) {
//					try {
//						onCargarImagenButtonAction(event);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			});
//		}
	}
	
	public Nota show(Stage parentStage) {
		stage = new Stage();
		if (parentStage !=null)
		stage.initOwner(parentStage);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Nueva nota de Campaña");
		stage.setResizable(true);
		stage.setScene(new Scene(view, 800, 600));
		cajaEditor.setHtmlText(notaDevuelta.getTexto());
		stage.showAndWait();
		return notaDevuelta;
	}
	
	@FXML
    void onCargarImagenButtonAction(ActionEvent event) throws IOException {
		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Cargar imagen");
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos de imagen(*.jpg o *.png)", "*.jpg", "*.png");
		fChooser.getExtensionFilters().add(extFilterJPG);
		File imageFile = fChooser.showOpenDialog(stage);
		
		rectangle = EditorImagen.redimensionarArchivo(imageFile);
		imagenPane.setCenter(rectangle);
		aux=imageFile;
    }
	
		
	@FXML
    void onAgregarNotaButtonAction(ActionEvent event) throws FileNotFoundException {
		cajaEditor.setHtmlText(cajaEditor.getHtmlText()+"<img src=\""+ aux.toURI()+  " \"width=\""+rectangle.getWidth()+"\"+ height=\""+rectangle.getHeight()+"\" >");
    }
	
    @FXML
    void onLimpiarButtonAction(ActionEvent event) {

    }


	@FXML
	void onGuardarNotaButtonAction(ActionEvent event) {
		notaNueva = new Nota();
		notaNueva.setTexto(cajaEditor.getHtmlText());
		notaNueva.setTitulo(tituloText.getText());
		Nota.copiar(notaNueva, notaDevuelta);
		stage.close();
	}

	public BorderPane getView() {
		return view;
	}
	
	public Nota getNotaDevuelta() {
		return notaDevuelta;
	}

	public void setNotaDevuelta(Nota notaDevuelta) {
		this.notaDevuelta.setTexto(notaDevuelta.getTexto());
		this.notaDevuelta.setTitulo(notaDevuelta.getTitulo());
	}
	
	

}
