public class Communicator implements Recieveable, Sendable {

	private Translator translator;

	private BadWordFilter badWordFilter;


	/**
	 * @see Recieveable#recieve()
	 */
	public String recieve() {
		return null;
	}


	/**
	 * @see Sendable#send(java.lang.String)
	 */
	public void send(String test) {

	}

}
