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

/**
 * The TCPServer class which is the first to listen and create each sessions
 */
public class TCPServer {
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	Logger logger = new Logger("TCPServer");
	
	/**
	 * The constructor which opens a serverSocket to listen on
	 * @param serverPort the port on which the serversocket should be listening
	 */
	public TCPServer(int serverPort) {
		try {
			this.serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A function used to close the current connection
	 */
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
	
	/**
	 * The Threads run function
	 * Here we listen to each connection and give it a session commands manager to handle it.
	 * @throws IOException
	 */
	public void run() throws IOException {
		while(true) {
			connectionSocket = serverSocket.accept();
			SessionCommandsManager scm = new SessionCommandsManager(connectionSocket);
			scm.start();
		}
	}
	
}
