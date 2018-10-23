package patterns.delegation;

import java.io.*;

/**
 * 1/5/2018
 * @author casperfeng
 *
 */
public class StreamLogger implements ILogger {
	
	private String formatString = "%s %s (%s)"; 	//angir hvordan teksten er formatert
	private OutputStream stream;				 	//stream hvor log meldinger skrives

	/**
	 * initialiserer en stream
	 * @param stream
	 */
	public StreamLogger(OutputStream stream) {
		this.stream = stream;
	}
	
	
	/**
	 * Må implementeres pga ILogger
	 * Skal angi alvorlighetsgraden osv
	 * @throws Exception 
	 */
	
	@Override
	public void log(String severity, String message, Exception exception) throws Exception{
		String logMessage = String.format(formatString, severity, message, exception); 
		//formaterer feilmeldingen
		try {
			this.stream.write(logMessage.getBytes());
		} catch (Exception e) {
			log(ILogger.ERROR, e.getStackTrace().toString(), e);
		}
		finally {
			this.stream.flush();
		}
	}
	
	/**
	 * Bruker en ny formattering av logMessage
	 * @param formatString
	 */
	public void setFormatString(String formatString) {
		this.formatString = formatString;
	}	
}
