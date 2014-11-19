import java.util.ArrayList;
import java.util.HashMap;
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
	private String ip;

	private Connection activeConnection;
	private ArrayList<Connection> connections;
	private HashMap<String,Sendable> sendables;
	private HashMap<String,Recievable> recievables;
	private HashMap<String,Activatable> activatables;
	
	public Chat(){
		this("-1");
	}
	
	public Chat(String ip){
		this.ip = ip;
		
		messages = new LinkedList<String>();
		connections = new ArrayList<Connection>();
		sendables = new HashMap<String,Sendable>();
		recievables = new HashMap<String,Recievable>();
		activatables = new HashMap<String,Activatable>();
	}
	
	public void setActiveConnection(Connection connection){
		if(!connections.contains(connection)){
			connections.add(connection);
		}
		
		if(activeConnection!=null && activeConnection.isOpen()){
			activeConnection.close();
		}
		
		activeConnection = connection;
		activeConnection.open(ip, 25555);
		
		sendables.put("t",new Translator(activeConnection));
		recievables.put("bwf",new BadWordFilter(activeConnection));
		
		communicator = new Communicator(sendables.get("t"), recievables.get("bwf"));
	}
	
	public ArrayList<Connection> getConnections(){
		return connections;
	}
	
	public Communicator getCommunicator(){
		return communicator;
	}
	
	public void setIp(String ip){
		this.ip = ip;
	}
}
