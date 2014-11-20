package communication.socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import common.Output;


public class SocketCommunicationServer extends Thread{
	private SocketCommunication socketCommunication;
	private ServerSocket serverSocket;
	private boolean lauf;
	
	public SocketCommunicationServer(ServerSocket serverSocket, SocketCommunication socketCommunication){
		this.socketCommunication = socketCommunication;
		this.serverSocket = serverSocket;
	}
	
	@Override
	public void run(){
		Socket clientSocket;
		lauf = true;
		while(lauf){
			try {
				Output.debug("accepting ...");
				clientSocket = serverSocket.accept();
				Output.debug("accepted!");
				socketCommunication.createSocketCommunicationThread(clientSocket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * stoppt den thread
	 */
	public void exit(){
		lauf = false;
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			Output.error("ERROR when closing ServerSocket: " + e.getMessage());
		}
	}
	
	/**
	 * um zu pruefen, ob der socket offen ist
	 * @return true, wenn der socket offen ist, ansonsten false
	 */
	public boolean isOpen(){
		return !serverSocket.isClosed();
	}
}
