package CommandManagement;

import java.io.File;

import Server.SessionCommandsManager;
import Utils.Constants;
import Utils.LogUtils;

public class DirectoryCommand implements Command {

	private SessionCommandsManager scm;
	private LogUtils logger = new LogUtils("DirectoryCommand");
	
	public DirectoryCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
	}	
	
	@Override
	public String execute() {
		//If the users directory does not exist, then we create one
		File usersDir = new File(Constants.WORK_DIRECTORY + "/" + this.scm.getUser().getUsername().replaceAll("\\s+", "").toLowerCase());
		usersDir.mkdirs();
		scm.setFile(usersDir);
		//After this, we send a response with the path to this directory
		return "257 \"" + usersDir.getAbsolutePath() + "\"";
	}

}
