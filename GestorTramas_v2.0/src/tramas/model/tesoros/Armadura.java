package tramas.model.tesoros;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import tramas.model.Tesoros;

public class Armadura extends Tesoros {

	private StringProperty categoria; //Ligera, Intermedia, Pesada.
	private IntegerProperty caBase;
	private IntegerProperty bonificadorCa;
	//TODO penalizador a habilidades.
	
	public Armadura () {
		categoria = new SimpleStringProperty(this, "categoria");
		caBase = new SimpleIntegerProperty(this, "caBase");
		bonificadorCa = new SimpleIntegerProperty(this, "bonificadorCa");
	}

	public StringProperty categoriaProperty() {
		return this.categoria;
	}
	

	public String getCategoria() {
		return this.categoriaProperty().get();
	}
	

	public void setCategoria(final String categoria) {
		this.categoriaProperty().set(categoria);
	}
	

	public IntegerProperty caBaseProperty() {
		return this.caBase;
	}
	

	public int getCaBase() {
		return this.caBaseProperty().get();
	}
	

	public void setCaBase(final int caBase) {
		this.caBaseProperty().set(caBase);
	}
	

	public IntegerProperty bonificadorCaProperty() {
		return this.bonificadorCa;
	}
	

	public int getBonificadorCa() {
		return this.bonificadorCaProperty().get();
	}
	

	public void setBonificadorCa(final int bonificadorCa) {
		this.bonificadorCaProperty().set(bonificadorCa);
	}
	
	
	
	
}
