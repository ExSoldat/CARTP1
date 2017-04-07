package Server;
import java.net.Socket;

import Model.User;
import RequestProcessing.RequestManager;
import Utils.LogUtils;

public class SessionCommandsManager extends Thread {

	private Socket connectionSocket;
	private RequestManager requestManager;
	private LogUtils logger = new LogUtils("SessionCommandsManager");
	private boolean running = false;
	SessionsManager sManager = SessionsManager.getInstance();
	private User loggedUser = null;

	public SessionCommandsManager(Socket connectionSocket) {
		super("SessionCommandsManager");
		requestManager= new RequestManager(connectionSocket);
		this.connectionSocket = connectionSocket;		
	}
	
	@Override
	public void run() {
		do {
			running=true;
			sManager.addAddress(connectionSocket.getInetAddress());
			requestManager.receive(this);			
		} while(running);
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setIsRunning(boolean running) {
		this.running = true;
	}

	public void setUser(User user) {
		this.loggedUser = user;
		
	}
	
	
}
