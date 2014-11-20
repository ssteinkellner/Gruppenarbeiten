import java.util.Iterator;

/**
 * Schreibt den Text des Chats nur in Grossbuchstaben
 * @author Alexander Koelbl
 *
 */
public class Caps implements Sendable, Activatable {
	private Sendable sendable;
	
	private boolean enabled;
	
	public Caps(Sendable sendable){
		this.sendable = sendable;
	}
	/**
	 * Setter Methode, die bestimmt, ob man die Funktionalitaet des Caps aktivieren bzw. deaktivieren will
	 */
	public void setEnabled(boolean active) {
		this.enabled = active;
	}
	/**
	 * Woerter werden nur mit Grossbuchstaben geschrieben
	 * @see Sendable#send(java.lang.String)
	 */
	@Override
	public void send(String text) {
		if(enabled == true){
			text = text.toUpperCase();
		}
		sendable.send(text);
	}

}
