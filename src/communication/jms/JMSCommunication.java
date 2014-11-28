package communication.jms;
import interfaces.Connection;
import interfaces.Recievable;
import interfaces.Sendable;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import common.Output;

/**
 * Eine Klasse fuer die Kommunikation ueber JRM
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class JMSCommunication implements Connection {
	private boolean isOpen;
	private String user, nick, password, subject;
	protected String mailBoxPrefix="mailBox_";

	Session session;
	javax.jms.Connection connection;
	MessageConsumer consumer;
	MessageProducer producer;
	Destination destination;

	public JMSCommunication(String user, String password){
		this(user,password,"localhost","VSDBChat");
		//setzt den default chatroom, falls er nicht angegeben wird
	}

	public JMSCommunication(String user, String password, String url, String subject){
		this.user = user;
		this.password = password;
		this.subject = subject;

		// Create the connection.
		session = null;
		connection = null;
		consumer = null;
		producer = null;
		destination = null;
		
		if(nick==null){
			nick=user;
		}
	}

	/**
	 * setzt den nicknamen
	 * @param nickName neuer nickname
	 */
	public void setNickName(String nickName){
		nick = nickName;
	}
	
	/**
	 * @see Sendable#send(java.lang.String)
	 */
	public void send(String text) {
		String[] cmd = new String[]{"switch","mailbox","mail"};
		if(text.toLowerCase().startsWith(cmd[0])){
			String[] temp = text.split(" ");
			if(temp.length<=1){
				Output.error("no Chatroom spezified\n");
				Output.println("Usage: "+cmd[0]+" <Chatroom>");
				return;
			}
			text = text.substring(temp[0].length()+1, text.length());	//ein abstand nach dem command
			this.closeDestination();
			try {
				this.openDestination(text);
				Output.println("switched to "+text);
			} catch (Exception e) {
				Output.error("Caught: " + e);
				e.printStackTrace();
			}
		}else if(text.toLowerCase().startsWith(cmd[1])){
			mailBox(nick);
		}else if(text.toLowerCase().startsWith(cmd[2])){
			String[] temp = text.split(" ");
			if(temp.length<=2){
				Output.error("no Message spezified");
				Output.println("Usage: "+cmd[2]+" <To> <Message>");
				return;
			}
			text = text.substring(temp[0].length()+temp[1].length()+2, text.length());
			//1 abstand nach dem command und einer nach dem namen
			mail(temp[1],"["+nick+"@"+getIp()+"|"+getDate()+" "+getTime()+"] "+text);
		}else{
			TextMessage message;
			try {
				message = session.createTextMessage( "["+nick+"@"+getIp()+"|"+getTime()+"] "+text );
				producer.send(message);
			} catch (JMSException e) {
				Output.error("Caught: " + e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see Recievable#recieve()
	 */
	public String recieve() {
		String text = null;
		TextMessage message;
		try {
			message = (TextMessage) consumer.receive();
			if ( message != null ) {
				text = message.getText();
				message.acknowledge();
			}else {
				text = "empty";
			}
		} catch (JMSException e) {
			Output.error("Caught: " + e);
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * @param url url of the server to connect to
	 * @param port port of the server (Default: 61616)
	 * @see Connection#open(String, int)
	 */
	public void open(String url, int port) {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, "tcp://"+url+":"+port);
			connection = connectionFactory.createConnection();
			connection.start();

			// Create the session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			openDestination(subject);
			
			isOpen=true;
		} catch (Exception e) {
			Output.error("Caught: " + e);
			e.printStackTrace();
		}
	}

	/**
	 * @see Connection#close()
	 */
	public void close() {
		try { connection.stop(); } catch ( Exception e ) {}
		closeDestination();
		try { session.close(); } catch ( Exception e ) {}
		try { connection.close(); } catch ( Exception e ) {}
		isOpen=false;
	}

	/**
	 * @see Connection#isOpen()
	 */
	public boolean isOpen(){
		return isOpen;
	}
	
	/**
	 * opens a new JMS destination (chatroom)
	 * @param subject name of the destination (chatroom name)
	 * @throws JMSException if the messageconsumer or messageproducer can't be created
	 */
	private void openDestination(String subject) throws JMSException{
		destination = session.createTopic( subject );

		consumer = session.createConsumer( destination );
		
		producer = session.createProducer( destination );
		producer.setDeliveryMode( DeliveryMode.NON_PERSISTENT );
	}
	
	/**
	 * closes the momentarily opened destination (chatroom)
	 */
	private void closeDestination(){
		try { consumer.close(); } catch ( Exception e ) {}
		try { producer.close(); } catch ( Exception e ) {}
	}
	
	/**
	 * sends the text to the mailbox of the spezified user
	 * @param toUser
	 * @param test
	 */
	private void mail(String toUser, String text){
		MessageProducer tempProducer = null;
		
		try {
			Queue tempDestination = session.createQueue( mailBoxPrefix+toUser );
			Output.debug( mailBoxPrefix+toUser );
			tempProducer = session.createProducer( tempDestination );
			
			TextMessage message = session.createTextMessage( text );
			tempProducer.send(message);
			
			Output.println("[MAIL] sent!");
		} catch (JMSException e) {
			Output.error("Caught: " + e);
			e.printStackTrace();
		}finally{
			try { tempProducer.close(); } catch ( Exception e ) {}
		}
	}
	
	/**
	 * reads the mailbox of the user
	 * @param userName
	 */
	private void mailBox(String userName){
		MessageConsumer tempConsumer = null;
		String text = "";
		
		try {
			Queue tempDestination = session.createQueue( mailBoxPrefix+userName );
			Output.debug( mailBoxPrefix+userName );
			tempConsumer = session.createConsumer( tempDestination );
			
			TextMessage message;
			try {
				message = (TextMessage) tempConsumer.receive(1000);
				while ( message != null ) {
					text += "\n[MAIL]";
					text += message.getText();
					message.acknowledge();
					message = (TextMessage) tempConsumer.receive(1000);
				}
				if(text.isEmpty()){ text="Your Mailbox is Empty!"; }
			} catch (JMSException e) {
				Output.error("Caught: " + e);
				e.printStackTrace();
			}
			
			
		} catch (JMSException e) {
			Output.error("Caught: " + e);
			e.printStackTrace();
		}finally{
			try { tempConsumer.close(); } catch ( Exception e ) {}
		}
		
//		return text;
		Output.println("[MAILBOX] "+text);
	}
	
	/**
	 * gibt den aktuellen Timestamp zurueck
	 * @return timestamp
	 */
	private String getTime(){
		Calendar calendar = Calendar.getInstance();
		String temp = ""+calendar.get(Calendar.HOUR_OF_DAY);
		temp += ":"+calendar.get(Calendar.MINUTE);
		return temp;
	}
	
	/**
	 * gibt den aktuellen Timestamp zurueck
	 * @return timestamp
	 */
	private String getDate(){
		Calendar calendar = Calendar.getInstance();
		String temp = ""+calendar.get(Calendar.YEAR);
		temp +="."+(calendar.get(Calendar.MONTH)+1);
		temp +="."+calendar.get(Calendar.DAY_OF_MONTH);
		return temp;
	}
	
	/**
	 * gibt die IP zureuck
	 * @return ip
	 */
	private String getIp(){
		try {
			return ""+InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "IPError";
	}
}
