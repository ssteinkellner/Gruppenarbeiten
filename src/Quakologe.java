/**
 * implementierter beobachter
 * @author TLins
 */
public class Quakologe implements Beobachter {

	/**
	 * @see Beobachter#aktualisieren(QuakBeobachtungsSubjekt)
	 */
	public void aktualisieren(QuakBeobachtungsSubjekt ente) {
		System.out.println("Quakologe: " + ente + " hat gerade gequakt.");
	}

	/**
	 * gibt den typ des beobachters als String zurueck
	 */
	public String toString() {
		return "Quakologe";
	}
}
