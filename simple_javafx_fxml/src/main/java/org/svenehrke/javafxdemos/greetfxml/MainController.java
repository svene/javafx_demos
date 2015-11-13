package org.svenehrke.javafxdemos.greetfxml;

import javafx.fxml.FXML;

public class MainController {

	@FXML
	GreetController greetController; // NOTE: JavaFX will bind this via fx:id="greet" in 'main.fxml'

}
