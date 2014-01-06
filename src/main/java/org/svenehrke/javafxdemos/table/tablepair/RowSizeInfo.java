package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class RowSizeInfo {

	private final DoubleProperty rowSize1 = new SimpleDoubleProperty(0);
	private final DoubleProperty rowSize2 = new SimpleDoubleProperty(0);
//	private final DoubleProperty rowSize = new SimpleDoubleProperty(1);
//	private final NumberBinding rowSize;

	public RowSizeInfo(final TableViewState tableViewState, final int rowIndex) {

//		rowSize = Bindings.max(rowSize1, rowSize2);

		if (Util.isDebugIndex(rowIndex)) {
			rowSize1.addListener((s, o, n) -> {
				System.out.printf("RowSizeInfo: Left, row %s: %s -> %s%n", rowIndex, o, n);
			});
			rowSize2.addListener((s,o,n) -> {
				System.out.printf("RowSizeInfo: Right, row %s: %s -> %s%n", rowIndex, o, n);
			});
		}

		rowSize1.addListener((s,o,n) -> {
			if (rowIndex < 0 || rowIndex >= tableViewState.getItems().size()) return;
			if (n.intValue() <= 0) {
				return;
			}
			Person person = tableViewState.getItems().get(rowIndex);
			Fireable f = (Fireable) person.name1Property();
			f.fireValueChangedEvent();
//			Platform.runLater(() -> rowSize1.setValue(-1));
		});


	}

	public DoubleProperty rowSize1Property() {
		return rowSize1;
	}
	public DoubleProperty rowSize2Property() {
		return rowSize2;
	}

}
