package Server;
import java.net.Socket;

import RequestProcessing.RequestManager;
import Utils.LogUtils;

public class SessionCommandsManager extends Thread {

	private Socket connectionSocket;
	private RequestManager requestManager;
	private LogUtils logger = new LogUtils("SessionCommandsManager");
	private boolean running;
	SessionsManager sManager = SessionsManager.getInstance();

	public SessionCommandsManager(Socket connectionSocket) {
		super("SessionCommandsManager");
		requestManager= new RequestManager(connectionSocket);
		this.connectionSocket = connectionSocket;		
	}
	
	@Override
	public void run() {
		do {
			if(sManager.isAlreadyConnected(connectionSocket.getInetAddress())) {
				//Read the command then call CommandManager
				requestManager.receive(true);
			} else {
				//Connection message
				running = true;
				sManager.addAddress(connectionSocket.getInetAddress());
				requestManager.receive(false);
			}
		} while(running);
	}
	
	
}
