package communication.jrm;
import common.Output;

import interfaces.Connection;
import interfaces.Recievable;
import interfaces.Sendable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Eine noch nicht fertig implementierte Klasse fuer die Kommunikation ueber JRM
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class JRMCommunication implements Connection {
	private boolean isOpen;
	private String user, password, url, subject;

	Session session;
	Connection connection;
	MessageConsumer consumer;
	Destination destination;

	private JRMCommunication(String user, String password, String url){
		this(user,password,url,"VSDBChat");
		//setzt den default chatroom, falls er nicht angegeben wird
	}
	
	public JRMCommunication(String user, String password, String url, String subject){
		this.user = user;
		this.password = password;
		this.url = url;
		this.subject = subject;

		// Create the connection.
		session = null;
		connection = null;
		consumer = null;
		destination = null;
	}
	
	/**
	 * @see Sendable#send(java.lang.String)
	 */
	public void send(String text) {
		TextMessage message = session.createTextMessage( text );
		producer.send(message);
	}

	/**
	 * @see Recievable#recieve()
	 */
	public String recieve() {
		String text = null;
		TextMessage message = (TextMessage) consumer.receive();
		if ( message != null ) {
			text = message.getText();
			message.acknowledge();
		}
		return text;
	}

	/**
	 * @see Connection#open(String, int)
	 */
	public void open(String ip, int port) {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
			connection = connectionFactory.createConnection();
			connection.start();

			// Create the session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic( subject );

			// Create the consumer
			consumer = session.createConsumer( destination );
		} catch (Exception e) {
			Output.error("Caught: " + e);
			e.printStackTrace();
		}
		isOpen=true;
	}

	/**
	 * @see Connection#close()
	 */
	public void close() {
		try { connection.stop(); } catch ( Exception e ) {}
		try { consumer.close(); } catch ( Exception e ) {}
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
}
