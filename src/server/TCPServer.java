package server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import utils.Logger;

public class TCPServer {
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	Logger logger = new Logger("TCPServer");
	
	public TCPServer(int serverPort) {
		try {
			this.serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			if(!serverSocket.isClosed())
				serverSocket.close();
			if(!connectionSocket.isClosed())
				connectionSocket.close();
		} catch (IOException e) {
			logger.e("Unable to close connection");
		}
	}
	
	public void run() throws IOException {
		while(true) {
			connectionSocket = serverSocket.accept();
			SessionCommandsManager scm = new SessionCommandsManager(connectionSocket);
			scm.start();
		}
	}
	
}
