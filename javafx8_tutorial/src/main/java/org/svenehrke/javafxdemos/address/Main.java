package org.svenehrke.javafxdemos.address;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.model.PersonListWrapper;
import org.svenehrke.javafxdemos.infra.Mate;
import org.svenehrke.javafxdemos.infra.OpenFileCommandHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.prefs.Preferences;

/**
 * Like 'Main' but the pane is loaded explicitly which allows to configure the controller (GreetController2 in this example) in the code
 * and not in the fxml.
 */
public class Main extends Application {

	private PersonOverviewController personOverviewController;

	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

	Stage primaryStage;
	private BorderPane rootLayout;
	private IApplicationEventHandler applicationEventHandler;
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

		mate.addCommand(Api.OPEN_FILE, new OpenFileCommandHandler(mate, primaryStage, model));

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");

		this.primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/Address_Book.png")));

		initRootLayout();
		showPersonOverview();

		applicationEventHandler = new ApplicationEventHandlerImpl(model, primaryStage, personOverviewController);
		personOverviewController.setApplicationEventHandler(applicationEventHandler);
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
		controller.setMainApp(this, mate);

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
		URL resource = Main.class.getResource("/PersonOverview.fxml");
		final FXMLLoader loader = new FXMLLoader(resource, null);
		AnchorPane personOverview;
		try {
			personOverview = loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		personOverviewController = loader.getController();
		personOverviewController.setModel(model);
		rootLayout.setCenter(personOverview);

	}

	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 *
	 * @return
	 */
	public File getPersonFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * Saves the current person data to the specified file.
	 *
	 * @param file
	 */
	public void savePersonDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext
				.newInstance(PersonListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			PersonListWrapper wrapper = new PersonListWrapper();
			wrapper.setPersons(model.getPersonData());

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			OpenFileCommandHandler.setPersonFilePath(file, primaryStage);
		} catch (Exception e) { // catches ANY exception
			e.printStackTrace();
			Dialogs.create().title("Error")
				.masthead("Could not save data to file:\n" + file.getPath())
				.showException(e);
		}
	}

	/**
	 * Opens a dialog to show birthday statistics.
	 */
	public void showBirthdayStatistics() {
		try {
			// Load the fxml file and create a new stage for the popup.
			URL resource = Main.class.getResource("/BirthdayStatistics.fxml");
			final FXMLLoader loader = new FXMLLoader(resource, null);
			AnchorPane page = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Birthday Statistics");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the persons into the controller.
			BirthdayStatisticsController controller = loader.getController();
			controller.setPersonData(model.getPersonData());

			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
