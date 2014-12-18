/**
 * @author TLins
 */
public class QuakZaehler implements Quakfaehig {
	
	Quakfaehig ente;
	static int anzahlDerQuaks;

	/**
	 * erstellt einen neuen quakzaehler, der alle quaks zaehlt
	 * @param ente
	 */
	public QuakZaehler(Quakfaehig ente) {
		this.ente = ente;
	}

	/**
	 * @see Quakfaehig#quaken()
	 */
	public void quaken() {
		ente.quaken();
		anzahlDerQuaks++;
	}

	/**
	 * gibt die anzahl der quaks zurueck
	 * @return anzahl der quaks
	 */
	public static int getQuaks() {
		return anzahlDerQuaks;
	}

	/**
	 * @see QuakBeobachtungsSubjekt#registriereBeobachter(Beobachter)
	 */
	public void registriereBeobachter(Beobachter beobachter) {
		ente.registriereBeobachter(beobachter);
	}

	/**
	 * @see QuakBeobachtungsSubjekt#benachrichtigeBeobachtende()
	 */
	public void benachrichtigeBeobachtende() {
		ente.benachrichtigeBeobachtende();
	}

	/**
	 * gibt den typ der ente als String zurueck
	 */
	public String toString() {
		return ente.toString();
	}
}