package commandmanagment.implementations;

import java.net.Socket;

import commandmanagment.Command;
import server.SessionCommandsManager;
import utils.Logger;

public class PASVCommand implements Command {

	private SessionCommandsManager scm;
	private Logger logger = new Logger("PASVCommand");
	
	public PASVCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
	}	
	
	@Override
	public String execute() {
		if (scm.openDataTransferThread())
			return "227 Entering Passive Mode (127,0,0,1," + formatFileTransferSocket(scm.getDataTransferSocketPort());
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
