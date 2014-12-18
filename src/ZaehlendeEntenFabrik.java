/**
 * Konkrete Entenfabrik, die zaehlbare enten zurueck gibt
 * @author TLins
 */
public class ZaehlendeEntenFabrik extends AbstraktEntenFabrik {

	/**
	 * @see AbstraktEntenFabrik#erzeugeStockEnte()
	 */
	public Quakfaehig erzeugeStockEnte() {
		return new QuakZaehler(new StockEnte());
	}
	
	/**
	 * @see AbstraktEntenFabrik#erzeugeMoorEnte()
	 */
	public Quakfaehig erzeugeMoorEnte() {
		return new QuakZaehler(new MoorEnte());
	}
	
	/**
	 * @see AbstraktEntenFabrik#erzeugeLockPfeife()
	 */
	public Quakfaehig erzeugeLockPfeife() {
		return new QuakZaehler(new LockPfeife());
	}

	/**
	 * @see AbstraktEntenFabrik#erzeugeGummiEnte()
	 */
	public Quakfaehig erzeugeGummiEnte() {
		return new QuakZaehler(new GummiEnte());
	}
}
