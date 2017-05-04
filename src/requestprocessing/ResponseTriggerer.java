package requestprocessing;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import commandmanagment.Command;
import commandmanagment.CommandFactory;
import server.SessionCommandsManager;
import utils.Constants;
import utils.Logger;
/**
 * A class that will create commands to handle a request and send a response after executing a command
 * @author Mathieu
 *
 */
public class ResponseTriggerer {
	private List<String> validCommands = new ArrayList<String>();
	private Socket connectionSocket;
	private Logger logger = new Logger("ResponseTriggerer");
	private Response response;
	private CommandFactory cf = new CommandFactory();
	
	/**
	 * The constructor that create object such as the response manager
	 * @param connectionSocket
	 */
	public ResponseTriggerer(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
		this.response = new Response(connectionSocket);
		validCommands = Command.getCommandsList();
		
	}

	/**
	 * A function used to create commands and handle their results
	 * @param input the received input (command + eventual parameters)
	 * @param scm the original scm
	 */
	public void triggerResponse(String input, SessionCommandsManager scm) {
		//Firstly, we separate the command from the parameters
		String[] splittedInput = input.split(" ");
		String command = splittedInput[0];
		String param = "";
		if(splittedInput.length == 2)
			param = splittedInput[1];
		if(!validCommands.contains(command)) {
			command = "";
			param = "";
		}
		logger.i("Command received : " + command);
		logger.i("Param received : " + param);
		//We create a command that represents the command we should handle
		Command c = cf.createCommand(scm, command, param, response);
		//The command will be executed and send us a response code and an eventual message
		String sresponse = c.execute();
		// We send this response back to client side using the Response class
		response.send(sresponse);
	}

}
