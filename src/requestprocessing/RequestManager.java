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

/**
 * The request manager is the object that will handle every requests sent by the client side
 * @author Mathieu
 *
 */
public class RequestManager {
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader bfr;
	private ResponseTriggerer responseTriggerer;
	private Logger logger = new Logger("RequestManager");
	
	/**
	 * The constructor
	 * @param connectionSocket the socket on which the request mnager should be listening on
	 */
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
	
	/**
	 * A function that is used to receive a request and sends it to a response triggerer which will handle the command to create
	 * @param scm the original scm
	 */
	public void receive(SessionCommandsManager scm) {
		if(scm.isRunning()) {
			//If the connection is already running then we can read the requests and ask to a responsetriggerer to screate a command.
			String input;
			try {
				input = bfr.readLine();
				responseTriggerer.triggerResponse(input, scm);
			} catch (IOException e) {
				logger.e("An error occured while trying to read request : ");
				e.printStackTrace();
			}
		} else {
			//If the session is not running yet, then we ask for a connection.
			responseTriggerer.triggerResponse(Command.CMD_CONNECTION, scm);
		}
	}
}
