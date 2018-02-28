package tramas.canvas.tablero;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
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
import javafx.scene.input.MouseButton;
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
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;
import tramas.App.GestorApp;
import tramas.App.SplashController;
import tramas.aventuras.AventurasController;
import tramas.calculadora.controller.CalculadoraController;
import tramas.campania.CampaniaController;
import tramas.editorImagen.EditorImagen;
import tramas.menu.MenuController;
import tramas.model.Aventura;
import tramas.model.Campania;
import tramas.model.pizarra.NodeGestures;
import tramas.model.pizarra.PannableCanvas;
import tramas.model.pizarra.SceneGestures;
import tramas.notasCampaña.DragResizeMod;

@SuppressWarnings("unused")
public class TableroController implements Initializable {

	// Referencia al controlador padre
	@SuppressWarnings("unused")
	private AventurasController aventurasController;

	// Vista
	private Stage stage;
	private Rectangle avatar = new Rectangle();
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
	public ArrayList<ImagePattern> listaAvatares = new ArrayList<>();
	public ObjectProperty<Rectangle> nuevoAvatar = new SimpleObjectProperty<>(this, "nuevoAvatar");
	public GraphicsContext g;

	// Cursores
	public Image cursorPencil = new Image("/tramas/resources/lapizCanvas.png");
	public ImageCursor pencil = new ImageCursor(cursorPencil);
	public ImageCursor cursor = new ImageCursor(GestorApp.getCursor());
	public Image cursorRubber = new Image ("/tramas/resources/gomaCanvas.png");
	public ImageCursor rubber = new ImageCursor(cursorRubber);

	public TableroController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TableroView1.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/tramas/resources/style.css").toExternalForm());
				
		// Se asigna Cursor normal cuando entra en zona de Herramientas
		cajaSuperior.setOnMouseEntered(event -> scene.setCursor(cursor));
		// La primera vez se asigna el lapiz a la zona de edición, luego dependerá del Listener del CheckBox
		contenedorPizarra.setOnMouseEntered(event -> scene.setCursor(pencil));
		
		// Se crea un listener, éste cambiará en función de su valor, si es TRUE o FALSE
		eraser.selectedProperty().addListener((ChangeListener<Boolean>) (ov, old_val, new_val) -> {
			if (new_val == true)
				// Se asigna Cursor de goma cuando está seleccionada la goma
				contenedorPizarra.setOnMouseEntered(event1 -> scene.setCursor(rubber));
			else
				// Se asigna Cursor de lapiz salvo cuando está seleccionada la goma
				contenedorPizarra.setOnMouseEntered(event2 -> scene.setCursor(pencil));
		});
		

		contenedorMapa.toBack();
		panelZoom.toBack();
		g = canvas.getGraphicsContext2D();

		canvas.setOnMouseDragged(e -> {
			double size = Double.parseDouble(brushSize.getText());
			double x = e.getX() - size;
			double y = e.getY() - size;

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
		// view.setTop(cajaSuperior);
		// view.setCenter(contenedorPizarra);

	}

	public void setMainController(AventurasController aventurasController) {
		this.aventurasController = aventurasController;

	}

	public BorderPane getView() {
		return view;
	}

	public void show(Stage parentStage) {
		stage = new Stage();
		stage.setScene(scene);
		stage.initOwner(parentStage);
		stage.getIcons().add(SplashController.getPrimaryStage().getIcons().get(0));
		stage.initStyle(StageStyle.UNDECORATED);
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
		double x = scene.getX();
		double y = scene.getY();
		g.clearRect(0, 0, contenedorPizarra.getWidth(), contenedorPizarra.getHeight());
	}

	@FXML
	void onExit(ActionEvent event) {
		g.clearRect(0, 0, contenedorPizarra.getWidth(), contenedorPizarra.getHeight());
		stage.close();
	}

	@FXML
	void onBackButtonAction(ActionEvent event) {
		g.clearRect(0, 0, contenedorPizarra.getWidth(), contenedorPizarra.getHeight());
		stage.close();
	}

	@FXML
	void onRollMenuItemAction(ActionEvent event) throws IOException {
		CalculadoraController controller = new CalculadoraController();
		controller.show(SplashController.getPrimaryStage());
	}

	@FXML
	void onCargarImagenButtonAction(ActionEvent event) {
		Stage stage = new Stage();

		boolean activo = false;
		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Cargar imagen");
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos de imagen(*.jpg o *.png)",
				"*.jpg", "*.png");
		fChooser.getExtensionFilters().add(extFilterJPG);
		File imageFile = fChooser.showOpenDialog(stage);

		if (imageFile != null) {

			Rectangle nuevo = new Rectangle();
			nuevo = EditorImagen.redimensionarArchivo(imageFile);

			listaAvatares.add((ImagePattern) nuevo.getFill());

			agregarAvatares();
		}
	}

	private void agregarAvatares() {
		Rectangle rectangle = new Rectangle(100, 100);
		for (int i = 0; i < listaAvatares.size(); i++) {

			rectangle.setFill(listaAvatares.get(i));
			// rectangle.toBack();
		}
		avatar = rectangle;
		panelZoom.getChildren().add(rectangle);

		avatar.setOnMouseClicked(new EventHandler<MouseEvent>() {

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
