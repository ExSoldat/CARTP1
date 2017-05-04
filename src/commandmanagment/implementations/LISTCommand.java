package commandmanagment.implementations;

import java.io.File;
import java.io.PrintWriter;

import commandmanagment.Command;
import requestprocessing.Response;
import server.FileUtils;
import server.SessionCommandsManager;
import utils.Logger;

/**
 * A command used when the client asks for a list of files in the current directory
 * @author Mathieu
 *
 */
public class LISTCommand implements Command {
	private SessionCommandsManager scm;
	private Response responseManager;
	private Logger logger = new Logger("LISTCommand");
	
	/**
	 * The constructor
	 * @param scm the SessionCommandsManager which originated this command
	 * @param param the parameter sent with this command
	 * @param rManager a response manager in order to send a response as well as data
	 */
	public LISTCommand(SessionCommandsManager scm, String param, Response rManager) {
		this.scm = scm;
		this.responseManager = rManager;
	}	
	
	/**
	 * The execution command that opens a DataTransferThread and sends the files information
	 */
	@Override
	public String execute() {
		//Opening the datatransfer thread
		if(!(scm.getDataTransferThread().getSocket() == null) && !scm.getDataTransferThread().getSocket().isClosed()) {
			responseManager.send("125 Data connection already opened");
		} else {
			if(!scm.openDataTransferThread()) {
				return "530 internal error";
			} else {
				responseManager.send("125 Data connection opened");
			}
		}
		//Sending the file informations
		try {
			for (File f : scm.getCurrentDirFilesList()) {
				String fileDescription = FileUtils.formatFileToAnswer(f);
				scm.getDataTransferThread().send(fileDescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "540 internal error";
		}
		scm.getDataTransferThread().close();
		return "226 Closing data connection";
	}
}
