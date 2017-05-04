package commandmanagment.implementations;

import java.util.ArrayList;
import java.util.List;

import commandmanagment.Command;
import model.User;
import server.SessionCommandsManager;
import utils.Logger;

public class PASSCommand implements Command {
	List<User> users = new ArrayList<User>();
	String testedPassword;
	private SessionCommandsManager scm;
	private Logger logger = new Logger("PasswordCommand");
	
	public PASSCommand(SessionCommandsManager scm, String param) {
		logger.i("received param : " + param);
		users = scm.getAvailableUsers();
		this.scm = scm;
		this.testedPassword = param;
	}
	
	@Override
	public String execute() {
		for(User u : users) {
			logger.c("password",(u.getUsername().equals(scm.getUser().getUsername()) && u.getPassword().equals(testedPassword)) + " : " + u.getPassword() + "/" + testedPassword);
			if(u.getUsername().equals(scm.getUser().getUsername()) && u.getPassword().equals(testedPassword)) {
				scm.setUser(new User(u.getUsername(), u.getPassword()));
				return "230 User logged in, proceed";
			}
		}
		scm.setIsRunning(false);
		scm.setUser(null);
		return "430 Invalid login";
	}

}
