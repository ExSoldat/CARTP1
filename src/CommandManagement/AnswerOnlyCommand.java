package CommandManagement;

import Server.SessionCommandsManager;
import Utils.LogUtils;

public class AnswerOnlyCommand implements Command {

	private SessionCommandsManager scm;
	private String response;
	private LogUtils logger = new LogUtils("AnswerOnlyResponse");
	
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
