package tramas.model;

import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tesoros {

	private StringProperty nombre;
	private IntegerProperty valor;

	public Tesoros() {
		nombre = new SimpleStringProperty(this, "nombre");
		valor = new SimpleIntegerProperty(this, "valor");

	}

	public final StringProperty nombreProperty() {
		return this.nombre;
	}

	public final String getNombre() {
		return this.nombreProperty().get();
	}

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final IntegerProperty valorProperty() {
		return this.valor;
	}

	public final int getValor() {
		return this.valorProperty().get();
	}

	public final void setValor(final int valor) {
		this.valorProperty().set(valor);
	}

}
