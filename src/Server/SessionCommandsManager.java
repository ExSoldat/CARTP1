package Server;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private ServerFile currentFile = null;
	private List<User> availableUsers = new ArrayList<User>();
	private DataTransferThread dataTransferSocket;
	private int dataTransferSocketPort;

	public SessionCommandsManager(Socket connectionSocket) {
		super("SessionCommandsManager");
		requestManager= new RequestManager(connectionSocket);
		this.connectionSocket = connectionSocket;	
		availableUsers.add(new User("NoctisLucisCaelum", "azerty"));
		availableUsers.add(new User("LunafreyaNoxFleuret", "azertyu"));
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
	
	public void setFile(ServerFile f) {
		this.currentFile = f;
	}
	
	public File getFile() {
		return this.currentFile;
	}

	public DataTransferThread getDataTransferSocket() {
		return dataTransferSocket;
	}

	public void setDataTransferSocket(DataTransferThread dataTransferSocket) {
		this.dataTransferSocket = dataTransferSocket;
	}
	
	public boolean openFileTransferSocket(int p) {
		this.dataTransferSocket = null;
		this.dataTransferSocketPort = p;
		try {
			this.dataTransferSocket = new DataTransferThread(new ServerSocket(dataTransferSocketPort));
			this.dataTransferSocket.start();
			logger.i("Listening on port " + dataTransferSocketPort);
			return true;
		} catch (Exception e) {
			logger.e("An error occured while opening a FileTransferSocket");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean openFileTransferSocket() {
		this.dataTransferSocket = null;
		int count = 0;
		Random r = new Random();
		while (count < 10) {
			//Open a random socket port if possible
			this.dataTransferSocketPort = r.nextInt(2000);
			try {
				count++;
				this.dataTransferSocket = new DataTransferThread(new ServerSocket(dataTransferSocketPort));
				this.dataTransferSocket.start();
				logger.i("Listening on port " + dataTransferSocketPort);
				return true;
			} catch (Exception e) {
				logger.e("Unable to open a Socket on port " + dataTransferSocketPort + " trying with a different one ... count ="  + count);
				e.printStackTrace();
			}
		}
		logger.e("All attempts to open a ServerSocket failed.");
		return false;
	}

	public int getFileTransferSocketPort() {
		return this.dataTransferSocketPort;
	}

	public ArrayList<File> getCurrentDirFileList() throws Exception {
		ArrayList<File> filesList = new ArrayList<File>();
		if(currentFile != null) {
			return ServerFile.getFilesList(currentFile);
		} else 
			throw new Exception("Current directory has not been set by PWD command");
	}

	
	
	
	
}
