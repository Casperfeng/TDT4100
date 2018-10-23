package patterns.delegation;
/**
 * 1/5/2018
 * @author casperfeng
 * gitt som en del av oppgaveteksten til Øving 8
 */

public interface ILogger {
    public String ERROR = "error", WARNING = "warning", INFO = "info";
    
    /**
     * Definerer en log-metode som brukes til logging
     * @param severity - "ERROR", "WARNING", eller "INFO"
     * @param message - Hva var feil?
     * @param exception - gir mer info om hva som er feil, kan være null
     * @throws Exception 
     */
    public void log(String severity, String message, Exception exception) throws Exception;
}