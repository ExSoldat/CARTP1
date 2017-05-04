package CommandManagement;

import java.net.Socket;

import Server.SessionCommandsManager;
import Utils.LogUtils;

public class PASVCommand implements Command {

	private SessionCommandsManager scm;
	private LogUtils logger = new LogUtils("PASVCommand");
	
	public PASVCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
	}	
	
	@Override
	public String execute() {
		if (scm.openFileTransferSocket())
			return "227 Entering Passive Mode (127,0,0,1," + formatFileTransferSocket(scm.getFileTransferSocketPort());
		else 
			return new AnswerOnlyCommand(this.scm, "", "540 an error occured while opening the socket").execute();
	}

	private String formatFileTransferSocket(int port) {
		int p1, p2;
		p1 = port/256;
		p2 = port%256;
		return "" + p1 + "," + p2;
	}
}
