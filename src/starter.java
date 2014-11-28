import frontend.Chat;
import frontend.GUI;
import interfaces.Connection;

import java.util.HashMap;

import javax.swing.JOptionPane;

import communication.jms.JMSCommunication;
import communication.socket.SocketCommunication;

/**
 * eine klasse, die einen port erfragt und die gui aufbaut
 * @author Steinkellner Sebastian
 * @verion 2014.11.20
 */
public class starter {
	private static String title = "Chat | Setup";
	private static boolean nogui = false;
	
	public static void main(String[] args){
		HashMap<String, String> arguments = new HashMap<String, String>();
		
		for(int i=0;i<args.length;i++){
			if(args[i].equalsIgnoreCase("help")
					|| args[i].equalsIgnoreCase("--help")
					|| args[i].equalsIgnoreCase("-h")){
				System.out.println(
					  "Possible arguments:"
					+ "\n --protocol\t-p  \tset the protocol to use"
//					+ "\n --nogui   \t    \tdisables the GUI"	//not completely implemented
					+ "\nIn case of Sockets:"
					+ "\n --port    \t-pt  \tset the port"
					+ "\nIn case of JMS:"
					+ "\n --server  \t-s  \tset the server"
					+ "\n --user    \t-u  \tinput the username of the server"
					+ "\n --password\t-pwd\tinput the password of the server"
					+ "\n --nickname\t-n  \tset the nickname you want to use on the server"
				);
				System.out.println(
					  "\nPossible protocols:"
					+ "\n'Sockets' => peer to peer communication over sockets.\n\tyou are:"
					+ "\n\t - a server that can accepts connections"
					+ "\n\t - a client that can connect to other chat servers"
					+ "\n'JMS' => querry communication over a server.\n\tyou are only a client"
				);
				System.exit(0);
			}else if(args[i].equalsIgnoreCase("--nogui")){
				nogui=true;
			}else if(args[i].equalsIgnoreCase("--protocol") || args[i].equalsIgnoreCase("-p")){
				i++;
				arguments.put("protocol", args[i]);
			}else if(args[i].equalsIgnoreCase("--server") || args[i].equalsIgnoreCase("-s")){
				i++;
				arguments.put("server", args[i]);
			}else if(args[i].equalsIgnoreCase("--port") || args[i].equalsIgnoreCase("-pt")){
				i++;
				arguments.put("port", args[i]);
			}else if(args[i].equalsIgnoreCase("--user") || args[i].equalsIgnoreCase("-u")){
				i++;
				arguments.put("user", args[i]);
			}else if(args[i].equalsIgnoreCase("--password") || args[i].equalsIgnoreCase("-pwd")){
				i++;
				arguments.put("password", args[i]);
			}else if(args[i].equalsIgnoreCase("--nickname") || args[i].equalsIgnoreCase("-n")){
				i++;
				arguments.put("nickname", args[i]);
			}
		}
		
		String protocol = null;
		String[] protocols = new String[]{"Sockets", "JMS"};
		if(arguments.containsKey("protocol")){
			protocol = arguments.get("protocol");
		}else{
			Object input = select("Please select a Communication Protocoll", protocols, 0);
			if(input==null){
				abort();
			}else{
				protocol = (String) input;
			}
		}
		Connection con = null;
		if(protocol.equalsIgnoreCase(protocols[0])){	//sockets
			con = new SocketCommunication();
			String port = checkValue(arguments, "port", "Please input a local port!\n(used for new Connections)");
			try{
				int temp = Integer.parseInt(port);
				con.open("-1", temp);
			}catch(Exception e){
				abort("illegal Port: '" + port + "' !");
			}
		}else if(protocol.equalsIgnoreCase(protocols[1])){	//jms
			String[] server = new String[]{
					checkValue(arguments, "server", "Please input the serveradress!"),
					checkValue(arguments, "port", "Please input the serverport! (Default: 61616)")
			};
			String[] user = new String[]{
				checkValue(arguments, "user", "Please input the server username!"),
				checkValue(arguments, "password", "Please input the server password!")
			};

			for(int i=0;i<user.length;i++){
				if(user[i].equalsIgnoreCase("null")){ user[i]=null; }
			}
			con = new JMSCommunication(user[0], user[1]);
			if(arguments.containsKey("nickname")){
				((JMSCommunication) con).setNickName(arguments.get("nickname"));
			}
			try{
				int temp = Integer.parseInt(server[1]);
				con.open(server[0], temp);
			}catch(Exception e){
				abort("illegal Port: '" + server[1] + "' !");
			}
		}
		
		if(con!=null){
			Chat c = new Chat();
			c.setActiveConnection(con);
			try { Thread.sleep(1000); } catch (Exception e) {}
			new GUI(c);
		}
	}
	
	/**
	 * aborts the setup and exits the programm
	 */
	private static void abort(){
		System.err.println("User aborted setup!");
		System.exit(0);
	}
	
	/**
	 * aborts the setup with a specific message and exits the programm
	 * @param text message to print before exiting
	 */
	private static void abort(String text){
		System.err.println(text + "\naborting setup!");
		System.exit(0);
	}
	
	/**
	 * opens a JOptionPane with an input field for the user
	 * @param text text to display in the window (maybe question)
	 * @return text that the user inputted
	 */
	private static String input(String text){
		if(nogui){ System.err.println(text); System.exit(0); }
		return JOptionPane.showInputDialog(null, text, title, JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * opens a JOptionPane with a selection dropdown box for the user
	 * @param text text to display in the window (maybe question)
	 * @param options all possible selection values
	 * @param defaultIndex index of the default selected value
	 * @return selected text
	 */
	private static Object select(String text, String[] options, int defaultIndex){
		if(nogui){ System.err.println(text); System.exit(0); }
		return JOptionPane.showInputDialog(null,
				text, title, JOptionPane.PLAIN_MESSAGE,
				null, options, options[defaultIndex]);
	}
	
	/**
	 * prüft, ob ein parameter per commandozeile eingegeben wurde. 
	 * <br>falls dies nicht der fall ist, so wird per GUI die eingabe verlangt
	 * <br>drueckt der User auf "Abbrechen" wird das progeamm beendet
	 * @param arguments liste der eingegebenen kommandozeilenargumente
	 * @param name name des arguments
	 * @param text text, der dem user angezeigt wird,
	 * falls er aufgefordert wird, einen parameter zu ergaenzen
	 * @return gibt den per startparameter definierten oder vom user eingegebenen text zurueck
	 */
	private static String checkValue(HashMap<String, String> arguments, String name, String text){
		if(arguments.containsKey(name)){
			return arguments.get(name);
		}else{
			String output = "";
			while(output.isEmpty()){
				output = input(text);
				if(output==null){
					abort();
				}
			}
			return output;
		}
	}
}
