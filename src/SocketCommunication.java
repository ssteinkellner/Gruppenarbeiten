import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Eine klasse, die eine Socketverbindung aufbaut und eine Kommunikation ermöglicht
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class SocketCommunication implements Sendable, Recievable, Connection {
	private boolean isOpen;
	private LinkedList<Socket> partners;
	private ServerSocket serverSocket;
	
	public SocketCommunication(){
		partners = new LinkedList<Socket>();
		isOpen = false;
	}
	
	/**
	 * @see Sendable#send(java.lang.String)
	 */
	 @Override
	public void send(String text) {
		if(serverSocket!=null && isOpen){
			Iterator<Socket> i = partners.iterator();
			while(i.hasNext()){
				try(
					PrintWriter out = new PrintWriter(i.next().getOutputStream(), true);	//leitung zum client
				){
					out.write(text);
				}catch(Exception e){
					System.err.println("ERROR when sending text to Socket: " + e.getMessage());
				}
			}
		}
	}

	/**
	 * @see Recievable#recieve()
	 */
	public String recieve() {
		String text = null;
		if(serverSocket!=null && isOpen){
			try (
				Socket clientSocket = serverSocket.accept();	//verbindung öffnen, wenn ein client sich meldet
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));	//leitung vom client
				){
				text = in.readLine();
			}catch(Exception e){
				System.err.println("ERROR when reading text from Socket: " + e.getMessage());
			}
		}
		return text;
	}

	/**
	 * @see Connection#open(String, int)
	 */
	public void open(String ip, int port) {
		if(ip.contains("-1")){
			try {
				serverSocket = new ServerSocket(port);
				isOpen=true;
			} catch (IOException e) {
				serverSocket = null;
				System.err.println("ERROR when opening ServerSocket: " + e.getMessage());
			}
		}else{
			
			
		}
	}

	/**
	 * @see Connection#close()
	 */
	public void close() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("ERROR when closing ServerSocket: " + e.getMessage());
		}
		
		isOpen=false;
	}
	
	/**
	 * @see Connection#isOpen()
	 */
	public boolean isOpen(){
		return isOpen;
	}
}
