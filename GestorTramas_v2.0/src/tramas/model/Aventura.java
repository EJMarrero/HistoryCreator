package tramas.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Aventura {

	private StringProperty nombre;
	private ObjectProperty<javafx.scene.image.Image> portrait;
	private ListProperty<Mapa> tableros;
	private ListProperty<String> eventos; 
	private ListProperty<Encuentro> encuentros;
	private ListProperty<Nota> notas;
	private ListProperty<NPC> pnjs;
	private ListProperty<Tesoros> tesoros;
	
	public Aventura() {
		nombre = new SimpleStringProperty(this, "nombre");
		portrait = new SimpleObjectProperty<>(this, "portrait");
		tableros = new SimpleListProperty<>(this, "mapas", FXCollections.observableArrayList());
		eventos = new SimpleListProperty<>(this, "eventos", FXCollections.observableArrayList());
		encuentros = new SimpleListProperty<>(this, "encuentros", FXCollections.observableArrayList());
		notas = new SimpleListProperty<>(this, "notas", FXCollections.observableArrayList());
		pnjs = new SimpleListProperty<>(this, "pnjs", FXCollections.observableArrayList());
		tesoros = new SimpleListProperty<>(this, "tesoros", FXCollections.observableArrayList());
	}

	
	public final ListProperty<Mapa> mapasProperty() {
		return this.tableros;
	}
	

	public final ObservableList<Mapa> getMapas() {
		return this.mapasProperty().get();
	}
	

	public final void setMapas(final ObservableList<Mapa> mapas) {
		this.mapasProperty().set(mapas);
	}
	

	public final ListProperty<Encuentro> encuentrosProperty() {
		return this.encuentros;
	}
	

	public final ObservableList<Encuentro> getEncuentros() {
		return this.encuentrosProperty().get();
	}
	

	public final void setEncuentros(final ObservableList<Encuentro> encuentros) {
		this.encuentrosProperty().set(encuentros);
	}
	

	public final ListProperty<Nota> notasProperty() {
		return this.notas;
	}
	

	public final ObservableList<Nota> getNotas() {
		return this.notasProperty().get();
	}
	

	public final void setNotas(final ObservableList<Nota> notas) {
		this.notasProperty().set(notas);
	}
	

	public final ListProperty<NPC> pnjsProperty() {
		return this.pnjs;
	}
	

	public final ObservableList<NPC> getPnjs() {
		return this.pnjsProperty().get();
	}
	

	public final void setPnjs(final ObservableList<NPC> pnjs) {
		this.pnjsProperty().set(pnjs);
	}
	

	public final ListProperty<Tesoros> tesorosProperty() {
		return this.tesoros;
	}
	

	public final ObservableList<Tesoros> getTesoros() {
		return this.tesorosProperty().get();
	}
	

	public final void setTesoros(final ObservableList<Tesoros> tesoros) {
		this.tesorosProperty().set(tesoros);
	}

	public StringProperty nombreProperty() {
		return this.nombre;
	}
	

	public String getNombre() {
		return this.nombreProperty().get();
	}
	

	public void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public ObjectProperty<javafx.scene.image.Image> portraitProperty() {
		return this.portrait;
	}
	

	public Image getPortrait() {
		return this.portraitProperty().get();
	}
	

	public void setPortrait(final Image portrait) {
		this.portraitProperty().set(portrait);
	}


	public ListProperty<Mapa> tablerosProperty() {
		return this.tableros;
	}
	


	public ObservableList<Mapa> getTableros() {
		return this.tablerosProperty().get();
	}
	


	public void setTableros(final ObservableList<Mapa> tableros) {
		this.tablerosProperty().set(tableros);
	}
	


	public ListProperty<String> eventosProperty() {
		return this.eventos;
	}
	


	public ObservableList<String> getEventos() {
		return this.eventosProperty().get();
	}
	


	public void setEventos(final ObservableList<String> eventos) {
		this.eventosProperty().set(eventos);
	}
	
	
	@Override
	public String toString() {
		return getNombre();
	}
	
	
	
	
	
}
