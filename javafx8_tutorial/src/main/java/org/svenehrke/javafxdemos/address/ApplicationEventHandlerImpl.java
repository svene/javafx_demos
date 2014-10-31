package org.svenehrke.javafxdemos.address;

public class ApplicationEventHandlerImpl implements IApplicationEventHandler {

	private final Model model;

	public ApplicationEventHandlerImpl(Model model) {
		this.model = model;
	}

	@Override
	public void handleCommand(String commandName) {
		switch (commandName) {
			case Api.CMD_NEW:
				System.out.println("new");
				break;
			case Api.CMD_EDIT:
				System.out.println("edit");
				break;
			case Api.CMD_DELETE:
				System.out.println("delete");
				break;
			default:
				System.out.println("command '" + commandName + "' unknown");
				break;
		}
	}
}
