package tramas.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlType
public class Encuentro {

	private StringProperty nombre;
	private ListProperty<Criaturas> criaturas;
	
	public Encuentro() {
		nombre = new SimpleStringProperty(this, "nombre");
		criaturas = new SimpleListProperty<>(this, "criaturas", FXCollections.observableArrayList());
	}
	
	public Encuentro(String nombre) {
		this.nombre = new SimpleStringProperty(this, "nombre");
		this.nombre.set(nombre);
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
	

//	public final ListProperty<Criaturas> criaturasProperty() {
//		return this.criaturas;
//	}
//	
//	@XmlElement
//	public final ObservableList<Criaturas> getCriaturas() {
//		return this.criaturasProperty().get();
//	}
//	
//
//	public final void setCriaturas(final ObservableList<Criaturas> criaturas) {
//		this.criaturasProperty().set(criaturas);
//	}
	
@Override
public String toString() {
	return getNombre();
}
	
	
	
}
