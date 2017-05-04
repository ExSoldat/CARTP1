package commandmanagment.implementations;

import commandmanagment.Command;
import server.SessionCommandsManager;
import utils.Logger;

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
