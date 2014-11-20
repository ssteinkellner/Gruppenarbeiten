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
					if(text=="bye."){
						clientSocket.close();
						exit();
					}else{
						text = clientSocket.getInetAddress() + ": " + text;
					}
					socketCommunication.setLastMessage(text);
				}catch(Exception e){
					if(e.getMessage().toLowerCase().contains("connection reset")){
						exit();
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
	
	@Override
	public void send(String text){
		Output.debug("writing ...");
		out.println(text);
		Output.debug("written!");
	}
	
	public boolean isOpen(){
		return !clientSocket.isClosed();
	}
	
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
