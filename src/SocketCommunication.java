/**
 * Eine klasse, die eine Socketverbindung aufbaut und eine Kommunikation ermöglicht
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class SocketCommunication implements Sendable, Recievable, Connection {

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
		if(ip.contains("-1")){
			
			
		}else{
			
			
		}
	}

	/**
	 * @see Connection#close()
	 */
	public void close() {

	}
}
