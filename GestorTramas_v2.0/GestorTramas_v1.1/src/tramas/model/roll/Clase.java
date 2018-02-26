package tramas.model.roll;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Clase {

	private StringProperty nombre;
	private StringProperty descripcion;
	private ListProperty<Dote> dotes;
	
	public Clase() {
		nombre = new SimpleStringProperty(this, "nombre");
		descripcion = new SimpleStringProperty(this, "descripcion");
		dotes = new SimpleListProperty<>(this, "dotes");
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
	

	public StringProperty descripcionProperty() {
		return this.descripcion;
	}
	

	public String getDescripcion() {
		return this.descripcionProperty().get();
	}
	

	public void setDescripcion(final String descripcion) {
		this.descripcionProperty().set(descripcion);
	}
	

	public ListProperty<Dote> dotesProperty() {
		return this.dotes;
	}
	

	public ObservableList<Dote> getDotes() {
		return this.dotesProperty().get();
	}
	

	public void setDotes(final ObservableList<Dote> dotes) {
		this.dotesProperty().set(dotes);
	}
	
	
	
}
