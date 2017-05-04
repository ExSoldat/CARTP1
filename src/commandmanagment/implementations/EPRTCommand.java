package commandmanagment.implementations;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import commandmanagment.Command;
import server.SessionCommandsManager;
import utils.Logger;

public class EPRTCommand implements Command {

	
	private SessionCommandsManager scm;
	private Logger logger = new Logger("EPRTCommand");
	private int dataport;
	
	public EPRTCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
		String delimiter = param.substring(param.length()-1);
		String[] parsedDataport = param.split("\\" + delimiter);
		this.dataport = Integer.valueOf(parsedDataport[3]);
	}	
	@Override
	public String execute() {/*
		if(scm.getDataTransferThread() !=null) {
			//Retour ok
			return "200 port opened";
		} else {
			if(scm.openFileTransferSocket(dataport)) {
				//Retour ok
				return "200 port opened";
			} else {
				return "501 unable to create file transfert socket";
			}
		}	*/
		return "500 unhandled";
	}
}
