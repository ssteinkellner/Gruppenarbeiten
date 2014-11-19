/**
 * Eine Klasse, die das letzte glied der Decorator kette darstellt,
 * um f�r die 2 Richtungen verschiedene l�ngen zu erm�glichen
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class Communicator implements Recieveable, Sendable {

	private Translator translator;
	private BadWordFilter badWordFilter;

	/**
	 * @see Recieveable#recieve()
	 */
	public String recieve() {
		return badWordFilter.recieve();
	}


	/**
	 * @see Sendable#send(java.lang.String)
	 */
	public void send(String text) {
		translator.send(text);
	}

}
