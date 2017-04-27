package CommandManagement;

import RequestProcessing.ResponseManager;
import Server.SessionCommandsManager;
import Utils.LogUtils;

public class ListCommand implements Command {
	private SessionCommandsManager scm;
	private ResponseManager responseManager;
	private LogUtils logger = new LogUtils("ListCommand");
	
	public ListCommand(SessionCommandsManager scm, String param, ResponseManager rManager) {
		this.scm = scm;
		this.responseManager = rManager;
	}	
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		if(scm.getFileTransferSocket().isConnected()) {
			responseManager.send("125 Data connection already opened");
			responseManager.send("-rwxr-xr-x 1 100 100 14757 a.out");
		}
		return "226 Closing data connection";
	}
	
	//http://stackoverflow.com/questions/26980284/implementing-own-ftp-server-response-on-list-command
}
