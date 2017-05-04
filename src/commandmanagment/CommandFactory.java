package commandmanagment;

import commandmanagment.implementations.AnswerOnlyCommand;
import commandmanagment.implementations.ConnectionCommand;
import commandmanagment.implementations.PWDCommand;
import commandmanagment.implementations.EPRTCommand;
import commandmanagment.implementations.LISTCommand;
import commandmanagment.implementations.PASVCommand;
import commandmanagment.implementations.PASSCommand;
import commandmanagment.implementations.RETRCommand;
import commandmanagment.implementations.STORCommand;
import commandmanagment.implementations.USERCommand;
import requestprocessing.ResponseManager;
import server.SessionCommandsManager;
import utils.Constants;

public class CommandFactory {

	public Command createCommand(SessionCommandsManager scm, String command, String param, ResponseManager rManager) {
		switch(command) {
		case Command.CMD_CONNECTION:
			return new ConnectionCommand(scm);
		case Command.CMD_USER:
			return new USERCommand(scm, param);
		case Command.CMD_PASSWORD:
			return new PASSCommand(scm, param);
		case Command.CMD_PWD:
			return new PWDCommand(scm, param);
		case Command.CMD_TYPE:
			return new AnswerOnlyCommand(scm, param, "200 TYPE set to I");
		case Command.CMD_EXTENDED_DATAPORT:
			return new EPRTCommand(scm, param);
		case Command.CMD_LIST:
			return new LISTCommand(scm, param, rManager);
		case Command.CMD_PASV:
			return new PASVCommand(scm, param);
		case Command.CMD_RETRIEVE:
			return new RETRCommand(scm, param);
		case Command.CMD_STOR:
			return new STORCommand(scm, param);
		case Command.CMD_TERMINATE:
			//return new TerminateCommand();
		default :
			return new AnswerOnlyCommand(scm, param, "503 Unknown command");
		}
	}

}
