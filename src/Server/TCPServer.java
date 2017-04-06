package Server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Utils.LogUtils;

public class TCPServer {
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	LogUtils logger = new LogUtils("TCPServer");
	
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
			//A chaque accept créer un thread pour traiter la demande, de cette manière le server pourra écouter d'autres demandes concurrentes
			//Créer un thread par socket
			connectionSocket = serverSocket.accept();
			SessionCommandsManager scm = new SessionCommandsManager(connectionSocket);
			scm.run();
		}
	}
	
}
