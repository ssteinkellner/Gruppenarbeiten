import java.util.Iterator;
import java.util.ArrayList;

/**
 * @author TLins<br>SSteinkellner (added generics | 2014.12.18)
 */
public class SenderRing implements QuakBeobachtungsSubjekt {
	
	ArrayList<Beobachter> beobachtende = new ArrayList<Beobachter>();
	QuakBeobachtungsSubjekt ente;

	/**
	 * erstellt einen senderring mit der uebergebenen ente
	 * @param ente ente
	 */
	public SenderRing(QuakBeobachtungsSubjekt ente) {
		this.ente = ente;
	}
	
	/**
	 * @see QuakBeobachtungsSubjekt#registriereBeobachter(Beobachter)
	 */
	public void registriereBeobachter(Beobachter beobachter) {
		beobachtende.add(beobachter);
	}

	/**
	 * benachrichtigt alle registrierten beobachter
	 * @see QuakBeobachtungsSubjekt#benachrichtigeBeobachtende()
	 */
	public void benachrichtigeBeobachtende() {
		Iterator<Beobachter> iterator = beobachtende.iterator();
		while (iterator.hasNext()) {
				Beobachter beobachter = (Beobachter)iterator.next();
				beobachter.aktualisieren(ente);
		}
	
	}
	
	/**
	 * gibt einen iterator der registrierten beobachter zurueck
	 * @return iterator der beobachter
	 */
	public Iterator<Beobachter> getBeobachtende() {
		return beobachtende.iterator();
	}
}