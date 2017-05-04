package commandmanagment.implementations;

import java.util.ArrayList;
import java.util.List;

import commandmanagment.Command;
import model.User;
import server.SessionCommandsManager;
import utils.Logger;

public class USERCommand implements Command {
	List<User> users;
	String testedUsername;
	private SessionCommandsManager scm;
	private Logger logger = new Logger("UsernameCommand");
	
	public USERCommand(SessionCommandsManager scm, String param) {
		this.testedUsername = param;
		this.scm = scm;
		this.users = scm.getAvailableUsers();
		this.scm.setUser(null);
	}

	@Override
	public String execute() {
		for(User u : users) {
			logger.c("username", u.getUsername().equals(testedUsername) + " : " + u.getUsername() + "/" + testedUsername);
			if(u.getUsername().equals(testedUsername)) {
				scm.setUser(new User(u.getUsername()));
				return "331 Username ok";
			}
				
		}
		scm.setIsRunning(false);
		scm.setUser(null);
		return "530 Login Incorrect";
	}

}
