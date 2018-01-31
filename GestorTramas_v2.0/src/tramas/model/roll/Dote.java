package tramas.model.roll;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dote {

	private StringProperty nombre;
	private StringProperty descripcion;
	
	public Dote () {
		nombre = new SimpleStringProperty(this, "nombre");
		descripcion = new SimpleStringProperty(this, "descripcion");
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
	
	
	
	
}
