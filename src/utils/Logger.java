package utils;

/***
 * A class that helps developpers to log things.
 * @author Mathieu
 *
 */
public class Logger {
	private String tag = "Undefined";
	public Logger(String classTag) {
		this.tag = classTag;
	}
	
	public void e(String errorMessage) {
		_w("ERROR", errorMessage);
	}
	
	public void i(String infoMessage) {
		_w("INFO", infoMessage);
	}
	
	/**
	 * A function that sends a warning message
	 * @param infoMessage
	 */
	public void w(String warningMessage) {
		_w("WARNING", warningMessage);
	}
	
	/***
	 * A function that outputs a message and its type
	 * @param type
	 * @param message
	 */
	private void _w(String type, String message) {
		System.out.println(tag + '-' + type.toUpperCase() + " : " + message);
	}

	public void c(String testedCondition, String conditionResult) {
		_w("DEBUGCONDITION-"+testedCondition.toUpperCase(), conditionResult);		
	}
}
