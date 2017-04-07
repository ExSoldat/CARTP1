package CommandManagement;

import java.util.ArrayList;

import Model.User;
import Server.SessionCommandsManager;

public class PasswordCommand implements Command {
	ArrayList<User> users = new ArrayList<User>();
	String testedPassword;
	private SessionCommandsManager scm;
	
	public PasswordCommand(SessionCommandsManager scm, String param) {
		users.add(new User("NoctisLucisCaelum", "azerty"));
		users.add(new User("LunafreyaNoxFleuret", "azerty"));
		this.scm = scm;
		this.testedPassword = param;
	}
	
	@Override
	public String execute() {
		for(User u : users) {
			if(u.getPassword().equals(testedPassword)) {
				scm.setUser(new User(u.getUsername()));
				return "230 User logged in, proceed";
			}
				
		}
		return "430 Invalid login";
	}

}
