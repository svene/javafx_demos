package org.svenehrke.javafxdemos.fxmlstages.fxml02;

import com.airhacks.afterburner.injection.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class App extends Application {

	public static void main(String[] args) {
		Application.launch(App.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Map<Object, Object> customProperties = new HashMap<>();
		customProperties.put("date", null);

		Injector.setConfigurationSource(customProperties::get);

		GreetView view = new GreetView();

		Scene scene = new Scene(view.getView());
		stage.setTitle("Greet with afterburner.fx");
		String uri = getClass().getResource("/app.css").toExternalForm();
		scene.getStylesheets().add(uri);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		Injector.forgetAll();
	}

}
