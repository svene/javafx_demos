package org.svenehrke.javafxdemos.greetfxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GreetController {

	@FXML
	Button greetingButton;

	@FXML
	TextField nameTextField;

	@FXML
	Label greetingLabel;
}
