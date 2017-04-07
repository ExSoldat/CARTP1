package CommandManagement;

import Server.SessionCommandsManager;

public class CommandFactory {

	public Command createCommand(SessionCommandsManager scm, String command, String param) {
		switch(command) {
		case "connection":
			return new ConnectionCommand(scm);
		case "USER":
			return new UsernameCommand(scm, param);
		case "password":
			//return new PasswordCommand(scm, param);
		case "retrieve":
			//return new RetrieveCommand(param);
		case "store":
			//return new StoreCommand(param);
		case "terminate":
			//return new TerminateCommand();
		default :
			return new UnknownCommand();
		}
	}

}
