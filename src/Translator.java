/**
 * 
 * @author Alex
 *
 */
public class Translator implements Sendable, Activatable {

	private Sendable sendable;
	
	private boolean enabled;


	/**
	 * @see Sendable#send(java.lang.String)
	 */
	public void send(String test) {

	}


	/**
	 * @see Activatable#setEnabled(boolean)
	 */
	public void setEnabled(boolean active) {
		this.enabled = active;
	}

}
