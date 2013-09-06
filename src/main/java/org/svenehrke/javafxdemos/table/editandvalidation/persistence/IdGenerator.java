package org.svenehrke.javafxdemos.table.editandvalidation.persistence;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
	static AtomicInteger currentValue = new AtomicInteger(0);

	static int next() {
		return currentValue.incrementAndGet();
	}
}
