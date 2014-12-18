/**
 * Konkrete Entenfabrik
 * @author TLins
 */
public class Entenfabrik extends AbstraktEntenFabrik {
	
	/**
	 * @see AbstraktEntenFabrik#erzeugeStockEnte()
	 */
	public Quakfaehig erzeugeStockEnte() {
		return new StockEnte();
	}
	
	/**
	 * @see AbstraktEntenFabrik#erzeugeMoorEnte()
	 */
	public Quakfaehig erzeugeMoorEnte() {
		return new MoorEnte();
	}
	
	/**
	 * @see AbstraktEntenFabrik#erzeugeLockPfeife()
	 */
	public Quakfaehig erzeugeLockPfeife() {
		return new LockPfeife();
	}
	
	/**
	 * @see AbstraktEntenFabrik#erzeugeGummiEnte()
	 */
	public Quakfaehig erzeugeGummiEnte() {
		return new GummiEnte();
	}
}
