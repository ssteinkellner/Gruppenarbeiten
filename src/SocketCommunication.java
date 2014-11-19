import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Eine klasse, die eine Socketverbindung aufbaut und eine Kommunikation erm�glicht
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class SocketCommunication implements Connection {
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
				Socket temp = i.next();
				if(temp.isConnected()){
					try(
						PrintWriter out = new PrintWriter(temp.getOutputStream(), true);	//leitung zum client
					){
						out.write(text);
					}catch(Exception e){
						System.err.println("ERROR when sending text to Socket: " + e.getMessage());
					}
				}else{
					partners.remove(temp);
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
			try {
				Socket clientSocket = serverSocket.accept();	//verbindung �ffnen, wenn ein client sich meldet
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));	//leitung vom client
				
				text = in.readLine();
				in.close();

				//wenn die ip noch nicht bekannt, dann socket speichern
				if(!containsSocket(partners, clientSocket)){
					partners.add(clientSocket);
				}
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
			try{
				Socket clientSocket = new Socket(ip, port);
			}catch (IOException e) {
				System.err.println("ERROR when opening ServerSocket: " + e.getMessage());
			}
		}
	}

	/**
	 * @see Connection#close()
	 */
	public void close() {
		try {
			serverSocket.close();
			Iterator<Socket> i = partners.iterator();
			while(i.hasNext()){
				i.next().close();
			}
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
	
	/**
	 * iteriert durch eine liste von sockets und gibt true zur�ck, wenn der socket enthalten ist
	 * @param sockets liste der sockets
	 * @param socket socket der gesucht werden soll
	 * @return true, falls der socket bereits in der liste ist, ansonsten false
	 */
	private boolean containsSocket(List<Socket> sockets, Socket socket){
		InetAddress ip = socket.getInetAddress();
		Iterator<Socket> i = sockets.iterator();
		while(i.hasNext()){
			if(i.next().getInetAddress().equals(ip)){
				return true;
			}
		}
		return false;
	}
}
