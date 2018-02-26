package tramas.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Nota {

	private StringProperty titulo;
	private StringProperty texto;
	private ListProperty<javafx.scene.image.Image> imagenes;
	
	public Nota() {
		titulo = new SimpleStringProperty(this, "nombre");
		texto = new SimpleStringProperty(this, "texto");
		imagenes = new SimpleListProperty<>(this, "imagenes");
	}

	public StringProperty tituloProperty() {
		return this.titulo;
	}
	

	public String getTitulo() {
		return this.tituloProperty().get();
	}
	

	public void setTitulo(final String titulo) {
		this.tituloProperty().set(titulo);
	}
	

	public StringProperty textoProperty() {
		return this.texto;
	}
	

	public String getTexto() {
		return this.textoProperty().get();
	}
	

	public void setTexto(final String texto) {
		this.textoProperty().set(texto);
	}

	public static void copiar(Nota notaNueva, Nota notaDevuelta) {
		notaDevuelta.setTitulo(notaNueva.getTitulo());
		notaDevuelta.setTexto(notaNueva.getTexto());
	}
	 
	@Override
	public String toString() {
		return getTitulo();
	}

	public ListProperty<javafx.scene.image.Image> imagenesProperty() {
		return this.imagenes;
	}
	

	public ObservableList<Image> getImagenes() {
		return this.imagenesProperty().get();
	}
	

	public void setImagenes(final ObservableList<Image> imagenes) {
		this.imagenesProperty().set(imagenes);
	}
	
	
	

	
	
}
