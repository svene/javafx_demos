package org.svenehrke.javafxdemos.address.commandhandler;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.Main;
import org.svenehrke.javafxdemos.address.Model;
import org.svenehrke.javafxdemos.address.model.PersonListWrapper;
import org.svenehrke.javafxdemos.infra.Mate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.prefs.Preferences;

/**
 * Opens a FileChooser to let the user select an address book to load.
 */
public class OpenFileCommandHandler implements Runnable {

	private final Stage primaryStage;
	private final Model model;

	public OpenFileCommandHandler(Stage primaryStage, Model model) {
		this.primaryStage = primaryStage;
		this.model = model;
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 *
	 * @param file the file or null to remove the path
	 * @param primaryStage1
	 */
	public static void setPersonFilePath(File file, Stage primaryStage1) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			primaryStage1.setTitle("AddressApp - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			primaryStage1.setTitle("AddressApp");
		}
	}

	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 *
	 * @return
	 */
	public static File getPersonFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	@Override
	public void run() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
			"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showOpenDialog(primaryStage);

		if (file != null) {
			loadPersonDataFromFile(file);
		}
	}

	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 *
	 * @param file
	 */
	public void loadPersonDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
			PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

			model.getPersonData().clear();
			model.getPersonData().addAll(wrapper.getPersons());

			// Save the file path to the registry.
			setPersonFilePath(file, primaryStage);

		} catch (Exception e) { // catches ANY exception
			e.printStackTrace();
			Dialogs.create()
				.title("Error")
				.masthead("Could not load data from file:\n" + file.getPath())
				.showException(e);
		}
	}


}
