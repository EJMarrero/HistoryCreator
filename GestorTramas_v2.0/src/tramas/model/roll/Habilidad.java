package tramas.model.roll;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Habilidad {

	private StringProperty nombre;
	private IntegerProperty rangos;
	private IntegerProperty bonificadorCaracteristica;
	private IntegerProperty bonificadorVarios;
	private IntegerProperty total;
	
	public Habilidad () {
		nombre = new SimpleStringProperty(this, "nombre");
		rangos = new SimpleIntegerProperty(this, "rangos");
		bonificadorCaracteristica = new SimpleIntegerProperty(this, "bonificadorCaracteristica");
		bonificadorVarios = new SimpleIntegerProperty(this, "bonificadorVarios");
		total = new SimpleIntegerProperty(this, "total");
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
	

	public IntegerProperty rangosProperty() {
		return this.rangos;
	}
	

	public int getRangos() {
		return this.rangosProperty().get();
	}
	

	public void setRangos(final int rangos) {
		this.rangosProperty().set(rangos);
	}
	

	public IntegerProperty bonificadorCaracteristicaProperty() {
		return this.bonificadorCaracteristica;
	}
	

	public int getBonificadorCaracteristica() {
		return this.bonificadorCaracteristicaProperty().get();
	}
	

	public void setBonificadorCaracteristica(final int bonificadorCaracteristica) {
		this.bonificadorCaracteristicaProperty().set(bonificadorCaracteristica);
	}
	

	public IntegerProperty bonificadorVariosProperty() {
		return this.bonificadorVarios;
	}
	

	public int getBonificadorVarios() {
		return this.bonificadorVariosProperty().get();
	}
	

	public void setBonificadorVarios(final int bonificadorVarios) {
		this.bonificadorVariosProperty().set(bonificadorVarios);
	}
	

	public IntegerProperty totalProperty() {
		return this.total;
	}
	

	public int getTotal() {
		return this.totalProperty().get();
	}
	

	public void setTotal(final int total) {
		this.totalProperty().set(total);
	}
	

	
	
	
	
	
}
