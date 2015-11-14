package org.svenehrke.fxmlguis.guis.greet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GreetController {

	@FXML
	public Button greetingButton;

	@FXML
	public TextField nameTextField;

	@FXML
	public Label greetingLabel;
}
