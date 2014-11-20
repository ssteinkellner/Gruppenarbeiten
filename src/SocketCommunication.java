import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Eine klasse, die eine Socketverbindung aufbaut und eine Kommunikation ermöglicht
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class SocketCommunication implements Connection {
	private LinkedList<SocketCommunicationThread> partners;
	private SocketCommunicationServer server;
	private String lastMessage;
	
	public SocketCommunication(){
		partners = new LinkedList<SocketCommunicationThread>();
	}
	
	/**
	 * @see Sendable#send(java.lang.String)
	 */
	 @Override
	public void send(String text) {
		Iterator<SocketCommunicationThread> i = partners.iterator();
		while(i.hasNext()){
			SocketCommunicationThread temp = i.next();
			if(temp.isOpen()){
				temp.send(text);
			}else{
				partners.remove(temp);
			}
		}
	}

	/**
	 * @see Recievable#recieve()
	 */
	public String recieve() {
		try {
			synchronized(this){
				this.wait();
			}
		} catch (InterruptedException e) {
			System.err.println("ERROR when waiting for Input: " + e.getMessage());
			e.printStackTrace();
		}
		return lastMessage;
	}

	/**
	 * @see Connection#open(String, int)
	 */
	public void open(String ip, int port) {
		if(ip.contains("-1")){
			try {
				ServerSocket serverSocket = new ServerSocket(port);
				server = new SocketCommunicationServer(serverSocket, this);
				server.start();
			} catch (IOException e) {
				System.err.println("ERROR when opening ServerSocket: " + e.getMessage());
				e.printStackTrace();
			}
		}else{
			try{
				Socket clientSocket = new Socket(ip, port);
				createSocketCommunicationThread(clientSocket);
			}catch (IOException e) {
				System.err.println("ERROR when opening ClientSocket: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see Connection#close()
	 */
	public void close() {
		server.exit();
		Iterator<SocketCommunicationThread> i = partners.iterator();
		while(i.hasNext()){
			i.next().exit();
		}
	}
	
	/**
	 * @see Connection#isOpen()
	 */
	public boolean isOpen(){
		return server.isOpen();
	}
	
	protected void setLastMessage(String text){
		lastMessage = text;
	}

	public void createSocketCommunicationThread(Socket clientSocket) {
		SocketCommunicationThread temp = new SocketCommunicationThread(clientSocket, this);
		temp.start();
		partners.add(temp);
	}
}
