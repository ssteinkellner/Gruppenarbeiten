/**
 * 
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class JRMCommunication implements Sendable, Recievable, Connection {
	
	/**
	 * @see Sendable#send(java.lang.String)
	 */
	public void send(String test) {

	}

	/**
	 * @see Recievable#recieve()
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
