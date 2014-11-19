/**
 * Eine Klasse, die das letzte glied der Decorator kette darstellt,
 * um für die 2 Richtungen verschiedene längen zu ermöglichen
 * @author Steinkellner Sebastian
 * @version 2014.11.19
 */
public class Communicator implements Recieveable, Sendable {
	private Sendable sender;
	private Recievable reciever;

	public Communicator(Sendable sender, Recievable reciever){
		this.sender = sender;
		this.reciever = reciever;
	}
	
	/**
	 * @see Recievable#recieve()
	 */
	public String recieve() {
		return reciever.recieve();
	}

	/**
	 * @see Sendable#send(java.lang.String)
	 */
	public void send(String text) {
		sender.send(text);
	}
}
