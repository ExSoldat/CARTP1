package Server;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
	private File file = null;
	private List<User> availableUsers = new ArrayList<User>();
	private Socket fileTransferSocket;

	public SessionCommandsManager(Socket connectionSocket) {
		super("SessionCommandsManager");
		requestManager= new RequestManager(connectionSocket);
		this.connectionSocket = connectionSocket;	
		availableUsers.add(new User("NoctisLucisCaelum", "azerty"));
		availableUsers.add(new User("LunafreyaNoxFleuret", "azerty"));
	}
	
	@Override
	public void run() {
		logger.i("Starting a new SessionCommandsManager");
		do {
			//running=true;
			sManager.addAddress(connectionSocket.getInetAddress());
			requestManager.receive(this);			
		} while(running);
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setIsRunning(boolean running) {
		this.running = running;
	}

	public void setUser(User user) {
		this.loggedUser = user;
	}
	
	public User getUser() {
		return this.loggedUser;
	}
	
	public List getAvailableUsers() {
		return availableUsers;
	}
	
	public void setFile(File f) {
		this.file = f;
	}
	
	public File getFile() {
		return this.file;
	}

	public Socket getFileTransferSocket() {
		return fileTransferSocket;
	}

	public void setDataport(Socket fileTransferSocket) {
		this.fileTransferSocket = fileTransferSocket;
	}
	
	
	
}
