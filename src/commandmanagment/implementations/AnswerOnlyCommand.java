package commandmanagment.implementations;

import commandmanagment.Command;
import server.SessionCommandsManager;
import utils.Logger;

/***
 * Command used when nothing but an answer has to be sent back to the client side
 * @author Mathieu
 *
 */
public class AnswerOnlyCommand implements Command {

	private SessionCommandsManager scm;
	private String response;
	private Logger logger = new Logger("AnswerOnlyCommand");
	
	public AnswerOnlyCommand(SessionCommandsManager scm, String param, String response) {
		this.scm = scm;
		this.response = response;
	}	
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return this.response;
	}

}
