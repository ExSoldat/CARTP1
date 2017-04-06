package CommandManagement;

import java.util.ArrayList;

import Model.User;

public class UsernameCommand implements Command {
	ArrayList<User> users = new ArrayList<User>();
	String testedUser;
	
	
	public UsernameCommand(String username) {
		users.add(new User("NoctisLucisCaelum", "azerty"));
		users.add(new User("LunafreyaNoxFleuret", "azerty"));
		testedUser = username;
	}

	@Override
	public String execute() {
		for(User u : users) {
			if(u.getUsername().equals(testedUser))
				return "331 Username ok";
		}
		return "530 Login Incorrect";
	}

}
