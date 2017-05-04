package commandmanagment.implementations;

import java.io.File;

import commandmanagment.Command;
import server.FileUtils;
import server.SessionCommandsManager;
import utils.Constants;
import utils.Logger;

public class PWDCommand implements Command {

	private SessionCommandsManager scm;
	private Logger logger = new Logger("PWDCommand");
	
	/**
	 * A command that is triggered when the user wants to get a working directory
	 * @param scm 
	 * @param param
	 */
	public PWDCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
	}	
	
	/**
	 * The execution function
	 * Creating the file if it does not already exists.
	 * TODO Send only "/" and store the current directory on the server side
	 */
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
