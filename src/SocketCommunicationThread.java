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
		try(
			BufferedReader in = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
		){
			while(lauf){
				try{
					text = in.readLine();
					synchronized(socketCommunication){
						socketCommunication.setLastMessage();
						socketCommunication.notify();
					}
				}catch(Exception e){
					System.err.println("ERROR when reading text from Socket: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			System.err.println("ERROR when opening SocketReader: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void exit(){
		lauf = false;
	}
}
