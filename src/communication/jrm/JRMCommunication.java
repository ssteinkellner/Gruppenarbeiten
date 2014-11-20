package communication.jrm;
import interfaces.Connection;
import interfaces.Recievable;
import interfaces.Sendable;

/**
 * Eine noch nicht fertig implementierte Klasse fuer die Kommunikation ueber JRM
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class JRMCommunication implements Connection {
	private boolean isOpen;
	
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

		isOpen=true;
	}

	/**
	 * @see Connection#close()
	 */
	public void close() {

		isOpen=false;
	}
	

	/**
	 * @see Connection#isOpen()
	 */
	public boolean isOpen(){
		return isOpen;
	}
}
