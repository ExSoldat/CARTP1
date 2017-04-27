package CommandManagement;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import Server.SessionCommandsManager;
import Utils.LogUtils;

public class ExtendedDataportCommand implements Command {

	
	private SessionCommandsManager scm;
	private LogUtils logger = new LogUtils("ExtendedDataportCommand");
	private Socket dataport;
	
	public ExtendedDataportCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
		createSocketFromInput(param);
	}	
	@Override
	public String execute() {
		if(dataport != null) {
			scm.setDataport(dataport);
			return "200 port opened";
		}
		return "501 unable to create file transfert socket";
	}
	
	public void createSocketFromInput(String input) {
		String delimiter = input.substring(input.length()-1);
		String[] parsedDataport = input.split("\\" + delimiter);
		int p;
		logger.i("delimiter : " + delimiter + "\n0 : " + parsedDataport[0] + "\n1 :" + parsedDataport[1] + "\n2 :" + parsedDataport[2] + "\n3 : " + parsedDataport[3]);
		try {
			p = Integer.valueOf(parsedDataport[3]);
			dataport = new Socket(parsedDataport[2], p);
		} catch (Exception e) {
			dataport = null;
		}			
	}

}
