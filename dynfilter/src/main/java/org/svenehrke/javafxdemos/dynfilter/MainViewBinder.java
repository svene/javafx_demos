package org.svenehrke.javafxdemos.dynfilter;

public class MainViewBinder {

	public void bind(MainView view, Context context) {

		view.allNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		view.allColorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
		view.allItemsTV.setItems(context.allItems);

		view.blueNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		view.blueColorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
		view.blueItemsTV.setItems(context.blueItems);

		view.addBlue.setOnAction(e -> {
			String name = String.valueOf(context.allItems.size() + 1);
			context.allItems.add(new Item(name, Item.BLUE));
		});
		view.addRed.setOnAction(e -> {
			String name = String.valueOf(context.allItems.size() + 1);
			context.allItems.add(new Item(name, Item.RED));
		});

		view.makeBlue.setOnAction(e -> {
			view.allItemsTV.getSelectionModel().getSelectedItems().forEach(item -> item.colorProperty().setValue(Item.BLUE));
		});
		view.makeRed.setOnAction(e -> {
			view.allItemsTV.getSelectionModel().getSelectedItems().forEach(item -> item.colorProperty().setValue(Item.RED));
		});

	}
}
