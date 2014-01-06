package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.StringProperty;

public class Person {

	private final BetterStringProperty name1 = new BetterStringProperty();
	private final BetterStringProperty name2 = new BetterStringProperty();
	private final BetterStringProperty name3 = new BetterStringProperty();
	private final BetterStringProperty name4 = new BetterStringProperty();
	private double height = 0.0;

	public Person(final int rowIdx) {

		name1.setValue(valueFrom(1, rowIdx));
		name2.setValue(valueFrom(2, rowIdx));
		name3.setValue(valueFrom(3, rowIdx));
		name4.setValue(valueFrom(4, rowIdx));

		name1Property().addListener((s,o,n) -> {
			System.out.printf("name1 change: %s->%s%n", o, n);
		});
	}

	public static boolean isLongText(final String item) {
		return item != null && item.length() > LONG_TEXT_ARRAY[1].length();
	}

	public StringProperty name1Property() {
		return name1;
	}

	public StringProperty name2Property() {
		return name2;
	}

	public StringProperty name3Property() {
		return name3;
	}

	public StringProperty name4Property() {
		return name4;
	}

	private String valueFrom(final int colIdx, final int rowIdx) {
//		if (colIdx == 3) {
			int textIdx = rowIdx % 3;
			return LONG_TEXT_ARRAY[textIdx];
//		}
//		return LONG_TEXT_ARRAY[0];
	}

	private static String[] LONG_TEXT_ARRAY = new String[]{
		wrappedHtml("{0} Dies ist ein nicht so langer Text"),
		wrappedHtml("{0} Dies ist ein mittellanger Text, der schon ein ziemlich blinder Blindtext ist, und nichts bedeutet. Damit er auch sicher einmal umgebrochen wird, müssen wir noch etwas labern"),
		wrappedHtml("{0} Dies ist ein ganz, ganz, ganz langer Text, der schon ein ziemlich blinder Blindtext ist. Dieser Text hat keine inhaltliche Aussage. Dieser ist noch vieeeel länger, als der andere und wird deswegen sicher umgebrochen. Da das Fenster sehr breit ist, müssen wir noch einen viel längeren Text schreiben. Eigentlich wollte ich ja, dass er zweimal umgebrochen wird, schaun wir mal ob es reicht")
	};

	private static String wrappedHtml(final String htmlSnippet) {
		return "<html><body style='background-color: red'><div id='bodyDivId'>" + htmlSnippet + "</div></body></html>";
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(final double height) {
		this.height = height;
	}
}
