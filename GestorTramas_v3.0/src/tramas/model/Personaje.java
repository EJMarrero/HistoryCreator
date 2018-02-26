package tramas.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personaje extends NPC{

	private StringProperty jugador;
	
	public Personaje () {
		jugador = new SimpleStringProperty(this, "jugador");
	}

	public final StringProperty jugadorProperty() {
		return this.jugador;
	}
	

	public final String getJugador() {
		return this.jugadorProperty().get();
	}
	

	public final void setJugador(final String jugador) {
		this.jugadorProperty().set(jugador);
	}
	

	
	
}
