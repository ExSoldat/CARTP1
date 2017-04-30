package CommandManagement;

public interface Command {
	//http://www.serv-u.com/respcode.asp?cmd=TYPE&prod=su
	
	//Should implement EPSV for passive data
	
	public static final String CMD_USER = "USER";
	public static final String CMD_PASSWORD = "PASS";
	public static final String CMD_RETRIEVE = "retrieve";
	public static final String CMD_TERMINATE = "terminate";
	public static final String CMD_PWD = "PWD";
	public static final String CMD_STORE = "store";
	public static final String CMD_CONNECTION = "CONNECTION";
	public static final String CMD_TYPE = "TYPE";
	public static final String CMD_EXTENDED_DATAPORT = "EPRT";
	public static final String CMD_LIST = "LIST";
	public static final String CMD_PASV = "PASV";
	
	public String execute();

}
