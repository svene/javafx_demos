package org.svenehrke.javafxdemos.address;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.commandhandler.*;
import org.svenehrke.javafxdemos.infra.*;

import java.io.IOException;
import java.net.URL;

/**
 * Like 'Main' but the pane is loaded explicitly which allows to configure the controller (GreetController2 in this example) in the code
 * and not in the fxml.
 */
public class Main extends Application {

	private PersonDetailsView personDetailsView;

	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

	Stage primaryStage;
	private BorderPane rootLayout;
	Model model;
	Mate mate;

	@Override
	public void init() throws Exception {
		model = new Model();
		mate = new Mate();
	}

	@Override
	public void stop() throws Exception {
	}


	@Override
	public void start(Stage primaryStage) throws Exception {

		registerCommands(primaryStage);

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");

		this.primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/Address_Book.png")));

		initRootLayout();
		showPersonOverview();
	}

	private void registerCommands(Stage primaryStage) {
		mate.addCommand(Api.OPEN_FILE, new OpenFileCommandHandler(primaryStage, model));
		mate.addCommand(Api.NEW_ADDRESS_BOOK, new NewAddressbookCommandHandler(primaryStage, model));
		mate.addCommand(Api.SAVE, new SaveCommandHandler(primaryStage, model));
		mate.addCommand(Api.SAVE_AS, new SaveAsCommandHandler(primaryStage, model));
		mate.addCommand(Api.STATISTICS, new ShowBirthdayStatisticsCommandHandler(primaryStage, model));

		mate.setModel(model);
		mate.setPrimaryStage(primaryStage);
	}

	private void initRootLayout() {
		URL resource = Main.class.getResource("/RootLayout.fxml");
		final FXMLLoader loader = new FXMLLoader(resource, null);
		try {
			rootLayout = loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// Give the controller access to the main app.
		RootLayoutController controller = loader.getController();
		controller.setMainApp(mate);

		// Try to load last opened person file.
//		File file = getPersonFilePath();
//		if (file != null) {
//			loadPersonDataFromFile(file);
//		}

		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void showPersonOverview() {
		URL resource = Main.class.getResource("/PersonDetails.fxml");
		final FXMLLoader loader = new FXMLLoader(resource, null);
		loader.setControllerFactory((Class<?> c) -> {
			PersonDetailsView controller = new PersonDetailsView();
			return controller;
		});
		AnchorPane personOverview;
		try {
			personOverview = loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		personDetailsView = loader.getController();
		PersonDetailsViewBinder.bindController(personDetailsView, mate);
		rootLayout.setCenter(personOverview);

	}

}
