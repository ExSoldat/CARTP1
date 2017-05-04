package utils;

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
	
	public void w(String infoMessage) {
		_w("WARNING", infoMessage);
	}
	
	private void _w(String type, String message) {
		System.out.println(tag + '-' + type.toUpperCase() + " : " + message);
	}

	public void c(String testedCondition, String conditionResult) {
		_w("DEBUGCONDITION-"+testedCondition.toUpperCase(), conditionResult);		
	}
}
