package org.svenehrke.javafxdemos.table.tablepair;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;

class WebViewTableCell extends TableCell<Person, String> {

	private final Rectangle smallRect = new Rectangle(100, 30);
	private final Rectangle bigRect = new Rectangle(100, 60);

	private final TextField textField = new TextField();
	private final TextArea textArea = new TextArea();

	private final HBox hBox = new HBox();
	private final WebView webview;

	WebViewTableCell(TableViewState tableViewState) {
		smallRect.setFill(Color.GREEN);
		bigRect.setFill(Color.RED);

		textArea.setWrapText(true);

		hBox.getChildren().addAll(textField);

		webview = new WebView();
		hideScrollBarsOfWebview();
		webview.setMouseTransparent(true);

		webview.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> adjustHeight());


		indexProperty().addListener((s,o,n) -> {
			int idx = n.intValue();
			if (idx < 0 || idx >= getTableView().getItems().size()) return;

			System.out.printf("getIndex() = %s -> %s%n", o, n);
			tableViewState.put(idx, getTableColumn().getId(), this);

		});

		tableRowProperty().addListener((s,o,n) -> {

		});

	}

	@Override
	protected void updateItem(final String item, final boolean empty) {
		super.updateItem(item, empty);

		System.out.printf("PersonTableCell.updateItem: col: %s, idx: %s, item: %s (%s)%n", getTableColumn().getId(), getIndex(), item, (item == null ? "-" : item.length()));

		if (item == null) return;
		if (getIndex() == -1) return;
		if (getTableRow() == null) return;

		Person p = getTableView().getItems().get(getIndex());

//		boolean longText = p.getRowItemInfo().getProperties().get(Person.ROW_HEIGHT).get();

//		boolean longText = p.getRowItemInfo().getProperties().get(Person.LONG_TEXT).get();
//		System.out.printf("  longText: %s%n", longText);

/*
		textField.setText(item);
		textArea.setText(item);
		Platform.runLater(() -> {
			hBox.setPrefHeight(longText ? 60 : 30);
			setGraphic(hBox);
		});
*/

		webview.getEngine().loadContent(item);


		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}

	private void hideScrollBarsOfWebview() {
		webview.getChildrenUnmodifiable().addListener(
			(ListChangeListener<Node>) c -> webview.lookupAll(".scroll-bar").forEach((n) -> n.setVisible(false))
		);
	}

	private void adjustHeight() {
		if (getIndex() == -1) return;
		// Adapt height of 'webview' to the height of the rendered HTML:
		Object result = getHtmlHeight();
		if (result instanceof Integer) {
			Integer i = (Integer) result;

			double height = (double) i + 20;
			height = Math.max(60, height);
			setPrefHeight(height);
		}
	}

	private Object getHtmlHeight() {
		try {
			return getHtmlHeight_();
		}
		catch (JSException jse) {
			jse.printStackTrace();
			return null;
		}
	}
	private Object getHtmlHeight_() {
		return webview.getEngine().executeScript("document.getElementById('bodyDivId').offsetHeight");
//		return webview.getEngine().executeScript("document.body.offsetHeight");
	}

}
