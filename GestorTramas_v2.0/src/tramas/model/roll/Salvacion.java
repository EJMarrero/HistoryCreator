package tramas.model.roll;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Salvacion {

	private StringProperty nombre;
	private IntegerProperty fortaleza;
	private IntegerProperty reflejos;
	private IntegerProperty voluntad;
	
	public Salvacion ( ) {
		nombre = new SimpleStringProperty(this, "nombre");
		fortaleza = new SimpleIntegerProperty(this, "fortaleza");
		reflejos = new SimpleIntegerProperty(this, "reflejos");
		voluntad = new SimpleIntegerProperty(this, "voluntad");
	}

	public IntegerProperty fortalezaProperty() {
		return this.fortaleza;
	}
	

	public int getFortaleza() {
		return this.fortalezaProperty().get();
	}
	

	public void setFortaleza(final int fortaleza) {
		this.fortalezaProperty().set(fortaleza);
	}
	

	public IntegerProperty reflejosProperty() {
		return this.reflejos;
	}
	

	public int getReflejos() {
		return this.reflejosProperty().get();
	}
	

	public void setReflejos(final int reflejos) {
		this.reflejosProperty().set(reflejos);
	}
	

	public IntegerProperty voluntadProperty() {
		return this.voluntad;
	}
	

	public int getVoluntad() {
		return this.voluntadProperty().get();
	}
	

	public void setVoluntad(final int voluntad) {
		this.voluntadProperty().set(voluntad);
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
	
	
	
	
	
}
