/**
 * @author TLins
 */
public class StockEnte implements Quakfaehig {
	
	SenderRing senderRing;
	
	/**
	 * erstellt eine neue stockente samt entenring
	 */
	public StockEnte() {
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
		return "Stockente";
	}
}