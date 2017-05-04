package CommandManagement;

import Server.SessionCommandsManager;
import Utils.LogUtils;

public class EPSVCommand implements Command {

	private SessionCommandsManager scm;
	private LogUtils logger = new LogUtils("EPSVCommand");
	
	public EPSVCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
	}	
	
	@Override
	public String execute() {
		return "200 Command ok";
	}
}