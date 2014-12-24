package org.svenehrke.javafxdemos.address.util;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ZipRoutines {

	public static <A,B,C> Iterable<C> zipWith(Iterable<A> iterableA, Iterable<B> iterableB, BiFunction<A,B,C> fn) {
		return () -> new Iterator<C>() {
			private Iterator<A> itA = iterableA.iterator();
			private Iterator<B> itB = iterableB.iterator();

			public boolean hasNext() {
				return itA.hasNext() && itB.hasNext();
			}

			public C next() {
				return fn.apply(itA.next(), itB.next());
			}
		};
	}


	public static<A, B, C> Stream<C> zip(Stream<? extends A> a,
		Stream<? extends B> b,
		BiFunction<? super A, ? super B, ? extends C> zipper) {
		Objects.requireNonNull(zipper);
		@SuppressWarnings("unchecked")
		Spliterator<A> aSpliterator = (Spliterator<A>) Objects.requireNonNull(a).spliterator();
		@SuppressWarnings("unchecked")
		Spliterator<B> bSpliterator = (Spliterator<B>) Objects.requireNonNull(b).spliterator();

		// Zipping looses DISTINCT and SORTED characteristics
		int both = aSpliterator.characteristics() & bSpliterator.characteristics() &
			~(Spliterator.DISTINCT | Spliterator.SORTED);
		int characteristics = both;

		long zipSize = ((characteristics & Spliterator.SIZED) != 0)
			? Math.min(aSpliterator.getExactSizeIfKnown(), bSpliterator.getExactSizeIfKnown())
			: -1;

		Iterator<A> aIterator = Spliterators.iterator(aSpliterator);
		Iterator<B> bIterator = Spliterators.iterator(bSpliterator);
		Iterator<C> cIterator = new Iterator<C>() {
			@Override
			public boolean hasNext() {
				return aIterator.hasNext() && bIterator.hasNext();
			}

			@Override
			public C next() {
				return zipper.apply(aIterator.next(), bIterator.next());
			}
		};

		Spliterator<C> split = Spliterators.spliterator(cIterator, zipSize, characteristics);
		return (a.isParallel() || b.isParallel())
			? StreamSupport.stream(split, true)
			: StreamSupport.stream(split, false);
	}

}
