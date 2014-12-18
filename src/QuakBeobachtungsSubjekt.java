/**
 * Interface um alle beobachtbaren quakfaehigen elemente zusammenzufassen
 * @author TLins
 */
public interface QuakBeobachtungsSubjekt {
	
	/**
	 * merkt sich einen beobachter
	 * @param beobachter zu merkender beobachter
	 */
	public void registriereBeobachter(Beobachter beobachter);
	
	/**
	 * sendet eine botschaft an alle beobachter
	 */
	public void benachrichtigeBeobachtende();
}