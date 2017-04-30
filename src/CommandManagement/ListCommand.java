package CommandManagement;

import java.io.File;
import java.io.PrintWriter;

import RequestProcessing.ResponseManager;
import Server.ServerFile;
import Server.SessionCommandsManager;
import Utils.LogUtils;

public class ListCommand implements Command {
	private SessionCommandsManager scm;
	private ResponseManager responseManager;
	private LogUtils logger = new LogUtils("ListCommand");
	
	public ListCommand(SessionCommandsManager scm, String param, ResponseManager rManager) {
		this.scm = scm;
		this.responseManager = rManager;
		PrintWriter emitter;
	}	
	
	@Override
	public String execute() {
		if(!(scm.getDataTransferSocket().getSocket() == null) && !scm.getDataTransferSocket().getSocket().isClosed()) {
			responseManager.send("125 Data connection already opened");
		} else {
			//Open a new connection and proceed
			//TODO refactor all this part
			if(!scm.openFileTransferSocket()) {
				return "530 internal error";
			} else {
				responseManager.send("125 Data connection opened");
			}
		}
		try {
			for (File f : scm.getCurrentDirFileList()) {
				String fileDescription = ServerFile.formatFileToAnswer(f);
				scm.getDataTransferSocket().send(fileDescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "540 internal error";
		}
		scm.getDataTransferSocket().close();
		return "226 Closing data connection";
	}
	
	//http://stackoverflow.com/questions/26980284/implementing-own-ftp-server-response-on-list-command
}
