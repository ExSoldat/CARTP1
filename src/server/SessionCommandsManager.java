package server;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dataprocessing.DataTransferThread;
import model.User;
import requestprocessing.RequestManager;
import utils.Logger;

public class SessionCommandsManager extends Thread {

	private Socket connectionSocket;
	private RequestManager requestManager;
	private Logger logger = new Logger("SessionCommandsManager");
	private boolean running = false;
	SessionsManager sManager = SessionsManager.getInstance();
	private User loggedUser = null;
	private File currentDirectory = null;
	private List<User> availableUsers = new ArrayList<User>();
	private DataTransferThread dataTransferThread;
	private int dataTransferSocketPort;
	private boolean active = false;

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
			sManager.addAddress(connectionSocket.getInetAddress()); //Is this still useful ?
			requestManager.receive(this);			
		} while(running);
	}
	
	//TODO : manage active or passive mode
	public boolean openDataTransferThread() {
		this.dataTransferThread = null;
		int count = 0;
		Random r = new Random();
		while (count < 10) {
			//Open a random socket port if possible
			this.dataTransferSocketPort = r.nextInt(2000);
			try {
				count++;
				this.dataTransferThread = new DataTransferThread(new ServerSocket(dataTransferSocketPort));
				this.dataTransferThread.start();
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

	public int getDataTransferSocketPort() {
		return this.dataTransferSocketPort;
	}

	public ArrayList<File> getCurrentDirFilesList() throws Exception {
		ArrayList<File> filesList = new ArrayList<File>();
		if(currentDirectory != null) {
			return FileUtils.getFilesList(currentDirectory);
		} else 
			throw new Exception("Current directory has not been set by PWD command");
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
	
	public void setCurrentDirectory(File d) {
		this.currentDirectory = d;
	}
	
	public File getCurrentDirectory() {
		return this.currentDirectory;
	}

	public DataTransferThread getDataTransferThread() {
		return dataTransferThread;
	}

	public void setDataTransferThread(DataTransferThread dataTransferThread) {
		this.dataTransferThread = dataTransferThread;
	}	
	
	public void setActive(boolean a) {
		this.active = false;
	}
	
	public boolean isActive() {
		return this.active;
	}
}
