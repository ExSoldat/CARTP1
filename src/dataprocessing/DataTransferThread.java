package dataprocessing;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import utils.Logger;

public class DataTransferThread extends Thread {
	private OutputStream os;
	private InputStream is;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Logger logger = new Logger("DataTransferSocket");
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
				is = connectionSocket.getInputStream();
				dis = new DataInputStream(is);
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
	
	public void send(byte[] buffer) {
		try {
			dos.write(buffer);
			logger.i("Message sent");
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
			is.close();
			dis.close();
		} catch (IOException e) {
			logger.e("Unable to close the data transfer connection");
			e.printStackTrace();
		}
		
	}
	
	public Socket getSocket() {
		return connectionSocket;
	}

	public DataInputStream getInputStream() {
		return dis;
	}

}
