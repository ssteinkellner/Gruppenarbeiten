import interfaces.Connection;

import java.util.HashMap;

import javax.swing.JOptionPane;

import common.Output;
import communication.jms.JMSCommunication;
import communication.socket.SocketCommunication;
import frontend.Chat;
import frontend.GUI;

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
					+ "\n --protocol\t-p\tset the protocol to use"
					+ "\n --server  \t-s\tset the server (in case of JMS)"
					+ "\n --port    \t  \tset the port (in case of sockets)"
					+ "\n --nogui   \t  \tdisables the GUI"
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
			}else if(args[i].equalsIgnoreCase("--port")){
				i++;
				arguments.put("port", args[i]);
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
			SocketCommunication scon = new SocketCommunication();
			String port = "";
			if(arguments.containsKey("port")){
				port = arguments.get("port");
			}else{
				while(port.isEmpty()){
					port = input("Please input a local port!\n(used for new Connections)");
					if(port==null){
						abort();
					}
				}
			}
			try{
				int temp = Integer.parseInt(port);
				scon.open("-1", temp);
			}catch(Exception e){
				abort("illegal Port: '" + port + "' !");
			}
		}else if(protocol.equalsIgnoreCase(protocols[1])){	//jms
			con = new JMSCommunication("", "");
		}
		
		
		
		
		
		System.exit(0);
		
		Chat c = new Chat();
		
		c.setActiveConnection(con);
		try { Thread.sleep(1000); } catch (Exception e) {}
		new GUI(c);
	}
	
	private static void abort(){
		System.err.println("User aborted setup!");
		System.exit(0);
	}
	
	private static void abort(String text){
		System.err.println(text + "\naborting setup!");
		System.exit(0);
	}
	
	private static String input(String text){
		if(nogui){ System.err.println(text); System.exit(0); }
		return JOptionPane.showInputDialog(null, text, title, JOptionPane.PLAIN_MESSAGE);
	}
	
	private static Object select(String text, String[] options, int defaultIndex){
		if(nogui){ System.err.println(text); System.exit(0); }
		return JOptionPane.showInputDialog(null,
				text, title, JOptionPane.PLAIN_MESSAGE,
				null, options, options[defaultIndex]);
	}
}
