package CommandManagement;

public class UnknownCommand implements Command {

	@Override
	public String execute() {
		return "503 Unknown command";
	}

}
