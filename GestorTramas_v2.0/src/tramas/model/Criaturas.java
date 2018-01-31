package tramas.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import tramas.model.roll.HabilidadEspecial;

public class Criaturas extends NPC {

	private ListProperty<HabilidadEspecial> habilidadesEspeciales;
	
	public Criaturas () {
		habilidadesEspeciales = new SimpleListProperty<>(this, "habilidadesEspeciales");
	}

	public ListProperty<HabilidadEspecial> habilidadesEspecialesProperty() {
		return this.habilidadesEspeciales;
	}
	

	public ObservableList<HabilidadEspecial> getHabilidadesEspeciales() {
		return this.habilidadesEspecialesProperty().get();
	}
	

	public void setHabilidadesEspeciales(final ObservableList<HabilidadEspecial> habilidadesEspeciales) {
		this.habilidadesEspecialesProperty().set(habilidadesEspeciales);
	}
	
	
	
	
}
