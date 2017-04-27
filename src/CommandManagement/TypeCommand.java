package CommandManagement;

import Server.SessionCommandsManager;
import Utils.LogUtils;

public class TypeCommand implements Command {

	private SessionCommandsManager scm;
	private LogUtils logger = new LogUtils("TypeCommand");
	
	public TypeCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
	}	
	
	@Override
	public String execute() {
		//Set file type ? 
		// TODO Auto-generated method stub
		return "200 Type set to I";
	}

}
