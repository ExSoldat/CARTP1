package server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.List;

import utils.Logger;

/**
 * A utiliy class that allows to gather informations from the files and have a list of the files inside another file
 * @author Mathieu
 *
 */
public class FileUtils {
	public static Logger logger = new Logger("FileUtils");
	
	/**
	 * A function used to get the files list in a directory
	 * @param file the file where we want to do the list
	 * @return a list of the files inside the directory
	 */
	public static ArrayList<File> getFilesList(File file) {
		ArrayList<File> filesList = new ArrayList<File>();
		File[] thefiles = file.listFiles();
		for(File f : thefiles) {
			filesList.add(f);
		}
		return filesList;
	}
	
	/**
	 * A function ro create a string that represents a file
	 * @param f the file e want to format
	 * @return a string representating this file
	 */
	public static String formatFileToAnswer(File f) {
		String result = "";
		result += f.isDirectory() ? "d" : "-";
		result += formatPermissions(); //For the moment I always put the same permissions
		result += " ";
		result += f.isDirectory() ? "1" : "0";
		result += " ";
		try {
			result += Files.getOwner(f.toPath());
			result += " ";
		} catch (IOException e) {
			logger.e("Unable to parse file attributes");
			e.printStackTrace();
		}
		result += f.length();
		result += " ";
		result += f.getName();
		return result;
	}
	
	/**
	 * Unhandled
	 * @return always the same string
	 */
	private static String formatPermissions() {
		return "rwxrwxrwx";
	}

}
