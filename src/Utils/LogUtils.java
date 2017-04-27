package Utils;

public class LogUtils {
	private String tag = "Undefined";
	public LogUtils(String classTag) {
		this.tag = classTag;
	}
	
	public void e(String errorMessage) {
		w("ERROR", errorMessage);
	}
	
	public void i(String infoMessage) {
		w("INFO", infoMessage);
	}
	
	private void w(String type, String message) {
		System.out.println(tag + '-' + type.toUpperCase() + " : " + message);
	}

	public void c(String testedCondition, String conditionResult) {
		w("DEBUGCONDITION-"+testedCondition.toUpperCase(), conditionResult);		
	}
}
