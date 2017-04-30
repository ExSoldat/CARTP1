package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Utils.LogUtils;

public class DataTransferThread extends Thread {
	private OutputStream os;
	private DataOutputStream dos;
	private LogUtils logger = new LogUtils("DataTransferSocket");
	private Socket connectionSocket;
	private ServerSocket serverSocket;
	
	public DataTransferThread(ServerSocket sSocket) {
		super("DataTransferThread");
		this.serverSocket = sSocket;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				connectionSocket = serverSocket.accept();
				os = connectionSocket.getOutputStream();
				dos = new DataOutputStream(os);
				logger.i("Connection opened");
			} catch (IOException e) {
				logger.e("An error occured while accepting socket connections");
				e.printStackTrace();
			}
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
	
	public void close() {
		
		try {
			//serverSocket.close();
			connectionSocket.close();
			os.close();
			dos.close();
		} catch (IOException e) {
			logger.e("Unable to close the data transfer connection");
			e.printStackTrace();
		}
		
	}
	
	public Socket getSocket() {
		return connectionSocket;
	}

}
