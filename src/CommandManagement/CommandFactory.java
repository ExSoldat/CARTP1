package CommandManagement;

import RequestProcessing.ResponseManager;
import Server.SessionCommandsManager;
import Utils.Constants;

public class CommandFactory {

	public Command createCommand(SessionCommandsManager scm, String command, String param, ResponseManager rManager) {
		switch(command) {
		case Command.CMD_CONNECTION:
			return new ConnectionCommand(scm);
		case Command.CMD_USER:
			return new UsernameCommand(scm, param);
		case Command.CMD_PASSWORD:
			return new PasswordCommand(scm, param);
		case Command.CMD_PWD:
			return new DirectoryCommand(scm, param);
		case Command.CMD_TYPE:
			return new AnswerOnlyCommand(scm, param, "200 TYPE set to I");
		case Command.CMD_EXTENDED_DATAPORT:
			return new ExtendedDataportCommand(scm, param);
		case Command.CMD_LIST:
			return new ListCommand(scm, param, rManager);
		case Command.CMD_PASV:
			return new PASVCommand(scm, param);
		case Command.CMD_RETRIEVE:
			//return new RetrieveCommand(param);
		case Command.CMD_STORE:
			//return new StoreCommand(param);
		case Command.CMD_TERMINATE:
			//return new TerminateCommand();
		default :
			return new AnswerOnlyCommand(scm, param, "503 Unknown command");
		}
	}

}
