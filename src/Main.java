import java.io.IOException;
import java.util.ArrayList;

import Server.TCPServer;
import Utils.LogUtils;

public class Main {

	public static void main(String[] args) {
		LogUtils logger = new LogUtils("Main");		
		TCPServer server = new TCPServer(1025);
		try {
			server.run();
		} catch (IOException e) {
			server.closeConnection();
			logger.e("An exception occured during runtime");
			e.printStackTrace();
		}
	}

}
