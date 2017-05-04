package CommandManagement;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import Server.SessionCommandsManager;
import Utils.LogUtils;

public class ExtendedDataportCommand implements Command {

	
	private SessionCommandsManager scm;
	private LogUtils logger = new LogUtils("ExtendedDataportCommand");
	private int dataport;
	
	public ExtendedDataportCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
		String delimiter = param.substring(param.length()-1);
		String[] parsedDataport = param.split("\\" + delimiter);
		this.dataport = Integer.valueOf(parsedDataport[3]);
	}	
	@Override
	public String execute() {
		if(scm.getDataTransferSocket() !=null) {
			//Retour ok
			return "200 port opened";
		} else {
			if(scm.openFileTransferSocket(dataport)) {
				//Retour ok
				return "200 port opened";
			} else {
				return "501 unable to create file transfert socket";
			}
		}		
	}
}
