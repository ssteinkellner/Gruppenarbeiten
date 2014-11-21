package communication.socket;
import interfaces.Sendable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import common.Output;

/**
 * 
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class SocketCommunicationThread extends Thread implements Sendable{
	private SocketCommunication socketCommunication;
	private Socket clientSocket;
	private String text;
	private boolean lauf;
	private BufferedReader in;
	private PrintWriter out;
	
	public SocketCommunicationThread(Socket clientSocket, SocketCommunication socketCommunication){
		this.clientSocket = clientSocket;
		this.socketCommunication = socketCommunication;
		text = "";
	}
	
	@Override
	public void run(){
		try{
			in = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			
			lauf=true;

			socketCommunication.setLastMessage(getIP()+" connected!");
			
			while(lauf){
				try{
					Output.debug("reading ...");
					text = in.readLine();
					Output.debug("read!");
					if(text.equalsIgnoreCase("bye.")){
						exit();
						break;
					}else{
						text = getIP() + ": " + text;
					}
					socketCommunication.setLastMessage(text);
				}catch(Exception e){
					if(clientSocket==null || clientSocket.isClosed()){
						Output.error("lost a Socket Socket!");
						lauf=false;
					}else if(e.getMessage().toLowerCase().contains("connection reset")){
						lauf=false;
					}else{
						Output.error("ERROR when reading text from Socket: " + e.getMessage());
						e.printStackTrace();
					}
				}
			}
			socketCommunication.setLastMessage(getIP()+" disconnected!");
		}catch(Exception e){
			Output.error("can't open Socket: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * um eine nachricht zu uebertragen
	 * @param text nachricht die uebertragen werden soll
	 */
	@Override
	public void send(String text){
		Output.debug("writing ...");
		out.println(text);
		if(text.equalsIgnoreCase("bye.")){
			exit();
		}
		Output.debug("written!");
	}

	/**
	 * um zu pruefen, ob der socket offen ist
	 * @return true, wenn der socket offen ist, ansonsten false
	 */
	public boolean isOpen(){
		return !clientSocket.isClosed();
	}
	
	/**
	 * stopp den thread und schliesst die verbindung
	 */
	public void exit(){
		lauf = false;
		Output.debug("closing Socket...");
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			Output.error("can't close Socket: " + e.getMessage());
			e.printStackTrace();
		}
		Output.debug("Socket cloesd!");
	}
	
	/**
	 * gibt die IP des partners zurueck
	 * @return IP des partners
	 */
	private String getIP(){
		return clientSocket.getInetAddress().getHostAddress();
	}
}
