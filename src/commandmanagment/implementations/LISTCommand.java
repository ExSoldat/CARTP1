package commandmanagment.implementations;

import java.io.File;
import java.io.PrintWriter;

import commandmanagment.Command;
import requestprocessing.ResponseManager;
import server.FileUtils;
import server.SessionCommandsManager;
import utils.Logger;

public class LISTCommand implements Command {
	private SessionCommandsManager scm;
	private ResponseManager responseManager;
	private Logger logger = new Logger("ListCommand");
	
	public LISTCommand(SessionCommandsManager scm, String param, ResponseManager rManager) {
		this.scm = scm;
		this.responseManager = rManager;
	}	
	
	@Override
	public String execute() {
		if(!(scm.getDataTransferThread().getSocket() == null) && !scm.getDataTransferThread().getSocket().isClosed()) {
			responseManager.send("125 Data connection already opened");
		} else {
			if(!scm.openDataTransferThread()) {
				return "530 internal error";
			} else {
				responseManager.send("125 Data connection opened");
			}
		}
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
