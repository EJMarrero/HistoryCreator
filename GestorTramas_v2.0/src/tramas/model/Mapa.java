package tramas.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import tramas.model.adapter.ImageAdapter;

@XmlType
public class Mapa {

	
	private StringProperty nombre;
	private ObjectProperty<javafx.scene.image.Image> imagen;
	
	public Mapa() {
		nombre = new SimpleStringProperty(this, "nombre");
		imagen = new SimpleObjectProperty<>(this, "imagen");
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
	

	public final ObjectProperty<javafx.scene.image.Image> imagenProperty() {
		return this.imagen;
	}
	
	@XmlElement
	@XmlJavaTypeAdapter(ImageAdapter.class)
	public final Image getImagen() {
		return this.imagenProperty().get();
	}
	

	public final void setImagen(final Image imagen) {
		this.imagenProperty().set(imagen);
	}
	

	@Override
	public String toString() {
		return getNombre();
	}
	
	
}
