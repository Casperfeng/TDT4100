package patterns.delegation;
public class DistributingLogger implements ILogger {

	private ILogger errorLogger, warningLogger, infoLogger;
	
	public DistributingLogger(ILogger errorLogger, ILogger warningLogger, ILogger infoLogger) {
		this.errorLogger = errorLogger;
		this.warningLogger = warningLogger;
		this.infoLogger = infoLogger;
	}
	
	public void setLogger(String severity, ILogger logger) {
		if (severity == ILogger.ERROR) {
			this.errorLogger = logger;
		} else if (severity == ILogger.WARNING) {
			this.warningLogger = logger;
		} else {
			this.infoLogger = logger;
		}
	}
	
	public ILogger findLogger(String severity) {
		if (severity == ILogger.ERROR) {
			return this.errorLogger;
		} else if (severity == ILogger.WARNING) {
			return this.warningLogger;
		} else {
			return this.infoLogger;
		}
	}
	
	@Override
	public void log(String severity, String message, Exception exception) {
		this.findLogger(severity).log(severity, message, exception);

	}

}