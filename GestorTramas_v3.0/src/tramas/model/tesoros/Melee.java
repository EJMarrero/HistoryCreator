package tramas.model.tesoros;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import tramas.model.Tesoros;
import tramas.model.roll.Dado;

public class Melee extends Tesoros{

	private StringProperty tipo;
	private ObjectProperty<Dado> dadosDeDanio;
	private IntegerProperty mejoraDanio;
	
	public Melee() {
		super();
		tipo = new SimpleStringProperty(this, "tipo");
		dadosDeDanio = new SimpleObjectProperty<>(this, "dadosDeDanio");
		mejoraDanio = new SimpleIntegerProperty(this, "mejoraDanio");
	}

	public final StringProperty tipoProperty() {
		return this.tipo;
	}
	

	public final String getTipo() {
		return this.tipoProperty().get();
	}
	

	public final void setTipo(final String tipo) {
		this.tipoProperty().set(tipo);
	}
	

	public final ObjectProperty<Dado> dadosDeDanioProperty() {
		return this.dadosDeDanio;
	}
	

	public final Dado getDadosDeDanio() {
		return this.dadosDeDanioProperty().get();
	}
	

	public final void setDadosDeDanio(final Dado dadosDeDanio) {
		this.dadosDeDanioProperty().set(dadosDeDanio);
	}
	

	public final IntegerProperty mejoraDanioProperty() {
		return this.mejoraDanio;
	}
	

	public final int getMejoraDanio() {
		return this.mejoraDanioProperty().get();
	}
	

	public final void setMejoraDanio(final int mejoraDanio) {
		this.mejoraDanioProperty().set(mejoraDanio);
	}
	
	
	
	
	
}
