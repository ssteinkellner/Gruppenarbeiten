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
				Output.println("accepting ...");
				clientSocket = serverSocket.accept();
				Output.println("accepted!");
				socketCommunication.createSocketCommunicationThread(clientSocket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void exit(){
		lauf = false;
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("ERROR when closing ServerSocket: " + e.getMessage());
		}
	}
	
	public boolean isOpen(){
		return !serverSocket.isClosed();
	}
}
