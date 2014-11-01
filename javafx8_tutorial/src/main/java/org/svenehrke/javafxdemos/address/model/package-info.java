@XmlJavaTypeAdapters({
	@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
}) package org.svenehrke.javafxdemos.address.model;

import org.svenehrke.javafxdemos.address.util.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.time.LocalDate;

