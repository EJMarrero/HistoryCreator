package tramas.aventuras;


import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import tramas.model.Mapa;

public class TableroListCell extends ListCell<Mapa> {
	
	private ImageView avatarMapa;
	
	public void updateItem(Mapa mapaObtenido, boolean empty) {
		avatarMapa = new ImageView();
		avatarMapa.setFitHeight(40);
		avatarMapa.setFitWidth(40);
		super.updateItem(mapaObtenido, empty);
		
		if (empty) {
			setText(null);
			setGraphic(null);
		}else {
			avatarMapa.setImage(mapaObtenido.getImagen());
			setText(mapaObtenido.getNombre());
			setGraphic(avatarMapa);
			
		}
	}
	
}
