package tramas.campania;


import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import tramas.model.Aventura;

public class AventuraListCell extends ListCell<Aventura> {
	
	private ImageView avatarAventura;
	
	public void updateItem(Aventura aventuraObtenida, boolean empty) {
		avatarAventura = new ImageView();
		avatarAventura.setFitHeight(40);
		avatarAventura.setFitWidth(40);
		super.updateItem(aventuraObtenida, empty);
		
		if (empty) {
			setText(null);
			setGraphic(null);
		}else {
			avatarAventura.setImage(aventuraObtenida.getPortrait());
			setText(aventuraObtenida.getNombre());
			setGraphic(avatarAventura);
			
		}
	}
	
}
