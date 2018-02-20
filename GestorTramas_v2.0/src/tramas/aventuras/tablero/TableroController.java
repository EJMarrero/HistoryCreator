package tramas.aventuras.tablero;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import tramas.App.GestorApp;
import tramas.aventuras.AventurasController;
import tramas.campania.CampaniaController;
import tramas.editorImagen.EditorImagen;
import tramas.menu.MenuController;
import tramas.model.Aventura;
import tramas.model.Campania;
import tramas.model.pizarra.NodeGestures;
import tramas.model.pizarra.PannableCanvas;
import tramas.model.pizarra.SceneGestures;
import tramas.notasCampaña.DragResizeMod;

public class TableroController implements Initializable {

	// Referencia al controlador padre
	private AventurasController aventurasController;
	

	//Vista
	private Stage stage;
	private Rectangle avatar= new Rectangle();
	@FXML
	private BorderPane view;
	@FXML
	private ImageView contenedorMapa = new ImageView();
	@FXML
	private ColorPicker colorPicker;
	@FXML
	private TextField brushSize;
	@FXML
	private CheckBox eraser;
	@FXML
	private CheckBox dibujarBox;
	@FXML
	private Canvas canvas;
    @FXML
    private VBox cajaSuperior;
    @FXML
    private StackPane contenedorPizarra;

	public ImageView getContenedorMapa() {
		return contenedorMapa;
	}

	public void setContenedorMapa(ImageView contenedorMapa) {
		this.contenedorMapa = contenedorMapa;
	}



	private Scene scene;

	// Model
	private ObjectProperty<Aventura> aventura = new SimpleObjectProperty<>(this, "aventura", new Aventura());
	Group group = new Group();
	public PannableCanvas panelZoom = new PannableCanvas();
	public NodeGestures nodeGestures = new NodeGestures(panelZoom);
//	public SceneGestures scenegestures = new SceneGestures(canvas);
	public ArrayList<ImagePattern> listaAvatares = new ArrayList<>();
	public ObjectProperty<Rectangle> nuevoAvatar = new SimpleObjectProperty<>(this, "nuevoAvatar");
	public GraphicsContext g;

	


	

	public TableroController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TableroView1.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		scene = new Scene(view);
	

		contenedorMapa.toBack();
		panelZoom.toBack();
        g = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size ;
            double y = e.getY() - size ;

            if (eraser.isSelected()) {
                g.clearRect(x, y, size, size);
            } else {
                g.setFill(colorPicker.getValue());
                g.fillRect(x, y, size, size);
            }
        });
          
        
        
        panelZoom.getChildren().addAll(contenedorMapa, canvas);
        contenedorPizarra.getChildren().addAll(panelZoom);
		
        SceneGestures sceneGestures = new SceneGestures(panelZoom);
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
		scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
		scene.addEventFilter(ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());
//		view.setTop(cajaSuperior);
//        view.setCenter(contenedorPizarra);
        




        
    }
		
	

	public void setMainController(AventurasController aventurasController) {
		this.aventurasController = aventurasController;

	}

	public BorderPane getView() {
		return view;
	}

	public void show(Stage parentStage) {
		// scene.getStylesheets().add(getClass().getResource("styleAventura.css").toExternalForm());
//		parentStage.setScene(scene);
//		parentStage.setFullScreen(true);
//		parentStage.setFullScreenExitHint("");
//		parentStage.setResizable(false);
//		parentStage.show();

		stage = new Stage();
		stage.setScene(scene);
		stage.initOwner(parentStage);
		stage.setMaximized(true);
		stage.setResizable(false);
		stage.showAndWait();
		
	}
	
	@FXML
	void onSave(ActionEvent event) {
		 try {
	            Image snapshot = contenedorPizarra.snapshot(null, null);
	            
	            FileChooser guardarDialog = new FileChooser();
				guardarDialog.setInitialDirectory(new File("."));
				guardarDialog.getExtensionFilters().add(new ExtensionFilter("Imagen PNG (*.png)", "*.png"));
				File fichero = guardarDialog.showSaveDialog(GestorApp.getPrimaryStage());
				
	            if (fichero != null)
	            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", fichero);
	        } catch (Exception e) {
	        	e.printStackTrace();
				// muestra un diálogo con el error
				Alert error = new Alert(AlertType.ERROR);
				error.initOwner(GestorApp.getPrimaryStage());
				error.setTitle("Guardar captura");
				error.setHeaderText("Error al guardar la captura.");
				error.setContentText(e.getMessage());
				error.showAndWait();
	        }
	}
    @FXML
    void onLimpiarButtonAction(ActionEvent event) {
    	g.clearRect(0, 0, scene.getWidth(), scene.getHeight());
    }
    
	@FXML
	void onExit(ActionEvent event) {
		g.clearRect(0, 0, scene.getWidth(), scene.getHeight());
		stage.close();
	}
    @FXML
    void onBackButtonAction(ActionEvent event) {
    	g.clearRect(0, 0, scene.getWidth(), scene.getHeight());
		stage.close();
    }

    @FXML
    void onCargarImagenButtonAction(ActionEvent event) {
    	
    	Stage stage = new Stage();
    	
    	boolean activo = false;
    	FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Cargar imagen");
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos de imagen(*.jpg o *.png)", "*.jpg", "*.png");
		fChooser.getExtensionFilters().add(extFilterJPG);
		File imageFile = fChooser.showOpenDialog(stage);
		
		if(imageFile != null) {
		
		Rectangle nuevo = new Rectangle();
		nuevo = EditorImagen.redimensionarArchivo(imageFile);

		listaAvatares.add((ImagePattern) nuevo.getFill());
		

		
		
		agregarAvatares();
		}
    }
    
    private void agregarAvatares() {
    	Rectangle rectangle = new Rectangle(100,100);
    	for (int i = 0; i < listaAvatares.size(); i++) {
    		
    		rectangle.setFill(listaAvatares.get(i));
//    		rectangle.toBack();
		}
    	avatar = rectangle;
    	panelZoom.getChildren().add(rectangle);
    	System.out.println(rectangle.toString());

        
// 		avatar.setOnMouseEntered(new EventHandler<MouseEvent>() {
//
// 			@Override
// 			public void handle(MouseEvent event) {
// 				avatar.setStroke(Color.BLACK);
//
// 			}
//
// 		});
// 		
// 		avatar.setOnMouseExited(new EventHandler<MouseEvent>() {
//
// 			@Override
// 			public void handle(MouseEvent event) {
// 				avatar.setStroke(Color.TRANSPARENT);
//
// 			}
//
// 		});
 		
 		avatar.setOnMouseClicked(new EventHandler<MouseEvent> () {
 			
 			@Override
 			public void handle(MouseEvent event) {
 				avatar.addEventFilter(MouseEvent.MOUSE_PRESSED, nodeGestures.getOnMousePressedEventHandler());
 				avatar.addEventFilter(MouseEvent.MOUSE_DRAGGED, nodeGestures.getOnMouseDraggedEventHandler());
 			}
 			
 		});
    	

		
		
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

	public VBox getCajaSuperior() {
		return cajaSuperior;
	}

	public void setCajaSuperior(VBox cajaSuperior) {
		this.cajaSuperior = cajaSuperior;
	}

	public PannableCanvas getPanelZoom() {
		return panelZoom;
	}

	public void setPanelZoom(PannableCanvas panelZoom) {
		this.panelZoom = panelZoom;
	}


	
    
    
	
}
