package org.svenehrke.javafxdemos.table.tablepair;

public class Util {
	/**  from Node.almostZero(...) */
	private static final double EPSILON_ABSOLUTE = 1.0e-5;

	/**  from Node.almostZero(...) */
	private static boolean almostZero(double a) {
		return ((a < EPSILON_ABSOLUTE) && (a > -EPSILON_ABSOLUTE));
	}

	static boolean almostEqual(double a, double b) {
		return almostZero(a - b);
	}

	public static boolean isDebugIndex(int index) {
		return ((index + 1)  % 3) == 0;
	}

}
