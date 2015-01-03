package org.svenehrke.javafxdemos.dynfilter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Context context = new Context();

		FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/mainContent.fxml"));
		Pane pane = loader.load();
		MainView view = loader.getController();
		new MainViewBinder().bind(view, context);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Observable List with Extractor");
		primaryStage.show();

	}
}
