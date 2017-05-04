package requestprocessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import commandmanagment.Command;
import server.SessionCommandsManager;
import utils.Constants;
import utils.Logger;

public class RequestManager {
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader bfr;
	private ResponseTriggerer responseTriggerer;
	private Logger logger = new Logger("RequestManager");
	
	public RequestManager(Socket connectionSocket) {
		try {
			is = connectionSocket.getInputStream();
			isr = new InputStreamReader(is);	
			bfr = new BufferedReader(isr);
			responseTriggerer = new ResponseTriggerer(connectionSocket);
		} catch (IOException e) {
			logger.e("An error occured while trying to create input stream utils : ");
			e.printStackTrace();
		}
	}
	
	public void receive(SessionCommandsManager scm) {
		if(scm.isRunning()) { //Previously it was user is connected
			String input;
			try {
				//Ajouter un truc pour que ce soit bloquant ?
				input = bfr.readLine();
				responseTriggerer.triggerResponse(input, scm);
			} catch (IOException e) {
				logger.e("An error occured while trying to read request : ");
				e.printStackTrace();
			}
		} else {
			responseTriggerer.triggerResponse(Command.CMD_CONNECTION, scm);
		}
	}
}