package commandmanagment.implementations;

import java.net.Socket;

import commandmanagment.Command;
import server.SessionCommandsManager;
import utils.Logger;

/**
 * A command triggered when the client wants to perform a Passive mode transaction
 * @author Mathieu
 *
 */
public class PASVCommand implements Command {

	private SessionCommandsManager scm;
	private Logger logger = new Logger("PASVCommand");
	/**
	 * The constructor
	 * @param scm the origins SessionCommandsManager
	 * @param param not needed here
	 */
	public PASVCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
	}	
	
	/**
	 * The execution function
	 * Opens a DataTransferThread
	 * Sends back to the client side the port we are listening on
	 * Or an error if there was any problems
	 */
	@Override
	public String execute() {
		if (scm.openDataTransferThread())
			return "227 Entering Passive Mode (127,0,0,1," + formatFileTransferSocket(scm.getDataTransferSocketPort()); //Sending the informations to the client side
		else 
			return new AnswerOnlyCommand(this.scm, "", "540 an error occured while opening the socket").execute(); //Executing the error sending
	}

	/**
	 * A function used to format the port in order to match with the asked formatting
	 * @param port the port we want to format
	 * @return the String representing the port to send in the response
	 */
	private String formatFileTransferSocket(int port) {
		int p1, p2;
		p1 = port/256;
		p2 = port%256;
		return "" + p1 + "," + p2;
	}
}
