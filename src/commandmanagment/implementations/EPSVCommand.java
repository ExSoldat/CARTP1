package commandmanagment.implementations;

import commandmanagment.Command;
import server.SessionCommandsManager;
import utils.Logger;

/**
 * Unimplemented
 * @author Mathieu
 *
 */
public class EPSVCommand implements Command {

	private SessionCommandsManager scm;
	private Logger logger = new Logger("EPSVCommand");
	
	public EPSVCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
	}	
	
	@Override
	public String execute() {
		return "200 Command ok";
	}
}