package communication.jrm;
import common.Output;
import interfaces.Connection;
import interfaces.Recievable;
import interfaces.Sendable;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Eine noch nicht fertig implementierte Klasse fuer die Kommunikation ueber JRM
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class JRMCommunication implements Connection {
	private boolean isOpen;
	private String user, password, subject;

	Session session;
	javax.jms.Connection connection;
	MessageConsumer consumer;
	MessageProducer producer;
	Destination destination;

	public JRMCommunication(String user, String password){
		this(user,password,"localhost","VSDBChat");
		//setzt den default chatroom, falls er nicht angegeben wird
	}

	public JRMCommunication(String user, String password, String url, String subject){
		this.user = user;
		this.password = password;
		this.subject = subject;

		// Create the connection.
		session = null;
		connection = null;
		consumer = null;
		producer = null;
		destination = null;
	}

	/**
	 * @see Sendable#send(java.lang.String)
	 */
	public void send(String text) {
		TextMessage message;
		try {
			message = session.createTextMessage( text );
			producer.send(message);
		} catch (JMSException e) {
			Output.error("Caught: " + e);
			e.printStackTrace();
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
			}
		} catch (JMSException e) {
			Output.error("Caught: " + e);
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * @param url url of the server to connect to
	 * @param port UNUSED
	 * @see Connection#open(String, int)
	 */
	public void open(String url, int port) {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
			connection = connectionFactory.createConnection();
			connection.start();

			// Create the session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			openDestination();
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
	
	private void openDestination() throws JMSException{
		destination = session.createTopic( subject );

		consumer = session.createConsumer( destination );
		
		producer = session.createProducer(destination);
		producer.setDeliveryMode( DeliveryMode.NON_PERSISTENT );
	}
	
	private void closeDestination(){
		try { consumer.close(); } catch ( Exception e ) {}
		try { producer.close(); } catch ( Exception e ) {}
	}

}
