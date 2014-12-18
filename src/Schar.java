import java.util.Iterator;
import java.util.ArrayList;

/**
 * entenschar, die jede menge enten beinhalten kann und alle zum quaken bringt
 * @author TLins<br>SSteinkellner (added generics | 2014.12.18)
 */
public class Schar implements Quakfaehig {
	
	ArrayList<Quakfaehig> quakende = new ArrayList<Quakfaehig>();

	/**
	 * fuegt ein quakfaehiges objekt zur schar hinzu
	 * @param quaker
	 */
	public void hinzufügen(Quakfaehig quaker) {
		quakende.add(quaker);
	}

	/**
	 * laesst alle enten der schar ein mal quaken
	 * @see Quakfaehig#quaken()
	 */
	public void quaken() {
		Iterator<Quakfaehig> iterator = quakende.iterator();
		while (iterator.hasNext()) {
			Quakfaehig quaker = (Quakfaehig)iterator.next();
			quaker.quaken();
		}
	}

	/**
	 * registriert bei jedem objekt in der schar einen beobachter
	 * @see QuakBeobachtungsSubjekt#registriereBeobachter(Beobachter)
	 */
	public void registriereBeobachter(Beobachter beobachter) {
		Iterator<Quakfaehig> iterator = quakende.iterator();
		while (iterator.hasNext()) {
			Quakfaehig quaker = (Quakfaehig)iterator.next();
			quaker.registriereBeobachter(beobachter);
		}
	}

	/**
	 * UNUSED
	 * @see QuakBeobachtungsSubjekt#benachrichtigeBeobachtende()
	 */
	public void benachrichtigeBeobachtende() { }
	
	public String toString() {
		return "Entenschar";
	}
}