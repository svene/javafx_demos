package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.util.StringConverter;

import java.util.function.Function;

interface IColumnSpecification {
	String title();
	Function<PersonTableBean, ValidatedString> validatedStringProvider();
	StringConverter<String> convenienceConverter();
	String unformat(String input); // usally used by validation
	String format(String input); // usually used to initially fill column strings from real value. E.g.: BigDecimal bd; format(bd.toString())
	Function<String, ValidationResult> validator();
}
