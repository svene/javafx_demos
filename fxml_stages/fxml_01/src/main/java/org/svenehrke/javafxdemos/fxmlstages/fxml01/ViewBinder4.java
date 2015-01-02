package org.svenehrke.javafxdemos.fxmlstages.fxml01;

public class ViewBinder4 {

	public void bind(GreetView4 view, Context4 context) {
		view.nameTextField.textProperty().bindBidirectional(context.name);
		view.greetingLabel.textProperty().bind(context.greeting);
		view.greetingButton.setOnAction(event -> {
			context.greeting.setValue(String.format("%s %s", context.prefix.getValue(), context.name.getValue()));
		} );
	}
}
