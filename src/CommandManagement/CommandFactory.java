package CommandManagement;

public class CommandFactory {

	public Command createCommand(String command, String param) {
		switch(command) {
		case "connection":
			return new ConnectionCommand();
		case "USER":
			return new UsernameCommand(param);
		case "password":
			//return new PasswordCommand(param);
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
