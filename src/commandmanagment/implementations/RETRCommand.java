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

	/**
	 * A command triggered when the user wants to download a file from the ftp server
	 */
	private SessionCommandsManager scm;
	private Logger logger = new Logger("RETRCommand");
	File f;
	
	/**
	 * The constructor creating the File object
	 * @param scm the origin SessionCommandsManager
	 * @param param the parameter (the name of the file to be retrieved)
	 */
	public RETRCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
		f = new File(param);
	}	
	
	/**
	 * The execution function, sending to the client via a socket, the data from the stored file
	 */
	@Override
	public String execute() {
		if(f.exists()) {
			try {
				FileInputStream fis = new FileInputStream(f);
				byte[] buffer = new byte[4096];
				
				//While we can still read from the file, then we send data to the socket
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