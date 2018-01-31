package tramas.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Campania {

	private ListProperty<Aventura> aventuras;
	private ListProperty<Nota> notas;
	private ListProperty<Personaje> personajes;
	private ObjectProperty<Mapa> mapaCampania;
	
	public Campania () {
		aventuras = new SimpleListProperty<>(this, "aventuras", FXCollections.observableArrayList());
		notas = new SimpleListProperty<>(this, "notas", FXCollections.observableArrayList());
		personajes = new SimpleListProperty<>(this, "personajes", FXCollections.observableArrayList());
		mapaCampania = new SimpleObjectProperty<>(this, "mapaCampania");
	}

	
	
	public final ListProperty<Aventura> aventurasProperty() {
		return this.aventuras;
	}
	

	public final ObservableList<Aventura> getAventuras() {
		return this.aventurasProperty().get();
	}
	

	public final void setAventuras(final ObservableList<Aventura> aventuras) {
		this.aventurasProperty().set(aventuras);
	}

	public ListProperty<Nota> notasProperty() {
		return this.notas;
	}
	

	public ObservableList<Nota> getNotas() {
		return this.notasProperty().get();
	}
	

	public void setNotas(final ObservableList<Nota> notas) {
		this.notasProperty().set(notas);
	}



	public ListProperty<Personaje> personajesProperty() {
		return this.personajes;
	}
	



	public ObservableList<Personaje> getPersonajes() {
		return this.personajesProperty().get();
	}
	



	public void setPersonajes(final ObservableList<Personaje> personajes) {
		this.personajesProperty().set(personajes);
	}



	public ObjectProperty<Mapa> mapaCampaniaProperty() {
		return this.mapaCampania;
	}
	



	public Mapa getMapaCampania() {
		return this.mapaCampaniaProperty().get();
	}
	



	public void setMapaCampania(final Mapa mapaCampania) {
		this.mapaCampaniaProperty().set(mapaCampania);
	}
	
	
	
	
	
	
	
}
