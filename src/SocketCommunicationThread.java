import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
	BufferedReader in;
	PrintWriter out;
	
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
					if(!clientSocket.isConnected()){
						lauf=false;
						break;
					}
					text = in.readLine();
					synchronized(socketCommunication){
						socketCommunication.setLastMessage(text);
						socketCommunication.notify();
					}
				}catch(Exception e){
					System.err.println("ERROR when reading text from Socket: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			System.err.println("ERROR when opening Socket: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void send(String text){
		out.write(text);
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
			System.err.println("ERROR when closing Socket: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
