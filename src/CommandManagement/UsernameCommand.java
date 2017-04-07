package CommandManagement;

import java.util.ArrayList;

import Model.User;
import Server.SessionCommandsManager;

public class UsernameCommand implements Command {
	ArrayList<User> users = new ArrayList<User>();
	String testedUsername;
	private SessionCommandsManager scm;
	
	public UsernameCommand(SessionCommandsManager scm, String param) {
		users.add(new User("NoctisLucisCaelum", "azerty"));
		users.add(new User("LunafreyaNoxFleuret", "azerty"));
		this.scm = scm;
	}

	@Override
	public String execute() {
		for(User u : users) {
			if(u.getUsername().equals(testedUsername)) {
				scm.setUser(new User(u.getUsername()));
				return "331 Username ok";
			}
				
		}
		return "530 Login Incorrect";
	}

}
