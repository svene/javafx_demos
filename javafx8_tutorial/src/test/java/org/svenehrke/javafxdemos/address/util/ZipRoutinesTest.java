package org.svenehrke.javafxdemos.address.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import static org.svenehrke.javafxdemos.address.util.ZipRoutines.*;

public class ZipRoutinesTest {

	@Test
	public void testZipWith() throws Exception {
		Stream<String> strings = Arrays.asList("a", "b", "c").stream();
		Stream<Integer> ints = Arrays.asList(6,9,14,32).stream();

		String result = zip(strings, ints, (s, i) -> s + String.valueOf(i)).collect(joining("-"));

		assertEquals("a6-b9-c14", result);

	}

	@Test
	public void testZip() throws Exception {
		List<String> strings = Arrays.asList("a","b","c");
		List<Integer> ints = Arrays.asList(6, 9, 14, 32);
		Iterable<String> result = zipWith(strings, ints, (s, i) -> s + i);
		String stringResult = StreamSupport.stream(result.spliterator(), false).collect(joining("-"));
		assertEquals("a6-b9-c14", stringResult);
	}
}