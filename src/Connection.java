/**
 * Interface, welches Methode zum starten und beenden der Kommunikation zur Verfuegung stellt
 * @author Alexander Koelbl
 * @version 2014.11.19
 */
public interface Connection {
	/**
	 * Methode, welche die Kommunikation startet
	 * @param ip IP-Adresse, mit der man sich verbinden will (vom Empfaenger)
	 * @param port Port, ueber dem kommuniziert wird (vom Empfaenger)
	 */
	public void open(String ip, int port);
	/**
	 * Methode, welche die Kommunikation beendet
	 */
	public void close();

}
