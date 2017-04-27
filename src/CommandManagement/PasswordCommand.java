package CommandManagement;

import java.util.ArrayList;
import java.util.List;

import Model.User;
import Server.SessionCommandsManager;
import Utils.LogUtils;

public class PasswordCommand implements Command {
	List<User> users = new ArrayList<User>();
	String testedPassword;
	private SessionCommandsManager scm;
	private LogUtils logger = new LogUtils("PasswordCommand");
	
	public PasswordCommand(SessionCommandsManager scm, String param) {
		logger.i("received param : " + param);
		users = scm.getAvailableUsers();
		this.scm = scm;
		this.testedPassword = param;
	}
	
	@Override
	public String execute() {
		for(User u : users) {
			logger.c("password", u.getPassword().equals(testedPassword) + " : " + u.getPassword() + "/" + testedPassword);
			if(u.getPassword().equals(testedPassword)) {
				scm.setUser(new User(u.getUsername(), u.getPassword()));
				return "230 User logged in, proceed";
			}
				
		}
		scm.setIsRunning(false);
		scm.setUser(null);
		return "430 Invalid login";
	}

}
