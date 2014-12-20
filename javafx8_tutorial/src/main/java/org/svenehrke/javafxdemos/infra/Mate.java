package org.svenehrke.javafxdemos.infra;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Mate {

	private Map<String, Runnable> commands = new HashMap<>();

	public void addCommand(String commandId, Runnable command) {
		commands.put(commandId, command);
	}

	public Optional<Runnable> getCommand(String commandId) {
		return Optional.ofNullable(commands.get(commandId));
	}

	public void handleCommand(String commandId) {
		Optional<Runnable> optional = getCommand(commandId);
		if (optional.isPresent()) {
			optional.get().run();
		}
	}
}
