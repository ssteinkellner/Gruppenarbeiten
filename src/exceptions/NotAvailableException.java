package exceptions;

/**
 * eine exception, die geworfen werden kann wenn ein, von der factory verlangtes, objekt nicht verfügbar ist
 * @author SSteinkellner
 * @version 2015.01.07
 */
public class NotAvailableException extends Exception {
	public NotAvailableException() { super(); }
	public NotAvailableException(String message) { super(message); }
	public NotAvailableException(String message, Throwable cause) { super(message, cause); }
	public NotAvailableException(Throwable cause) { super(cause); }
}
