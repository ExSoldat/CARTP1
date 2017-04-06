package CommandManagement;

public class ConnectionCommand implements Command {

	@Override
	public String execute() {
		return "220 Service Ready";
	}

}
