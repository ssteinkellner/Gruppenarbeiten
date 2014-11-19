public class JRMCommunication implements Sendable, Recieveable, Connection {


	/**
	 * @see Sendable#send(java.lang.String)
	 */
	public void send(String test) {

	}


	/**
	 * @see Recieveable#recieve()
	 */
	public String recieve() {
		return null;
	}


	/**
	 * @see Connection#open(String, int)
	 */
	public void open(String ip, int port) {

	}


	/**
	 * @see Connection#close()
	 */
	public void close() {

	}

}
