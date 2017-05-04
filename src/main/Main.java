package main;
import java.io.IOException;
import java.util.ArrayList;

import server.TCPServer;
import utils.Logger;

public class Main {

	public static void main(String[] args) {
		Logger logger = new Logger("Main");		
		TCPServer server = new TCPServer(1025);
		try {
			logger.i("Starting server on port 127.0.0.1:1025");
			logger.w("Active mode not supported yet");
			logger.w("IPV6 not supported yet");
			server.run();
		} catch (IOException e) {
			server.closeConnection();
			logger.e("An exception occured during runtime");
			e.printStackTrace();
		}
	}

}
