package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class PersonTableCell extends TableCell<Person, String> {

	private final TextField textField = new TextField();
	private final TextArea textArea = new TextArea();
	private final Rectangle rect = new Rectangle();

	private final HBox hBox = new HBox();
	private final TableViewState tableViewState;

	PersonTableCell(TableViewState tableViewState) {
		this.tableViewState = tableViewState;

		textArea.setWrapText(true);
		rect.setFill(Color.RED);
		rect.setWidth(2);
		rect.setHeight(2);
		hBox.getChildren().addAll(rect, textField);

		indexProperty().addListener((s,o,n) -> {
			int idx = n.intValue();
			if (idx < 0 || idx >= getTableView().getItems().size()) return;
			tableViewState.put(idx, getTableColumn().getId(), this);
		});
	}

	@Override
	protected void updateItem(final String item, final boolean empty) {
		super.updateItem(item, empty);

		if (item == null) return;
		if (getIndex() == -1) return;

		textField.setText(getIndex() + " " + item);
		setGraphic(hBox);
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}


}
