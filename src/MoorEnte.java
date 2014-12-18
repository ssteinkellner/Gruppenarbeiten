/**
 * implementierung einer moorente
 * @author TLins
 */
public class MoorEnte implements Quakfaehig {
	
	SenderRing senderRing;
	
	/**
	 * erstellt eine moorente samt sendering
	 */
	public MoorEnte() {
		senderRing = new SenderRing(this);
	}

	/**
	 * @see Quakfaehig#quaken()
	 */
	public void quaken() {
		System.out.println("Quak");
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
		return "Moorente";
	}
}