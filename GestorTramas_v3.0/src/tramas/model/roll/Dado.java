package tramas.model.roll;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Dado {

	private IntegerProperty cantidad;
	private IntegerProperty caras;
	private IntegerProperty total;

	public Dado() {
		cantidad = new SimpleIntegerProperty(this, "cantidad");
		caras = new SimpleIntegerProperty(this, "caras");
		total = new SimpleIntegerProperty(this, "total");

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

	public final IntegerProperty totalProperty() {
		return this.total;
	}

	public final int getTotal() {
		return this.totalProperty().get();
	}

	public final void setTotal(final int total) {
		this.totalProperty().set(total);
	}

	public int getTiradaSimple() {
		return (int) (Math.random() * getCaras() + 1) * getCantidad();
	}

	public String getTiradaMultiple() {
		return rollResultadoDesglosado();
	}

	private ArrayList<Integer> desglose() {
		ArrayList<Integer> resultados = new ArrayList<>();
		for (int i = 0; i < getCantidad(); i++) {
			resultados.add((int)(Math.random() * getCaras() + 1));
		}
		return resultados;
	}

	private String rollResultadoDesglosado() {
		String resultado = "";
		int valorAcumulado = 0;
		ArrayList<Integer> resultados = desglose();
		for (int i = 0; i < resultados.size(); i++) {
			if (i != resultados.size() - 1) {
				resultado += String.valueOf(resultados.get(i) + " + ");
			} else {
				resultado += String.valueOf(resultados.get(i));
			}
			valorAcumulado += resultados.get(i);
			setTotal(valorAcumulado);
		}
		return "(" + resultado + ")" + " = " + String.valueOf(valorAcumulado);
	}

	@Override
	public String toString() {
		return String.valueOf(getCantidad())+ "d" + getCaras();
	}

}
