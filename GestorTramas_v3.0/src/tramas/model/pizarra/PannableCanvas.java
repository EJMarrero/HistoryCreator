package tramas.model.pizarra;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class PannableCanvas extends StackPane {

	DoubleProperty myScale = new SimpleDoubleProperty(1.0);
	Canvas grid;

	public PannableCanvas() {
		setPrefSize(600, 600);
		setStyle("-fx-background-color: lightgrey; -fx-border-color: blue;");

		// bindea la escala a las coordenadas
		scaleXProperty().bind(myScale);
		scaleYProperty().bind(myScale);
	}

	/**
	 * A�ade una rejilla al fondo del panel
	 */
	public void addGrid() {

		double w = getBoundsInLocal().getWidth();
		double h = getBoundsInLocal().getHeight();

		// A�ade el grid.
		grid = new Canvas(w, h);

		// Con este m�todo evitamos que al pulsar sobre el grid se generen eventos del mouse
		grid.setMouseTransparent(true);

		GraphicsContext gc = grid.getGraphicsContext2D();

		gc.setStroke(Color.GRAY);
		gc.setLineWidth(1);

		// Dibuja la rejilla
		double offset = 50;
		for (double i = offset; i < w; i += offset) {
			gc.strokeLine(i, 0, i, h);
			gc.strokeLine(0, i, w, i);
		}

		getChildren().add(grid);

		grid.toBack();
	}

	public double getScale() {
		return myScale.get();
	}

	public void setScale(double scale) {
		myScale.set(scale);
	}

	public void setPivot(double x, double y) {
		setTranslateX(getTranslateX() - x);
		setTranslateY(getTranslateY() - y);
	}

	public Canvas getGrid() {
		return grid;
	}

	public void setGrid(Canvas grid) {
		this.grid = grid;
	}
	
	
}
