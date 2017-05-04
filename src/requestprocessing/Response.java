package requestprocessing;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import utils.Logger;

/**
 * A class that is used to send responses to the client side
 * @author Mathieu
 *
 */
public class Response {
	private OutputStream os;
	private DataOutputStream dos;
	private Logger logger = new Logger("ResponseManager");
	
	/**
	 * The constructor initiating the stream where we will be sending our responses using the data from the socket
	 * @param connectionSocket
	 */
	public Response(Socket connectionSocket) {
		try {
			os = connectionSocket.getOutputStream();
			dos = new DataOutputStream(os);	
		} catch (IOException e) {
			logger.e("An error occured while trying to create output stream utils : ");
			e.printStackTrace();
		}
	}
	
	/**
	 * Sending a message to the stream
	 * @param message
	 */
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
