package RequestProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import Utils.LogUtils;

public class RequestManager {
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader bfr;
	private ResponseTriggerer responseTriggerer;
	private LogUtils logger = new LogUtils("RequestManager");
	
	public RequestManager(Socket connectionSocket) {
		try {
			is = connectionSocket.getInputStream();
			isr = new InputStreamReader(is);	
			bfr = new BufferedReader(isr);
			responseTriggerer = new ResponseTriggerer(connectionSocket);
		} catch (IOException e) {
			logger.e("An error occured while trying to create input stream utils : ");
			e.printStackTrace();
		}
	}
	
	public void receive(boolean userIsConnected) {
		if(userIsConnected) {
		String input;
		try {
			//Ajouter un truc pour que ce soit bloquant ?
			input = bfr.readLine();
			responseTriggerer.triggerResponse(input);
			logger.i("Message \"" + input + "\" received");
		} catch (IOException e) {
			logger.e("An error occured while trying to read request : ");
			e.printStackTrace();
		}
		} else {
			responseTriggerer.triggerResponse("connection");
		}
	}
}
