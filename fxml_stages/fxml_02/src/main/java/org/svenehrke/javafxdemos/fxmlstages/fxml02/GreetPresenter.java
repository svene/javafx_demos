package org.svenehrke.javafxdemos.fxmlstages.fxml02;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class GreetPresenter implements Initializable {

	@FXML
	private Button greetingButton;

	@FXML
	private TextField nameTextField;

	@FXML
	private Label greetingLabel;

	@Inject
	private String prefix;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameTextField.setText("Duke");
		greetingButton.setOnAction(event -> greetingLabel.setText(String.format("GreetController2: %s %s", prefix, nameTextField.getText())) );
	}
}
