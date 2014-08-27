package org.svenehrke.javafxdemos.table.lazyloading;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.table.tablepopulation.LazyCollections;

/**
 * Lazy Loading Demo3 . As LazyLoadingDemo3 but using JavaFX means (Service) for asynchronous calls instead of 'ExecutorService' (in PMProvider)
 *
 */
public class LazyLoadingDemo3 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle(getClass().getSimpleName());

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));
		pane.setSpacing(10);

		LazyList<FXPerson> lazyPersonList = new LazyList<>(100_000, (idx) -> new FXPerson(idx, -1, "not loaded", "not loaded"), this::loadPresentationModel);
		ObservableList<FXPerson> items = LazyCollections.newObservableList(lazyPersonList);
		final TableView<FXPerson> tableView = tableView(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.getColumns().addAll(firstColumn(), secondColumn());

		Label fillSizeLabel = new Label("0");
		lazyPersonList.fillSizeProperty().addListener((s,o,n) -> fillSizeLabel.setText(String.valueOf(n)));

		pane.getChildren().addAll(tableView, fillSizeLabel);

		Scene scene = new Scene(pane, 300, 500);
		stage.setScene(scene);
		stage.show();
	}

	private TableView<FXPerson> tableView(final ObservableList<FXPerson> items) {
		TableView<FXPerson> tableView = new TableView<>(items);
		return tableView;
	}

	private void loadPresentationModel(int rowIdx, final FXPerson person) {
		if (rowIdx == -1) return;
		System.out.println("loadPresentationModel: rowIdx = " + rowIdx);
		if (person.getLoadState() == LoadState.LOADED) return;
		if (person.getLoadState() == LoadState.LOADING) return;

		// Simulate 'withPresentationModel' call:
		person.setLoadState(LoadState.LOADING);

		LoadService loadService = new LoadService(rowIdx);
		loadService.setOnSucceeded(event -> {
			FakedPresentationModel pm = (FakedPresentationModel) event.getSource().getValue();
			person.setDbId(rowIdx); //pm.dbId
			person.firstNameProperty().bindBidirectional(pm.firstNameProperty());
			person.lastNameProperty().bindBidirectional(pm.lastNameProperty());
			person.setLoadState(LoadState.LOADED);
		});
		loadService.start();

	}

	private TableColumn<FXPerson, String> firstColumn() {
		TableColumn<FXPerson, String> result = new TableColumn<>("first");
		result.setCellValueFactory(param -> param.getValue().firstNameProperty());
		result.setCellFactory(TextFieldTableCell.forTableColumn());
		return result;
	}

	private TableColumn<FXPerson, String> secondColumn() {
		TableColumn<FXPerson, String> result = new TableColumn<>("last");
		result.setCellValueFactory(param -> param.getValue().lastNameProperty());
		return result;
	}

	static class LoadService extends Service<FakedPresentationModel> {
		private final int rowIdx;

		LoadService(final int rowIdx) {
			this.rowIdx = rowIdx;
		}

		@Override
		protected Task<FakedPresentationModel> createTask() {
			return new Task<FakedPresentationModel>() {
				@Override
				protected FakedPresentationModel call() throws Exception {
					Thread.sleep(1000); // Simulate DB-Query time
					FakedPresentationModel pm = new FakedPresentationModel(rowIdx, rowIdx, "first " + rowIdx, "last " + rowIdx);
					return pm;
				}
			};
		}
	}


}

