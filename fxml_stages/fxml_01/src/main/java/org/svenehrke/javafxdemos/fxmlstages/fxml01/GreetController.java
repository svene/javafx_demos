package org.svenehrke.javafxdemos.fxmlstages.fxml01;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GreetController implements Initializable {

	@FXML
	private Button greetingButton;

	@FXML
	private TextField nameTextField;

	@FXML
	private Label greetingLabel;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		nameTextField.setText("Duke");
		greetingButton.setOnAction(event -> greetingLabel.setText("Hello " + nameTextField.getText()) );
	}
}

