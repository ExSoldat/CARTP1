package server;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class SessionsManager {
	private List<InetAddress> inetaddresses = new ArrayList<InetAddress>();
	private static SessionsManager instance;
	
	public static SessionsManager getInstance() {
		if(instance == null) {
			return new SessionsManager();
		} else {
			return instance;
		}
	}
	
	public void addAddress(InetAddress address) {
		if(isAlreadyConnected(address))
			inetaddresses.add(address);
	}
	
	public void removeAddress(InetAddress address) {
		inetaddresses.remove(address);
	}
	
	public boolean isAlreadyConnected(InetAddress address) {
		return inetaddresses.contains(address);
		
	}
}
