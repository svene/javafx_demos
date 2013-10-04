package org.svenehrke.javafxdemos.table.lazyloading;

import java.util.concurrent.Executors;
import java.util.function.Consumer;

class PMProvider {

	public static void withPresentationModel(final int rowIdx, Consumer<FakedPresentationModel> onFinishedHandler) {
		// Simulate 'withPresentationModel' call:
		Executors.newSingleThreadExecutor().execute(() -> {
			// Simulate wait time until onFinishedHandler of 'dolphin.clientModelStore.withPresentationModel' is done:
			try {
				System.out.println("sending withPresentationModel for rowIdx " + rowIdx);
				Thread.sleep(300);
				System.out.println("got PM for rowIdx " + rowIdx);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			FakedPresentationModel pm = new FakedPresentationModel(rowIdx, rowIdx, "first " + rowIdx, "last " + rowIdx);
			onFinishedHandler.accept(pm);
		});
	}
}
