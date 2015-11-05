package org.svenehrke.javafxdemos.greetfxml;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GreetController {

	@FXML
	Button greetingButton;

	@FXML
	TextField nameTextField;

	@FXML
	Label greetingLabel;
}
