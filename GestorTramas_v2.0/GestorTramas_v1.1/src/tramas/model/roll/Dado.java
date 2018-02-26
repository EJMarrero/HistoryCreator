package tramas.model.roll;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Dado {

	private IntegerProperty cantidad;
	private IntegerProperty caras;
	private IntegerProperty resultado;
	
	public Dado ( ) {
		cantidad = new SimpleIntegerProperty(this, "cantidad");
		caras = new SimpleIntegerProperty(this, "caras");
		resultado = new SimpleIntegerProperty(this, "resultado");
	}

	public Dado roll () {
		Dado dado = new Dado();
		int resultado = ((int)Math.random()*dado.getCaras()+1)*dado.getCantidad();
		dado.setResultado(resultado);
		return dado;
	}

	public IntegerProperty cantidadProperty() {
		return this.cantidad;
	}

	public int getCantidad() {
		return this.cantidadProperty().get();
	}

	public void setCantidad(final int cantidad) {
		this.cantidadProperty().set(cantidad);
	}

	public IntegerProperty carasProperty() {
		return this.caras;
	}

	public int getCaras() {
		return this.carasProperty().get();
	}

	public void setCaras(final int caras) {
		this.carasProperty().set(caras);
	}

	public IntegerProperty resultadoProperty() {
		return this.resultado;
	}

	public int getResultado() {
		return this.resultadoProperty().get();
	}

	public void setResultado(final int resultado) {
		this.resultadoProperty().set(resultado);
	}
	
}
