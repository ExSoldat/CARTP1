package requestprocessing;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import utils.Logger;

public class ResponseManager {
	private OutputStream os;
	private DataOutputStream dos;
	private Logger logger = new Logger("ResponseManager");
	
	public ResponseManager(Socket connectionSocket) {
		try {
			os = connectionSocket.getOutputStream();
			dos = new DataOutputStream(os);	
		} catch (IOException e) {
			logger.e("An error occured while trying to create output stream utils : ");
			e.printStackTrace();
		}
	}
	
	public void send(String message) {
		String output = message+"\r\n";
		try {
			dos.writeBytes(output);
			logger.i("Message \"" + message + "\" sent");
		} catch (IOException e) {
			logger.e("An error occured while trying to write response : ");
			e.printStackTrace();
		}
	}
}
