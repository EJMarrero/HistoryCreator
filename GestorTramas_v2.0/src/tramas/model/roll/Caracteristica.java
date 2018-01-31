package tramas.model.roll;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Caracteristica {

	public StringProperty nombre;
	public IntegerProperty puntos;
	public IntegerProperty bonificador;
	
	public Caracteristica () {
		nombre = new SimpleStringProperty(this, "nombre");
		//TODO como aplicar el sistema??
			//Debe seguir la siguiente relación: 
// 					Bono:0 ===> -5
//						 2 ===> -4	
//						 4 ===> -3
//						 6 ===> -2
//						 8 ===> -1
//						 10 ===> 0
//						 12 ===> 1
//						 14 ===> 2
//						 16 ===> 3
//						 18 ===> 4
//						 20 ===> 5
					
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
	}
	
	
}
