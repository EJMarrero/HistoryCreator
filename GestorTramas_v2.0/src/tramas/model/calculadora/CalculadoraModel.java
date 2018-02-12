package tramas.model.calculadora;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CalculadoraModel {

	private StringProperty cero, uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve;
	private StringProperty dosCaras, cuatroCaras, seisCaras, ochoCaras, diezCaras, doceCaras, veinteCaras, cienCaras, xCaras;
	private StringProperty multiplicar, dividir, sumar, restar, borrarTodo, borrar, igual;
	private IntegerProperty operando;
	private StringProperty pantallaEntrada;
	private StringProperty pantallaSalida;
	
	public CalculadoraModel() {
		this.cero = new SimpleStringProperty();
		this.uno = new SimpleStringProperty();
		this.dos = new SimpleStringProperty();
		this.tres = new SimpleStringProperty();
		this.cuatro = new SimpleStringProperty();
		this.cinco = new SimpleStringProperty();
		this.seis = new SimpleStringProperty();
		this.siete = new SimpleStringProperty();
		this.ocho = new SimpleStringProperty();
		this.nueve = new SimpleStringProperty();
		this.dosCaras = new SimpleStringProperty();
		this.cuatroCaras = new SimpleStringProperty();
		this.seisCaras = new SimpleStringProperty();
		this.ochoCaras = new SimpleStringProperty();
		this.diezCaras = new SimpleStringProperty();
		this.doceCaras = new SimpleStringProperty();
		this.veinteCaras = new SimpleStringProperty();
		this.cienCaras = new SimpleStringProperty();
		this.multiplicar = new SimpleStringProperty();
		this.dividir = new SimpleStringProperty();
		this.sumar = new SimpleStringProperty();
		this.restar = new SimpleStringProperty();
		this.borrar = new SimpleStringProperty();
		this.borrarTodo = new SimpleStringProperty();
		this.igual = new SimpleStringProperty();
		this.operando = new SimpleIntegerProperty();
		this.pantallaEntrada = new SimpleStringProperty();
		this.pantallaSalida = new SimpleStringProperty();
	}

	public final StringProperty ceroProperty() {
		return this.cero;
	}
	

	public final String getCero() {
		return this.ceroProperty().get();
	}
	

	public final void setCero(final String cero) {
		this.ceroProperty().set(cero);
	}
	

	public final StringProperty unoProperty() {
		return this.uno;
	}
	

	public final String getUno() {
		return this.unoProperty().get();
	}
	

	public final void setUno(final String uno) {
		this.unoProperty().set(uno);
	}
	

	public final StringProperty dosProperty() {
		return this.dos;
	}
	

	public final String getDos() {
		return this.dosProperty().get();
	}
	

	public final void setDos(final String dos) {
		this.dosProperty().set(dos);
	}
	

	public final StringProperty tresProperty() {
		return this.tres;
	}
	

	public final String getTres() {
		return this.tresProperty().get();
	}
	

	public final void setTres(final String tres) {
		this.tresProperty().set(tres);
	}
	

	public final StringProperty cuatroProperty() {
		return this.cuatro;
	}
	

	public final String getCuatro() {
		return this.cuatroProperty().get();
	}
	

	public final void setCuatro(final String cuatro) {
		this.cuatroProperty().set(cuatro);
	}
	

	public final StringProperty cincoProperty() {
		return this.cinco;
	}
	

	public final String getCinco() {
		return this.cincoProperty().get();
	}
	

	public final void setCinco(final String cinco) {
		this.cincoProperty().set(cinco);
	}
	

	public final StringProperty seisProperty() {
		return this.seis;
	}
	

	public final String getSeis() {
		return this.seisProperty().get();
	}
	

	public final void setSeis(final String seis) {
		this.seisProperty().set(seis);
	}
	

	public final StringProperty sieteProperty() {
		return this.siete;
	}
	

	public final String getSiete() {
		return this.sieteProperty().get();
	}
	

	public final void setSiete(final String siete) {
		this.sieteProperty().set(siete);
	}
	

	public final StringProperty ochoProperty() {
		return this.ocho;
	}
	

	public final String getOcho() {
		return this.ochoProperty().get();
	}
	

	public final void setOcho(final String ocho) {
		this.ochoProperty().set(ocho);
	}
	

	public final StringProperty nueveProperty() {
		return this.nueve;
	}
	

	public final String getNueve() {
		return this.nueveProperty().get();
	}
	

	public final void setNueve(final String nueve) {
		this.nueveProperty().set(nueve);
	}
	

	public final StringProperty dosCarasProperty() {
		return this.dosCaras;
	}
	

	public final String getDosCaras() {
		return this.dosCarasProperty().get();
	}
	

	public final void setDosCaras(final String dosCaras) {
		this.dosCarasProperty().set(dosCaras);
	}
	

	public final StringProperty cuatroCarasProperty() {
		return this.cuatroCaras;
	}
	

	public final String getCuatroCaras() {
		return this.cuatroCarasProperty().get();
	}
	

	public final void setCuatroCaras(final String cuatroCaras) {
		this.cuatroCarasProperty().set(cuatroCaras);
	}
	

	public final StringProperty seisCarasProperty() {
		return this.seisCaras;
	}
	

	public final String getSeisCaras() {
		return this.seisCarasProperty().get();
	}
	

	public final void setSeisCaras(final String seisCaras) {
		this.seisCarasProperty().set(seisCaras);
	}
	

	public final StringProperty ochoCarasProperty() {
		return this.ochoCaras;
	}
	

	public final String getOchoCaras() {
		return this.ochoCarasProperty().get();
	}
	

	public final void setOchoCaras(final String ochoCaras) {
		this.ochoCarasProperty().set(ochoCaras);
	}
	

	public final StringProperty diezCarasProperty() {
		return this.diezCaras;
	}
	

	public final String getDiezCaras() {
		return this.diezCarasProperty().get();
	}
	

	public final void setDiezCaras(final String diezCaras) {
		this.diezCarasProperty().set(diezCaras);
	}
	

	public final StringProperty doceCarasProperty() {
		return this.doceCaras;
	}
	

	public final String getDoceCaras() {
		return this.doceCarasProperty().get();
	}
	

	public final void setDoceCaras(final String doceCaras) {
		this.doceCarasProperty().set(doceCaras);
	}
	

	public final StringProperty veinteCarasProperty() {
		return this.veinteCaras;
	}
	

	public final String getVeinteCaras() {
		return this.veinteCarasProperty().get();
	}
	

	public final void setVeinteCaras(final String veinteCaras) {
		this.veinteCarasProperty().set(veinteCaras);
	}
	

	public final StringProperty cienCarasProperty() {
		return this.cienCaras;
	}
	

	public final String getCienCaras() {
		return this.cienCarasProperty().get();
	}
	

	public final void setCienCaras(final String cienCaras) {
		this.cienCarasProperty().set(cienCaras);
	}
	

	public final StringProperty xCarasProperty() {
		return this.xCaras;
	}
	

	public final String getXCaras() {
		return this.xCarasProperty().get();
	}
	

	public final void setXCaras(final String xCaras) {
		this.xCarasProperty().set(xCaras);
	}
	

	public final StringProperty multiplicarProperty() {
		return this.multiplicar;
	}
	

	public final String getMultiplicar() {
		return this.multiplicarProperty().get();
	}
	

	public final void setMultiplicar(final String multiplicar) {
		this.multiplicarProperty().set(multiplicar);
	}
	

	public final StringProperty dividirProperty() {
		return this.dividir;
	}
	

	public final String getDividir() {
		return this.dividirProperty().get();
	}
	

	public final void setDividir(final String dividir) {
		this.dividirProperty().set(dividir);
	}
	

	public final StringProperty sumarProperty() {
		return this.sumar;
	}
	

	public final String getSumar() {
		return this.sumarProperty().get();
	}
	

	public final void setSumar(final String sumar) {
		this.sumarProperty().set(sumar);
	}
	

	public final StringProperty restarProperty() {
		return this.restar;
	}
	

	public final String getRestar() {
		return this.restarProperty().get();
	}
	

	public final void setRestar(final String restar) {
		this.restarProperty().set(restar);
	}
	

	public final StringProperty borrarTodoProperty() {
		return this.borrarTodo;
	}
	

	public final String getBorrarTodo() {
		return this.borrarTodoProperty().get();
	}
	

	public final void setBorrarTodo(final String borrarTodo) {
		this.borrarTodoProperty().set(borrarTodo);
	}
	

	public final StringProperty borrarProperty() {
		return this.borrar;
	}
	

	public final String getBorrar() {
		return this.borrarProperty().get();
	}
	

	public final void setBorrar(final String borrar) {
		this.borrarProperty().set(borrar);
	}
	

	public final StringProperty igualProperty() {
		return this.igual;
	}
	

	public final String getIgual() {
		return this.igualProperty().get();
	}
	

	public final void setIgual(final String igual) {
		this.igualProperty().set(igual);
	}
	

	public final IntegerProperty operandoProperty() {
		return this.operando;
	}
	

	public final int getOperando() {
		return this.operandoProperty().get();
	}
	

	public final void setOperando(final int operando) {
		this.operandoProperty().set(operando);
	}

	public final StringProperty pantallaEntradaProperty() {
		return this.pantallaEntrada;
	}
	

	public final String getPantallaEntrada() {
		return this.pantallaEntradaProperty().get();
	}
	

	public final void setPantallaEntrada(final String pantallaEntrada) {
		this.pantallaEntradaProperty().set(pantallaEntrada);
	}
	

	public final StringProperty pantallaSalidaProperty() {
		return this.pantallaSalida;
	}
	

	public final String getPantallaSalida() {
		return this.pantallaSalidaProperty().get();
	}
	

	public final void setPantallaSalida(final String pantallaSalida) {
		this.pantallaSalidaProperty().set(pantallaSalida);
	}
	
	


	
	
}
