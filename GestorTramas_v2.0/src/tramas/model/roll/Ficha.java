package tramas.model.roll;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import tramas.model.Tesoros;

public class Ficha {

	private ObjectProperty<javafx.scene.image.Image> portrait;
	private StringProperty nombre;
	private StringProperty nivel;
	private ListProperty<Clase> clases;
	private StringProperty raza;
	private StringProperty sexo;
	private StringProperty edad;
	private ListProperty<Caracteristica> caracteristicas;
	private ListProperty<Salvacion> salvaciones;
	private IntegerProperty bonificadorAtaque;
	private IntegerProperty ca;
	private ListProperty<Habilidad> habilidades;
	private ListProperty<Dote> dotes;
	private ListProperty<Tesoros> equipo;
	private StringProperty notas;
	private IntegerProperty oro;
	
	public Ficha() {
		portrait = new SimpleObjectProperty<>(this, "portrait");
		nombre = new SimpleStringProperty(this, "nombre");
		nivel = new SimpleStringProperty(this, "nivel");
		clases = new SimpleListProperty<>(this, "clases");
		raza = new SimpleStringProperty(this, "raza");
		sexo = new SimpleStringProperty(this, "sexo");
		edad = new SimpleStringProperty(this, "edad");
		caracteristicas = new SimpleListProperty<>(this, "caracteristicas", FXCollections.observableArrayList());
		salvaciones = new SimpleListProperty<>(this, "salvaciones", FXCollections.observableArrayList());
		bonificadorAtaque = new SimpleIntegerProperty(this, "bonificadorAtaque");
		ca = new SimpleIntegerProperty(this, "ca");
		habilidades = new SimpleListProperty<>(this, "habilidades", FXCollections.observableArrayList());
		dotes = new SimpleListProperty<>(this, "dotes", FXCollections.observableArrayList());
		equipo = new SimpleListProperty<>(this, "equipo", FXCollections.observableArrayList());
		notas = new SimpleStringProperty(this, "notas");
		oro = new SimpleIntegerProperty(this, "oro");
	}

	public ObjectProperty<javafx.scene.image.Image> portraitProperty() {
		return this.portrait;
	}
	

	public Image getPortrait() {
		return this.portraitProperty().get();
	}
	

	public void setPortrait(final Image portrait) {
		this.portraitProperty().set(portrait);
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
	

	public StringProperty razaProperty() {
		return this.raza;
	}
	

	public String getRaza() {
		return this.razaProperty().get();
	}
	

	public void setRaza(final String raza) {
		this.razaProperty().set(raza);
	}
	

	public StringProperty sexoProperty() {
		return this.sexo;
	}
	

	public String getSexo() {
		return this.sexoProperty().get();
	}
	

	public void setSexo(final String sexo) {
		this.sexoProperty().set(sexo);
	}
	

	public StringProperty edadProperty() {
		return this.edad;
	}
	

	public String getEdad() {
		return this.edadProperty().get();
	}
	

	public void setEdad(final String edad) {
		this.edadProperty().set(edad);
	}
	

	public ListProperty<Caracteristica> caracteristicasProperty() {
		return this.caracteristicas;
	}
	

	public ObservableList<Caracteristica> getCaracteristicas() {
		return this.caracteristicasProperty().get();
	}
	

	public void setCaracteristicas(final ObservableList<Caracteristica> caracteristicas) {
		this.caracteristicasProperty().set(caracteristicas);
	}
	

	public ListProperty<Salvacion> salvacionesProperty() {
		return this.salvaciones;
	}
	

	public ObservableList<Salvacion> getSalvaciones() {
		return this.salvacionesProperty().get();
	}
	

	public void setSalvaciones(final ObservableList<Salvacion> salvaciones) {
		this.salvacionesProperty().set(salvaciones);
	}
	

	public IntegerProperty bonificadorAtaqueProperty() {
		return this.bonificadorAtaque;
	}
	

	public int getBonificadorAtaque() {
		return this.bonificadorAtaqueProperty().get();
	}
	

	public void setBonificadorAtaque(final int bonificadorAtaque) {
		this.bonificadorAtaqueProperty().set(bonificadorAtaque);
	}
	

	public IntegerProperty caProperty() {
		return this.ca;
	}
	

	public int getCa() {
		return this.caProperty().get();
	}
	

	public void setCa(final int ca) {
		this.caProperty().set(ca);
	}
	

	public ListProperty<Habilidad> habilidadesProperty() {
		return this.habilidades;
	}
	

	public ObservableList<Habilidad> getHabilidades() {
		return this.habilidadesProperty().get();
	}
	

	public void setHabilidades(final ObservableList<Habilidad> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}
	

	public ListProperty<Dote> dotesProperty() {
		return this.dotes;
	}
	

	public ObservableList<Dote> getDotes() {
		return this.dotesProperty().get();
	}
	

	public void setDotes(final ObservableList<Dote> dotes) {
		this.dotesProperty().set(dotes);
	}
	

	public ListProperty<Tesoros> equipoProperty() {
		return this.equipo;
	}
	

	public ObservableList<Tesoros> getEquipo() {
		return this.equipoProperty().get();
	}
	

	public void setEquipo(final ObservableList<Tesoros> equipo) {
		this.equipoProperty().set(equipo);
	}
	

	public StringProperty notasProperty() {
		return this.notas;
	}
	

	public String getNotas() {
		return this.notasProperty().get();
	}
	

	public void setNotas(final String notas) {
		this.notasProperty().set(notas);
	}

	public IntegerProperty oroProperty() {
		return this.oro;
	}
	

	public int getOro() {
		return this.oroProperty().get();
	}
	

	public void setOro(final int oro) {
		this.oroProperty().set(oro);
	}

	public StringProperty nivelProperty() {
		return this.nivel;
	}
	

	public String getNivel() {
		return this.nivelProperty().get();
	}
	

	public void setNivel(final String nivel) {
		this.nivelProperty().set(nivel);
	}
	

	public ListProperty<Clase> clasesProperty() {
		return this.clases;
	}
	

	public ObservableList<Clase> getClases() {
		return this.clasesProperty().get();
	}
	

	public void setClases(final ObservableList<Clase> clases) {
		this.clasesProperty().set(clases);
	}
	
	
	
	
	
	
	
	
}
