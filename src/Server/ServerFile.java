package Server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.List;

import Utils.LogUtils;

public class ServerFile extends File {
	public static LogUtils logger = new LogUtils("ServerFile");

	public ServerFile(String pathname) {
		super(pathname);
	}
	/***
	 * I don't handle the recusrive param now
	 * @param recursive
	 * @return a list of files inside a directory
	 */
	public static ArrayList<File> getFilesList(File file) {
		ArrayList<File> filesList = new ArrayList<File>();
		File[] thefiles = file.listFiles();
		for(File f : thefiles) {
			filesList.add(f);
		}
		return filesList;
	}
	
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
	
	private static String formatPermissions() {
		return "rwxrwxrwx";
	}

}
