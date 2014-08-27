package org.svenehrke.javafxdemos.table.customtablecell;

import javafx.application.Platform;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

public class PersonTableCell extends TableCell<Person, String> {

	private final TextField textField = new TextField();

	public PersonTableCell() {
		tableRowProperty().addListener((s,o,n) -> {
			n.heightProperty().addListener((s2,o2,n2) -> {
				System.out.printf("new row (%s) height: %s%n", n.getIndex() , n2);
			});
		});
	}

	@Override
	public void updateIndex(final int i) {
		super.updateIndex(i);
//		System.out.println("PersonTableCell.updateIndex: index: " + i);
	}

	@Override
	protected void updateItem(final String item, final boolean empty) {
		super.updateItem(item, empty);

		textField.setText(item);
		Platform.runLater(() -> setGraphic(textField));
	}
}
