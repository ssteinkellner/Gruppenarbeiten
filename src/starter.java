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
	
	public static void main(String[] args){
		HashMap<String, String> temp = new HashMap<String, String>();
		
		for(int i=0;i<args.length;i++){
			if(args[i].equalsIgnoreCase("help")
					|| args[i].equalsIgnoreCase("--help")
					|| args[i].equalsIgnoreCase("-h")){
				System.out.println(
					  "Possible arguments:"
					+ "\n --protocol\t-p\tset the protocol to use"
					+ "\n --server  \t-s\tset the server (in case of JMS)"
					+ "\n --port    \t  \tset the port (in case of sockets)"
				);
				System.out.println(
					  "\nPossible protocols:"
					+ "\n'sockets' => peer to peer communication over sockets.\n\tyou are:"
					+ "\n\t - a server that can accepts connections"
					+ "\n\t - a client that can connect to other chat servers"
					+ "\n'JMS' => querry communication over a server.\n\tyou are only a client"
				);
				System.exit(0);
			}else if(args[i].equalsIgnoreCase("--protocol") || args[i].equalsIgnoreCase("-p")){
				i++;
				temp.put("protocol", args[i]);
			}else if(args[i].equalsIgnoreCase("--server") || args[i].equalsIgnoreCase("-s")){
				i++;
				temp.put("server", args[i]);
			}else if(args[i].equalsIgnoreCase("--port")){
				i++;
				temp.put("port", args[i]);
			}
		}
		
		
		
		
		Chat c = new Chat();
		SocketCommunication scon = new SocketCommunication();
		JMSCommunication jmscon = new JMSCommunication("", "");
		
		String input = JOptionPane.showInputDialog(null,
				"Bitte den Lokalen port eingeben!\n(fuer neue Verbindungen)",
				"Chat | setup", JOptionPane.PLAIN_MESSAGE
		);
		if(input==null || input.isEmpty()){ return; }
		int port = 0;
		try{
			port = Integer.parseInt(input);
		}catch(Exception e){
			Output.error("Illegal Port: '" + input + "' !");
			return;
		}
		scon.open("-1",port);
		c.setActiveConnection(scon);
		try { Thread.sleep(1000); } catch (Exception e) {}
		new GUI(c);
	}
}
