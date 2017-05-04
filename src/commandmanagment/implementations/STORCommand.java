package commandmanagment.implementations;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import commandmanagment.Command;
import server.SessionCommandsManager;
import utils.Logger;

/**
 * A command triggered when the user wants to store a file into the ftp server
 * @author Mathieu
 *
 */
public class STORCommand implements Command {

	private SessionCommandsManager scm;
	private Logger logger = new Logger("STORCommand");
	File f;
	
	/**
	 * The constructor creating the file
	 * @param scm the original SessionCommandsManager
	 * @param param the name of the file to be created
	 */
	public STORCommand(SessionCommandsManager scm, String param) {
		this.scm = scm;
		f = new File(param);
		logger.i("Attempting to create file : " + param);
	}	
	
	/**
	 * The execution function creating the file on the server side then copying data from the datasocket to the file
	 */
	@Override
	public String execute() {
		//Managing the case where the file already exists
		if(f.exists()) {
			f.delete();
		}
		//trying to create the file
		try {
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			byte[] buffer = new byte[4096];
			
			DataInputStream reader = scm.getDataTransferThread().getInputStream();
			
			//reading the input from the data transfer socket in order to write in the newly created file
			while (reader.read(buffer) > 0) {
				fos.write(buffer);
			}
			
			logger.i("data of file : " + f.getName() + " transfered");
			scm.getDataTransferThread().close(); //Closing the data socket
			return "226 file sent, closing data connection";
		} catch (IOException e) {
			e.printStackTrace();
			return "500 an error occured";
		}
	}
}