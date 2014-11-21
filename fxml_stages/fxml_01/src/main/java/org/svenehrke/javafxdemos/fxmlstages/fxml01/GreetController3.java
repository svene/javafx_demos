package org.svenehrke.javafxdemos.fxmlstages.fxml01;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GreetController3 implements Initializable {

	@FXML
	private Button greetingButton;

	@FXML
	private TextField nameTextField;

	@FXML
	private Label greetingLabel;

	/** This prefix can be set from outside. For example it could be injected by a dependency injection framework like afterburner */
	String prefix = "Hello";


	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		nameTextField.setText("Duke");
		greetingButton.setOnAction(event -> greetingLabel.setText(String.format("GreetController2: %s %s", prefix, nameTextField.getText())) );
	}

}

