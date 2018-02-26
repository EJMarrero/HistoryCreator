package tramas.model.pizarra;

import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import tramas.notasCampaña.DragResizeMod;

public class NodeGestures {
	private DragContext nodeDragContext = new DragContext();
	Rectangle rec = new Rectangle(100,100);
	PannableCanvas canvas;

	public NodeGestures(PannableCanvas canvas) {
		this.canvas = canvas;

	}

	public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
		return onMousePressedEventHandler;
	}

	public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
		return onMouseDraggedEventHandler;
	}


	

	
	private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {

			// left mouse button => dragging
			if (!event.isPrimaryButtonDown())
				return;
			
			Node node = (Node) event.getSource();
			rec = (Rectangle) node;
			Rotate rotate = new Rotate();
			double anchoEspejo = 0;

				rec.setOnMouseEntered(new EventHandler<MouseEvent>() {
	
					@Override
					public void handle(MouseEvent event) {
	
//						rec = (Rectangle) node;
						rec.setStroke(Color.BLACK);
						event.consume();
					}
	
				});
	
				rec.setOnMouseExited(new EventHandler<MouseEvent>() {
	
					@Override
					public void handle(MouseEvent event) {
	
//						rec = (Rectangle) node;
						rec.setStroke(Color.TRANSPARENT);
						event.consume();
					}
	
				});

			if (!event.isControlDown()) {

//				rec = (Rectangle) node;
				DragResizeMod.makeResizable(rec);

				// } else if (event.isShiftDown()) {
				// rec.getTransforms().add(rotate);
				// rotate.setPivotX((rec.getX()) + rec.getWidth() / 2);
				// rotate.setPivotY((rec.getY()) + rec.getWidth() / 2);
				// rotate.setAngle(rotate.getAngle() -22.5);
				//
				//
				// } else if (event.isAltDown()) {
				// rec.getTransforms().add(rotate);
				// rotate.setPivotX((rec.getX()) + rec.getWidth() / 2);
				// rotate.setPivotY((rec.getY()) + rec.getWidth() / 2);
				// rotate.setAngle(rotate.getAngle() +22.5);

			} else {

				nodeDragContext.mouseAnchorX = event.getSceneX();
				nodeDragContext.mouseAnchorY = event.getSceneY();

//				 node = (Node) event.getSource();

				nodeDragContext.translateAnchorX = node.getTranslateX();
				nodeDragContext.translateAnchorY = node.getTranslateY();

				System.out.println("Movimiento real X" + (nodeDragContext.translateAnchorX = node.getTranslateX()));
				System.out.println("Movimiento real Y" + (nodeDragContext.translateAnchorY = node.getTranslateY()));

				System.out.println("MouseAnchorX" + nodeDragContext.mouseAnchorX);
				System.out.println("MouseAnchorY" + nodeDragContext.mouseAnchorY);
			}
		}

	};

	private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			
			// left mouse button => dragging
			if (!event.isPrimaryButtonDown())
				return;
			
			if(event.isControlDown()) {
			double scale = canvas.getScale();

			Node node = (Node) event.getSource();

			node.setTranslateX(
					nodeDragContext.translateAnchorX + ((event.getSceneX() - nodeDragContext.mouseAnchorX) / scale));
			node.setTranslateY(
					nodeDragContext.translateAnchorY + ((event.getSceneY() - nodeDragContext.mouseAnchorY) / scale));

			event.consume();

			} else {
				return;
			}
		}
	};
}

