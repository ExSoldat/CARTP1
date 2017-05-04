package RequestProcessing;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import CommandManagement.Command;
import CommandManagement.CommandFactory;
import Server.SessionCommandsManager;
import Utils.Constants;
import Utils.LogUtils;

public class ResponseTriggerer {
	private List<String> validCommands = new ArrayList<String>();
	private Socket connectionSocket;
	private LogUtils logger = new LogUtils("ResponseTriggerer");
	private ResponseManager responseManager;
	private CommandFactory cf = new CommandFactory();
	
	public ResponseTriggerer(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
		this.responseManager = new ResponseManager(connectionSocket);
		validCommands = Command.getCommandsList();
		
	}

	public void triggerResponse(String input, SessionCommandsManager scm) {
		String[] splittedInput = input.split(" ");
		String command = splittedInput[0];
		String param = "";
		if(splittedInput.length == 2)
			param = splittedInput[1];
		if(!validCommands.contains(command)) {
			command = "";
			param = "";
		}
		//Traiter la commande selon ses paramètres
		logger.i("Command received : " + command);
		logger.i("Param received : " + param);
		
		Command c = cf.createCommand(scm, command, param, responseManager);
		String response = c.execute();
		responseManager.send(response);
	}

}
