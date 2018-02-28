package tramas.calculadora.logica;

import java.util.ArrayList;

import tramas.model.roll.Dado;

/**
 * Implementación de la lógica de una calculadora.
 * 
 * @author Eduardo Marrero
 */
public class Calculadora {

	public static final char IGUAL = '=';
	public static final char SUMAR = '+';
	public static final char RESTAR = '-';
	public static final char DIVIDIR = '/';
	public static final char MULTIPLICAR = 'x';

	private ArrayList<Integer> resultadosArray = new ArrayList<>();
	private String operador = "+";
	private Boolean nuevoOperando = false;
	private String pantallaSalida = "";
	private String pantallaEntrada = "";
	private Dado dado;
	private String cantidadAcumulada = "";

	public Calculadora() {
		dado = new Dado();

	}

	public String insertarDigito(String digito) {
		if(pantallaEntrada.contains(dado.toString())) {
			pantallaEntrada += operador;
			dado.getTotal();
		}
		Integer.parseInt(digito);
		pantallaEntrada += digito;
		cantidadAcumulada += digito;

		return pantallaEntrada;
	}

	public Dado insertarCarasDado(int caras) {
		dado = new Dado();
		dado.setCaras(caras);
		if (pantallaEntrada.equals("")) {
			dado.setCantidad(1);
		} else {
			dado.setCantidad(Integer.parseInt(cantidadAcumulada));
		}
		if (!nuevoOperando) {
			pantallaEntrada = dado.toString();
		} else {
			pantallaEntrada += dado.toString();
		}

		return dado;
	}

	public void operar(String operador) {
		nuevoOperando = true;
		if (operador.equals("+")) {
			this.operador = operador;
		}

	}

	public void resultado() {
		pantallaSalida += dado.getTiradaMultiple();
		resultadosArray.add(dado.getTotal());
	}

	public void borrarTodo() {
		pantallaEntrada = "";
		cantidadAcumulada = "";

	}

	public void borrarPantallaSalida() {
		pantallaSalida = "";

	}

	public String getPantallaSalida() {
		return pantallaSalida;
	}

	public String getPantallaEntrada() {
		return pantallaEntrada;
	}



}
