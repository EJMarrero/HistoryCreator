package tramas.test;

import tramas.model.roll.Dado;

public class Test {

	public static void main(String[] args) {
		Dado dado = new Dado();
		dado.setCantidad(5);
		dado.setCaras(100);
		Dado dado2 = new Dado();
		dado2.setCantidad(1);
		dado2.setCaras(20);
		
		
		
//		System.out.println(dado.getTiradaSimple());
//		System.out.println(dado.getCantidad());
//		System.out.println(dado.getCaras());

		System.out.println(dado.getTiradaMultiple());
		System.out.println(dado.getTotal());
		System.out.println(dado2.getTiradaMultiple());
		System.out.println(dado2.getTotal());
		System.out.println(dado.toString()+" + "+dado2.toString());
	}

}
