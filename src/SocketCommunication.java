import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.LinkedList;

/**
 * Eine klasse, die eine Socketverbindung aufbaut und eine Kommunikation ermöglicht
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class SocketCommunication implements Sendable, Recievable, Connection {
	private boolean isOpen;
	private LinkedList<PrintWriter> partners;
	private ServerSocket serverSocket;
	
	public SocketCommunication(){
		partners = new LinkedList<PrintWriter>();
		isOpen=false;
	}
	
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
