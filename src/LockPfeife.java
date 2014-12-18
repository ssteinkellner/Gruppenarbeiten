/**
 * eine lockpfeife
 * (mit der keine enten gerufen werden koennen)
 * @author TLins
 */
public class LockPfeife implements Quakfaehig {
	
	SenderRing senderRing;
	
	/**
	 * erstellt eine lockpfeife samt senderring
	 */
	public LockPfeife() {
		senderRing = new SenderRing(this);
	}

	/**
	 * @see Quakfaehig#quaken()
	 */
	public void quaken() {
		System.out.println("Kwaak");
		benachrichtigeBeobachtende();
	}

	/**
	 * @see QuakBeobachtungsSubjekt#registriereBeobachter(Beobachter)
	 */
	public void registriereBeobachter(Beobachter beobachter) {
		senderRing.registriereBeobachter(beobachter);
	}

	/**
	 * @see QuakBeobachtungsSubjekt#benachrichtigeBeobachtende()
	 */
	public void benachrichtigeBeobachtende() {
		senderRing.benachrichtigeBeobachtende();
	}

	/**
	 * gibt den typ der Lockpfeife als String zurueck
	 */
	public String toString() {
		return "Lockpfeife";
	}
}