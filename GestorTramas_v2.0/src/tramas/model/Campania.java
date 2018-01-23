package tramas.model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


@XmlRootElement
@XmlType
public class Campania {

	private StringProperty nombre;
	private ListProperty<Aventura> aventuras;
	private ListProperty<Nota> notas;
	private ListProperty<Personaje> personajes;
	private ObjectProperty<Image> mapaCampania;
	
	public Campania () {
		nombre = new SimpleStringProperty(this, "nombre");
		aventuras = new SimpleListProperty<>(this, "aventuras", FXCollections.observableArrayList());
		notas = new SimpleListProperty<>(this, "notas", FXCollections.observableArrayList());
		personajes = new SimpleListProperty<>(this, "personajes", FXCollections.observableArrayList());
		mapaCampania = new SimpleObjectProperty<>(this, "mapaCampania");
	}

	

	
	
	public void save(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Campania.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(this, file);
	}

	public static Campania load(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Campania.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Campania) unmarshaller.unmarshal(file);
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
	





	public final ListProperty<Aventura> aventurasProperty() {
		return this.aventuras;
	}
	




	@XmlElement
	public final ObservableList<Aventura> getAventuras() {
		return this.aventurasProperty().get();
	}
	





	public final void setAventuras(final ObservableList<Aventura> aventuras) {
		this.aventurasProperty().set(aventuras);
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
	





	public final ListProperty<Personaje> personajesProperty() {
		return this.personajes;
	}
	




	@XmlElement
	public final ObservableList<Personaje> getPersonajes() {
		return this.personajesProperty().get();
	}
	





	public final void setPersonajes(final ObservableList<Personaje> personajes) {
		this.personajesProperty().set(personajes);
	}
	




	
	public final ObjectProperty<Image> mapaCampaniaProperty() {
		return this.mapaCampania;
	}
	




	@XmlElement
	public final Image getMapaCampania() {
		return this.mapaCampaniaProperty().get();
	}
	





	public final void setMapaCampania(final Image mapaCampania) {
		this.mapaCampaniaProperty().set(mapaCampania);
	}
	
	
	
	
	
	
	
}
