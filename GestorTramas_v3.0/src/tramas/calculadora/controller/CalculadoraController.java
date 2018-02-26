package tramas.calculadora.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tramas.calculadora.logica.Calculadora;
import tramas.model.Nota;
import tramas.model.calculadora.CalculadoraModel;

public class CalculadoraController implements Initializable {

	
	private Stage stage;
	@FXML
	private BorderPane view;

	@FXML
	private TextField pantallaEntrada;

	@FXML
	private TextField pantallaSalida;

	@FXML
	private Button d2Button, d4Button, d6Button, d8Button, d10Button, d12Button, d20Button, sieteButton, cuatroButton,
			unoButton, ceroButton, d100Button, ochoButton, cincoButton, dosButton, parentesisIzqButton, dxButton,
			nueveButton, seisButton, tresButton, parentesisDerButton, borrarButton, borrarTodosButton, dividirButton,
			multiplicarButton, restarButton, sumarButton, rollButton;

	
	//Model
	private CalculadoraModel model = new CalculadoraModel();
	
	//Logica de Negocio
	Calculadora calculadora = new Calculadora();
	
	public CalculadoraController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculadoraView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pantallaEntrada.textProperty().bindBidirectional(model.pantallaEntradaProperty());
		pantallaSalida.textProperty().bindBidirectional(model.pantallaSalidaProperty());
//		ceroButton.textProperty().bindBidirectional(model.ceroProperty());
//		unoButton.textProperty().bindBidirectional(model.unoProperty());
//		dosButton.textProperty().bindBidirectional(model.dosProperty());
//		tresButton.textProperty().bindBidirectional(model.tresProperty());
//		cuatroButton.textProperty().bindBidirectional(model.cuatroProperty());
//		cincoButton.textProperty().bindBidirectional(model.cincoProperty());
//		seisButton.textProperty().bindBidirectional(model.seisProperty());
//		sieteButton.textProperty().bindBidirectional(model.sieteProperty());
//		ochoButton.textProperty().bindBidirectional(model.ochoProperty());
//		nueveButton.textProperty().bindBidirectional(model.nueveProperty());
		Bindings.bindBidirectional(model.unoProperty(), unoButton.textProperty());
		Bindings.bindBidirectional(model.dosProperty(), dosButton.textProperty());
		Bindings.bindBidirectional(model.tresProperty(), tresButton.textProperty());
		Bindings.bindBidirectional(model.cuatroProperty(), cuatroButton.textProperty());
		Bindings.bindBidirectional(model.cincoProperty(), cincoButton.textProperty());
		Bindings.bindBidirectional(model.seisProperty(), seisButton.textProperty());
		Bindings.bindBidirectional(model.sieteProperty(), sieteButton.textProperty());
		Bindings.bindBidirectional(model.ochoProperty(), ochoButton.textProperty());
		Bindings.bindBidirectional(model.nueveProperty(), nueveButton.textProperty());
		Bindings.bindBidirectional(model.cuatroCarasProperty(), d4Button.textProperty());
		
		model.pantallaEntradaProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d+\\.[0-9]")) {
		           pantallaEntrada.setText(newValue.replaceAll("\\d+\\.[0-9]", ""));
		        }
		    }
		});

	}

	@FXML
	private void getPantallaEntrada() {
		model.pantallaEntradaProperty().set(calculadora.getPantallaEntrada());
	}
	@FXML
	private void getPantallaSalida() {
		model.pantallaSalidaProperty().set(calculadora.getPantallaSalida());
	}
	
	@FXML
	void borrar(ActionEvent event) {
//		calculadora.borrar();
	}

	@FXML
	void borrarTodo(ActionEvent event) {
		calculadora.borrarTodo();
		getPantallaEntrada();
	}

	@FXML
	void insertarCantidad(ActionEvent event) {
		Button boton = (Button) event.getSource();
		calculadora.insertarDigito(boton.getText());
		getPantallaEntrada();
	}
	
	@FXML
	void insertarCaras(ActionEvent event) {
		Button boton = (Button) event.getSource();
		String valorBoton = boton.getText();
		calculadora.insertarCarasDado((Integer.parseInt(valorBoton.substring(1, valorBoton.length()))));
		getPantallaEntrada();
		
	}

	@FXML
	void operar(ActionEvent event) {
		Button boton = (Button) event.getSource();
//		char [] caracteres = boton.getText().toCharArray();
		calculadora.operar(boton.getText());
		System.out.println(boton.getText());
//		calculadora.operar(caracteres[0]);
		getPantallaEntrada();
	}
	
	@FXML
	void rollear(ActionEvent event) {
		calculadora.borrarPantallaSalida();
		calculadora.resultado();
		getPantallaSalida();
	}
	
	public Calculadora show(Stage parentStage) {
		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/tramas/resources/style.css").toExternalForm());
		stage = new Stage();
		stage.getIcons().add(parentStage.getIcons().get(0));
		if (parentStage !=null)
		stage.initOwner(parentStage);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Roll Calculator");
		stage.setResizable(true);
		stage.setScene(scene);
		stage.showAndWait();
		return calculadora;
	}

}
