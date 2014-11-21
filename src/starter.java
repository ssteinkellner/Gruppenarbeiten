import javax.swing.JOptionPane;

import common.Output;
import communication.jrm.JRMCommunication;
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
		Chat c = new Chat();
		SocketCommunication scon = new SocketCommunication();
		JRMCommunication jrmcon = new JRMCommunication("", "");
		
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
