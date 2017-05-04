package commandmanagment.implementations;

import java.util.ArrayList;
import java.util.List;

import commandmanagment.Command;
import model.User;
import server.SessionCommandsManager;
import utils.Logger;

/**
 * A command triggered when the client wants give a password to finally connect the user
 * @author Mathieu
 *
 */
public class PASSCommand implements Command {
	List<User> users = new ArrayList<User>();
	String testedPassword;
	private SessionCommandsManager scm;
	private Logger logger = new Logger("PASSCommand");
	
	/**
	 * The constructor
	 * @param scm the SessionCommandsManager that originated the command
	 * @param param the parameter (password) sent to the server
	 */
	public PASSCommand(SessionCommandsManager scm, String param) {
		logger.i("received param : " + param);
		users = scm.getAvailableUsers();
		this.scm = scm;
		this.testedPassword = param;
	}
	
	/**
	 * Checking if the password given is the one of the username we gave before
	 */
	@Override
	public String execute() {
		for(User u : users) {
			if(u.getUsername().equals(scm.getUser().getUsername()) && u.getPassword().equals(testedPassword)) {
				scm.setUser(new User(u.getUsername(), u.getPassword()));
				return "230 User logged in, proceed";
			}
		}
		//If we did not send a response before then the user is not connected
		scm.setIsRunning(false);
		scm.setUser(null);
		return "430 Invalid credentials";
	}

}
