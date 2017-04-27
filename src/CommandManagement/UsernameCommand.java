package CommandManagement;

import java.util.ArrayList;
import java.util.List;

import Model.User;
import Server.SessionCommandsManager;
import Utils.LogUtils;

public class UsernameCommand implements Command {
	List<User> users;
	String testedUsername;
	private SessionCommandsManager scm;
	private LogUtils logger = new LogUtils("UsernameCommand");
	
	public UsernameCommand(SessionCommandsManager scm, String param) {
		this.testedUsername = param;
		this.scm = scm;
		this.users = scm.getAvailableUsers();
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
