package patterns.delegation;
import java.util.*;

/**
 * 1/5/2018
 * @author casperfeng
 *
 */

public class FilteringLogger implements ILogger{

	private List<String> isLogging = new ArrayList<>();
	private ILogger logger;
	
	public FilteringLogger(ILogger logger, String...severities) {
		this.logger = logger;
		for (String severity : severities) {
			isLogging.add(severity);
		}
	}
	
	public boolean isLogging(String severity) {
		return isLogging.contains(severity);
	}
	
	public void setIsLogging(String severity, boolean value) {
		if (value && !isLogging(severity)) {
			isLogging.add(severity);
		}
		else if (isLogging.contains(severity)) {
			isLogging.remove(severity);
		}
	}
	
	
	@Override
	public void log(String severity, String message, Exception exception) throws Exception {
		if (this.isLogging(severity)) {
			logger.log(severity, message, exception);
		}
		
	}
	
	
	
}
