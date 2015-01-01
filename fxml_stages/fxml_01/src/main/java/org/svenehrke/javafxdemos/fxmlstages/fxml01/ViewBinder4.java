package org.svenehrke.javafxdemos.fxmlstages.fxml01;

public class ViewBinder4 {

	public void bind(GreetView4 view, Context4 context) {
		view.nameTextField.setText("Duke");
		view.greetingButton.setOnAction(event -> {
			view.greetingLabel.setText(String.format("%s %s", context.prefix, view.nameTextField.getText()));
		} );

	}
}
