package commandmanagment.implementations;

import commandmanagment.Command;
import server.SessionCommandsManager;

public class ConnectionCommand implements Command {
	
	private SessionCommandsManager scm;
	
	public ConnectionCommand(SessionCommandsManager scm) {
		this.scm = scm;
	}

	@Override
	public String execute() {
		scm.setIsRunning(true);
		return "220 Service Ready";
	}

}
