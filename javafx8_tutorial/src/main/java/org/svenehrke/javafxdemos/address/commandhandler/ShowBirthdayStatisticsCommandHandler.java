package org.svenehrke.javafxdemos.address.commandhandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.BirthdayStatisticsController;
import org.svenehrke.javafxdemos.address.Main;
import org.svenehrke.javafxdemos.address.Model;

import java.io.IOException;
import java.net.URL;

public class ShowBirthdayStatisticsCommandHandler implements Runnable {

	private final Stage primaryStage;
	private final Model model;

	public ShowBirthdayStatisticsCommandHandler(Stage primaryStage, Model model) {
		this.primaryStage = primaryStage;
		this.model = model;
	}

	@Override
	public void run() {
		showBirthdayStatistics(primaryStage, model);
	}

	/**
	 * Opens a dialog to show birthday statistics.
	 * @param primaryStage1
	 * @param model1
	 */
	public static void showBirthdayStatistics(Stage primaryStage1, Model model1) {
		try {
			// Load the fxml file and create a new stage for the popup.
			URL resource = Main.class.getResource("/BirthdayStatistics.fxml");
			final FXMLLoader loader = new FXMLLoader(resource, null);
			AnchorPane page = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Birthday Statistics");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage1);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the persons into the controller.
			BirthdayStatisticsController controller = loader.getController();
			controller.setPersonData(model1.getPersonData());

			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
