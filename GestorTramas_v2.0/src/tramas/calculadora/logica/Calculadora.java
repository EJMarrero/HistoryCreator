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
	private int operando;
	private String operador = "+";
	private Boolean nuevoOperando = false;
	private String pantallaSalida = "";
	private String pantallaEntrada = "";
	private Boolean nuevoDado;
	private Dado dado;
	private String valorAcumulado;
	private String cantidadAcumulada = "";

	public Calculadora() {
		dado = new Dado();

	}

	public String insertarDigito(String digito) {
		// if(pantallaEntrada.equals("")) {
		// pantallaEntrada += digito;
		// cantidadAcumulada += digito;
		// } else if (pantallaEntrada.contains(dado.toString())){
		// System.out.println(operador);
		// pantallaEntrada += operador;
		// pantallaEntrada += digito;
		// cantidadAcumulada += digito;
		// }
		if(pantallaEntrada.contains(dado.toString())) {
			pantallaEntrada += operador;
			operando = dado.getTotal();
		}
		operando = Integer.parseInt(digito);
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
		// valorAcumulado = dado.getTiradaMultiple();
		if (!nuevoOperando) {
			pantallaEntrada = dado.toString();
		} else {
			pantallaEntrada += dado.toString();
		}

		return dado;
	}

	public void operar(String operador) {
		nuevoOperando = true;
		
		Integer operando2 = resultadosArray.get(resultadosArray.size()-1);
		if (operador.equals("+")) {
			this.operador = operador;
			operando += operando2;
			System.out.println(operando);
		}

	}

	public void resultado() {
//		operador = "+";
//		pantallaEntrada += "+";
		pantallaSalida += dado.getTiradaMultiple();
		System.out.println(dado.getTotal());
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
