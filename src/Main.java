import java.io.IOException;
import java.util.ArrayList;

import Server.TCPServer;
import Utils.LogUtils;

public class Main {

	//TODO : LIST command, following this thread : http://stackoverflow.com/questions/26980284/implementing-own-ftp-server-response-on-list-command
	//Create new methods in the Socket class for datatransfer
	public static void main(String[] args) {
		LogUtils logger = new LogUtils("Main");		
		TCPServer server = new TCPServer(1025);
		try {
			logger.i("Starting server...");
			server.run();
		} catch (IOException e) {
			server.closeConnection();
			logger.e("An exception occured during runtime");
			e.printStackTrace();
		}
	}

}
