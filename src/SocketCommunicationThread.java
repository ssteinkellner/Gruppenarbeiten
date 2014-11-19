import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class SocketCommunicationThread extends Thread{
	private SocketCommunication socketCommunication;
	private Socket clientSocket;
	private String text;
	private boolean lauf;
	
	public SocketCommunicationThread(Socket clientSocket, SocketCommunication socketCommunication){
		this.clientSocket = clientSocket;
		this.socketCommunication = socketCommunication;
		text = "";
		lauf = true;
	}
	
	@Override
	public void run(){
		while(lauf){
			try{
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				
				text = in.readLine();
				socketCommunication.notify();
			}catch(Exception e){
				System.err.println("ERROR when reading text from Socket: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void exit(){
		lauf = false;
	}
}
