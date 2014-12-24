package org.svenehrke.javafxdemos.address;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.model.SampleData;
import org.svenehrke.javafxdemos.infra.*;

/**
 * Like 'Main' but the pane is loaded explicitly which allows to configure the controller (GreetController2 in this example) in the code
 * and not in the fxml.
 */
public class Main extends Application {

	private PersonDetailsView personDetailsView;

	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

	private BorderPane rootLayout;
	Model model;

	@Override
	public void init() throws Exception {
	}

	@Override
	public void stop() throws Exception {
	}


	@Override
	public void start(Stage primaryStage) throws Exception {

		model = new Model(primaryStage);
		model.getPeople().addAll(SampleData.getPeople());
		model.getPrimaryStage().setTitle("AddressApp");

		model.getPrimaryStage().getIcons().add(new Image(this.getClass().getResourceAsStream("/Address_Book.png")));

		initRootLayout();
		showPersonOverview();
	}

	private void initRootLayout() {

		// Give the view access to the main app.
		final ViewAndRoot<RootLayoutView, BorderPane> cr = FXMLLoader2.loadFXML("/RootLayout.fxml");
		RootLayoutView view = cr.getView();
		rootLayout = cr.getRoot();

		new RootLayoutViewBinder().bindView(view, model);
		model.getPrimaryStage().titleProperty().bind(model.applicationTitle);

		Scene scene = new Scene(rootLayout);
		model.getPrimaryStage().setScene(scene);
		model.getPrimaryStage().show();
	}

	private void showPersonOverview() {
		final ViewAndRoot<PersonDetailsView, Pane> cr = FXMLLoader2.loadFXML("/PersonDetails.fxml");
		personDetailsView = cr.getView();
		PersonDetailsViewBinder.bindView(personDetailsView, model);
		rootLayout.setCenter(cr.getRoot());
	}

}
