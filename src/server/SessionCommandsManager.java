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

/**
 * The class that manages all a session between a client and a erver
 * @author Mathieu
 *
 */
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

	/**
	 * A constructor that create basic objects and instanciate the users
	 * @param connectionSocket the socket that will be used to communicate with the server
	 */
	public SessionCommandsManager(Socket connectionSocket) {
		super("SessionCommandsManager");
		requestManager= new RequestManager(connectionSocket);
		this.connectionSocket = connectionSocket;	
		availableUsers.add(new User("NoctisLucisCaelum", "azerty"));
		availableUsers.add(new User("LunafreyaNoxFleuret", "azerty"));
	}
	
	/**
	 * Implementing the run function of Thread
	 */
	@Override
	public void run() {
		//While we can, we receive requests from the connectionSocket
		logger.i("Starting a new SessionCommandsManager");
		do {
			sManager.addAddress(connectionSocket.getInetAddress()); //Is this still useful ?
			requestManager.receive(this);			
		} while(running);
	}
	
	//
	/**
	 * A function that allows the session commands manager to create a data transfer socket, which will be used to transfer files
	 * TODO : manage active mode (since this function works only for passive mode)
	 * @return true if the datatransferthread has been successfully created
	 */
	public boolean openDataTransferThread() {
		//Initializing variables
		this.dataTransferThread = null;
		int count = 0;
		Random r = new Random();
		//try to get a free port to open the data socket
		while (count < 10) {
			//Open a random socket port if possible
			this.dataTransferSocketPort = r.nextInt(2000);
			try {
				//We try to open the socket
				count++;
				this.dataTransferThread = new DataTransferThread(new ServerSocket(dataTransferSocketPort));
				//It seems successful so we start this thread
				this.dataTransferThread.start();
				logger.i("Listening on port " + dataTransferSocketPort);
				return true;
			} catch (Exception e) {
				//If an exception occured we try again
				logger.e("Unable to open a Socket on port " + dataTransferSocketPort + " trying with a different one ... count ="  + count);
				e.printStackTrace();
			}
		}
		//If we made this try ten times then there may be a problem so we stop the attempts.
		logger.e("All attempts to open a ServerSocket failed.");
		return false;
	}

	/**
	 * A function that allows to get the current directory files list
	 * @return a list of the files in the current directory
	 * @throws Exception if we do not find the files
	 */
	public ArrayList<File> getCurrentDirFilesList() throws Exception {
		if(currentDirectory != null)
			return FileUtils.getFilesList(currentDirectory);
		else 
			throw new Exception("Current directory has not been set by PWD command");
	}
	
	//Getters and setters
	
	public int getDataTransferSocketPort() {
		return this.dataTransferSocketPort;
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
