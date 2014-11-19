/**
 * Stellt eine Methode zur Verfuegung, um Text zu senden
 * @author Alexander Koelbl
 * @version 2014.11.19
 */
public interface Sendable{
	/**
	 * Methode zum Senden von Texten
	 * @param text Text, der gesendet werden soll
	 */
	public abstract void send(String text);

}
