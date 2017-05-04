package CommandManagement;

import java.util.ArrayList;
import java.util.List;

public interface Command {
	//http://www.serv-u.com/respcode.asp?cmd=TYPE&prod=su
	
	//Should implement EPSV for passive data
	
	public static final String CMD_USER = "USER";
	public static final String CMD_PASSWORD = "PASS";
	public static final String CMD_RETRIEVE = "RETR";
	public static final String CMD_TERMINATE = "terminate";
	public static final String CMD_PWD = "PWD";
	public static final String CMD_STOR = "STOR";
	public static final String CMD_CONNECTION = "CONNECTION";
	public static final String CMD_TYPE = "TYPE";
	public static final String CMD_EXTENDED_DATAPORT = "EPRT";
	public static final String CMD_LIST = "LIST";
	public static final String CMD_PASV = "PASV";
	public static final String CMD_EPSV = "EPSV";
	
	public String execute();

	public static List<String> getCommandsList() {
		List<String> result = new ArrayList<String>();
		result.add(Command.CMD_USER);
		result.add(Command.CMD_PASSWORD);
		result.add(Command.CMD_RETRIEVE);
		result.add(Command.CMD_TERMINATE);
		result.add(Command.CMD_PWD);
		result.add(Command.CMD_STOR);
		result.add(Command.CMD_TYPE);
		result.add(Command.CMD_EXTENDED_DATAPORT);
		//result.add(Command.CMD_PORT);
		result.add(Command.CMD_PASV);
		result.add(Command.CMD_EPSV);
		result.add(Command.CMD_LIST);
		result.add(Command.CMD_CONNECTION);
		return result;
	}

}
