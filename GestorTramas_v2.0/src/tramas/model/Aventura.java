package tramas.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import tramas.model.adapter.ImageAdapter;

@XmlType
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

	public final StringProperty nombreProperty() {
		return this.nombre;
	}

	@XmlAttribute
	public final String getNombre() {
		return this.nombreProperty().get();
	}

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final ObjectProperty<javafx.scene.image.Image> portraitProperty() {
		return this.portrait;
	}
	
	@XmlElement
	@XmlJavaTypeAdapter(ImageAdapter.class)
	public final Image getPortrait() {
		return this.portraitProperty().get();
	}

	public final void setPortrait(final Image portrait) {
		this.portraitProperty().set(portrait);
	}

	public final ListProperty<Mapa> tablerosProperty() {
		return this.tableros;
	}
	
	@XmlElement
	public final ObservableList<Mapa> getTableros() {
		return this.tablerosProperty().get();
	}

	public final void setTableros(final ObservableList<Mapa> tableros) {
		this.tablerosProperty().set(tableros);
	}

	public final ListProperty<String> eventosProperty() {
		return this.eventos;
	}

	public final ObservableList<String> getEventos() {
		return this.eventosProperty().get();
	}

	public final void setEventos(final ObservableList<String> eventos) {
		this.eventosProperty().set(eventos);
	}

	public final ListProperty<Encuentro> encuentrosProperty() {
		return this.encuentros;
	}
	
	@XmlElement
	public final ObservableList<Encuentro> getEncuentros() {
		return this.encuentrosProperty().get();
	}

	public final void setEncuentros(final ObservableList<Encuentro> encuentros) {
		this.encuentrosProperty().set(encuentros);
	}

	public final ListProperty<Nota> notasProperty() {
		return this.notas;
	}
	
	@XmlElement
	public final ObservableList<Nota> getNotas() {
		return this.notasProperty().get();
	}

	public final void setNotas(final ObservableList<Nota> notas) {
		this.notasProperty().set(notas);
	}

	public final ListProperty<NPC> pnjsProperty() {
		return this.pnjs;
	}
	
	@XmlElement
	public final ObservableList<NPC> getPnjs() {
		return this.pnjsProperty().get();
	}

	public final void setPnjs(final ObservableList<NPC> pnjs) {
		this.pnjsProperty().set(pnjs);
	}

	public final ListProperty<Tesoros> tesorosProperty() {
		return this.tesoros;
	}

	@XmlElement
	public final ObservableList<Tesoros> getTesoros() {
		return this.tesorosProperty().get();
	}

	public final void setTesoros(final ObservableList<Tesoros> tesoros) {
		this.tesorosProperty().set(tesoros);
	}

	@Override
	public String toString() {
		return getNombre();
	}

	public Aventura clonar() {
		Aventura aventura = new Aventura();
		aventura.setNombre(getNombre());
		aventura.setPortrait(getPortrait());
		aventura.getEncuentros().setAll(getEncuentros());
		aventura.getNotas().setAll(getNotas());
		aventura.getTableros().setAll(getTableros());
		aventura.getPnjs().setAll(getPnjs());
		aventura.getTesoros().setAll(getTesoros());
		return aventura;
	}

}
