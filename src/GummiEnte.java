/**
 * implementierung einer gummiente
 * @author TLins
 */
public class GummiEnte implements Quakfaehig {
	
	SenderRing senderRing;
	
	/**
	 * erstellt eine lockpfeife samt senderring
	 */
	public GummiEnte() {
		senderRing = new SenderRing(this);
	}
	
	/**
	 * @see Quakfaehig#quaken()
	 */
	public void quaken() {
		System.out.println("Quietsch");
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
	 * gibt den typ der ente als String zurueck
	 */
	public String toString() {
		return "Gummiente";
	}
}