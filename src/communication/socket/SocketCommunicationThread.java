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
			
			while(lauf){
				try{
					Output.debug("reading ...");
					text = in.readLine();
					Output.debug("read!");
					if(text.equalsIgnoreCase("bye.")){
						clientSocket.close();
						exit();
					}else{
						text = clientSocket.getInetAddress() + ": " + text;
					}
					socketCommunication.setLastMessage(text);
				}catch(Exception e){
					if(e.getMessage().toLowerCase().contains("connection reset")){
						lauf=false;
					}else{
						Output.error("ERROR when reading text from Socket: " + e.getMessage());
						e.printStackTrace();
					}
				}
			}
			socketCommunication.setLastMessage(clientSocket.getInetAddress()+" disconnected!");
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
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			Output.error("can't close Socket: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
