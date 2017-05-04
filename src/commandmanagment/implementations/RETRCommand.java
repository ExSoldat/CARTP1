package commandmanagment.implementations;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import commandmanagment.Command;
import server.SessionCommandsManager;
import utils.Logger;

public class RETRCommand implements Command {

	private SessionCommandsManager scm;
	private Logger logger = new Logger("RETRCommand");
	File f;
	
	public RETRCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
		f = new File(param);
	}	
	
	@Override
	public String execute() {
		if(f.exists()) {
			try {
				FileInputStream fis = new FileInputStream(f);
				byte[] buffer = new byte[4096];
				
				while (fis.read(buffer) > 0) {
					scm.getDataTransferThread().send(buffer);
				}
				
				scm.getDataTransferThread().close();
				logger.i("data of file : " + f.getName() + " transfered");
				return "226 file sent, closing data connection";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return "500 an error occured";
	}
}