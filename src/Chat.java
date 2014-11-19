import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Die hauptklasse des Programms
 * Sie speichert die nachrichten, lässt sie abrufen und neue senden
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class Chat {
	private Communicator communicator;
	private LinkedList<String> messages;
	private ArrayList<Connection> connections;
	private Connection activeConnection;
	
	public Chat(){
		messages = new LinkedList<String>();
		connections = new ArrayList<Connection>();
	}
	
	public void setActiveConnection(Connection connection){
		if(!connections.contains(connection)){
			connections.add(connection);
		}
		
		if(activeConnection!=null /*|| activeConnection.isOpen()*/){
			activeConnection.close();
		}
		
		activeConnection = connection;
		activeConnection.open("", 25555);
	}
	
	public ArrayList<Connection> getConnections(){
		return connections;
	}
}
