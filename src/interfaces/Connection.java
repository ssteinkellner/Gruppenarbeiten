package interfaces;
/**
 * Interface, welches Methode zum starten und beenden der Kommunikation zur Verfuegung stellt
 * @author Alexander Koelbl
 * @version 2014.11.19
 */
public interface Connection extends Sendable, Recievable {
	/**
	 * Methode, welche die Kommunikation startet
	 * @param ip IP-Adresse, mit der man sich verbinden will (vom Empfaenger)
	 * @param port Port, ueber dem kommuniziert wird (vom Empfaenger)
	 */
	public abstract void open(String ip, int port);
	/**
	 * Methode, welche die Kommunikation beendet
	 */
	public abstract void close();
	/**
	 * Methode kontrolliert, ob eine Verbindung geoeffnet ist
	 * @return true, wenn die Verbindung offen ist, false, wenn die Verbindung geschlossen ist 
	 */
	public abstract boolean isOpen();

}
