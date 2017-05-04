package commandmanagment.implementations;

import java.io.File;

import commandmanagment.Command;
import server.FileUtils;
import server.SessionCommandsManager;
import utils.Constants;
import utils.Logger;

public class PWDCommand implements Command {

	private SessionCommandsManager scm;
	private Logger logger = new Logger("DirectoryCommand");
	
	public PWDCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
	}	
	
	@Override
	public String execute() {
		//If the users directory does not exist, then we create one
		File usersDir = new File(Constants.WORK_DIRECTORY + "/" + this.scm.getUser().getUsername().replaceAll("\\s+", "").toLowerCase());
		usersDir.mkdirs();
		scm.setCurrentDirectory(usersDir);
		//After this, we send a response with the path to this directory
		return "257 \"" + usersDir.getAbsolutePath() + "\"";
	}

}
