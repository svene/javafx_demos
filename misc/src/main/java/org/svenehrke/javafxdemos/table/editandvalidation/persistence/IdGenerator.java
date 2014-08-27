package org.svenehrke.javafxdemos.table.editandvalidation.persistence;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
	static AtomicLong currentValue = new AtomicLong(0);

	public static long next() {
		return currentValue.incrementAndGet();
	}
}
