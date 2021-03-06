package tramas.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import tramas.model.roll.Ficha;

@XmlType
public class NPC {

	private StringProperty nombre;
	private ObjectProperty<javafx.scene.image.Image> portrait;
	private ObjectProperty<Ficha> ficha;
	private ObjectProperty<Tesoros> tesoroDropeable; 

	
	public NPC() {
		nombre = new SimpleStringProperty(this, "nombre");
		portrait = new SimpleObjectProperty<>(this, "portrait");
		ficha = new SimpleObjectProperty<>(this, "ficha");
		tesoroDropeable = new SimpleObjectProperty<>(this, "tesoroDropeable");
	}
	
	public NPC(String nombre) {
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
	

//	public final ObjectProperty<javafx.scene.image.Image> portraitProperty() {
//		return this.portrait;
//	}
//	
//
//	public final Image getPortrait() {
//		return this.portraitProperty().get();
//	}
//	
//
//	public final void setPortrait(final Image portrait) {
//		this.portraitProperty().set(portrait);
//	}
//	
//
//	public final ObjectProperty<Ficha> fichaProperty() {
//		return this.ficha;
//	}
//	
//
//	public final Ficha getFicha() {
//		return this.fichaProperty().get();
//	}
//	
//
//	public final void setFicha(final Ficha ficha) {
//		this.fichaProperty().set(ficha);
//	}
//
//	public ObjectProperty<Tesoros> tesoroDropeableProperty() {
//		return this.tesoroDropeable;
//	}
//	
//
//	public Tesoros getTesoroDropeable() {
//		return this.tesoroDropeableProperty().get();
//	}
//	
//
//	public void setTesoroDropeable(final Tesoros tesoroDropeable) {
//		this.tesoroDropeableProperty().set(tesoroDropeable);
//	}
	
	
	@Override
	public String toString() {
		return getNombre();
	}
	
	
	
}
