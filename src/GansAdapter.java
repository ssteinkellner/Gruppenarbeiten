/**
 * GansAdapter, damit man die Ente als Gans verwenden kann
 * @author TLins
 */
public class GansAdapter implements Quakfaehig {
	
	Gans gans;
	SenderRing senderRing;

	/**
	 * erstellt den adapter mit der gans, die adaptiert werden soll samt senderring
	 * @param gans gans, die adaptiert werden soll
	 */
	public GansAdapter(Gans gans) {
		this.gans = gans;
		senderRing = new SenderRing(this);
	}
	
	/**
	 * laesst die gans schnattern
	 */
	public void quaken() {
		gans.schnattern();
		benachrichtigeBeobachtende();
	}
	
	/**
	 * @see QuakBeobachtungsSubjekt#registriereBeobachter()
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
	 * gibt den typ der ente bzw. gans als String zurueck
	 */
	public String toString() {
		return "sich als Ente ausgebende Gans";
	}
}