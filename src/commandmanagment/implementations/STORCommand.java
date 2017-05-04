package commandmanagment.implementations;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import commandmanagment.Command;
import server.SessionCommandsManager;
import utils.Logger;

public class STORCommand implements Command {

	private SessionCommandsManager scm;
	private Logger logger = new Logger("STORCommand");
	File f;
	
	public STORCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
		f = new File(param);
		logger.i("Attempting to create file : " + param);
	}	
	
	@Override
	public String execute() {
		if(f.exists()) {
			f.delete();
		}
		try {
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			byte[] buffer = new byte[4096];
			
			DataInputStream reader = scm.getDataTransferThread().getInputStream();
			
			while (reader.read(buffer) > 0) {
				fos.write(buffer);
			}
			
			logger.i("data of file : " + f.getName() + " transfered");
			scm.getDataTransferThread().close();
			return "226 file sent, closing data connection";
		} catch (IOException e) {
			e.printStackTrace();
			return "500 an error occured";
		}
	}
}