package org.svenehrke.javafxtemplates.simple;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SnapshotResult;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TakeSnapshotDemo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox pane = new VBox();
		pane.setPadding(new Insets(10));

		Button button = new Button("Take Snapshot");
		button.setOnAction(event -> {

			WritableImage wim = new WritableImage(300, 500);
			stage.getScene().snapshot(new Callback<SnapshotResult, Void>() {
				@Override
				public Void call(final SnapshotResult param) {
					try {
						ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", new File("javafxsnapshot.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}
			}, wim);

		});

		pane.getChildren().addAll(button);

		Scene scene = new Scene(pane, 300, 500, Color.DODGERBLUE);
		stage.setScene(scene);
		stage.setTitle("Take Snapshot Demo");
		stage.show();
	}
}
