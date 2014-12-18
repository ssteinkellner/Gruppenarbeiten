
/**
 * Abstrakte Entenfabrik fuer das Factory Pattern
 * @author TLins
 */
public abstract class AbstraktEntenFabrik {
	
	/**
	 * gibt eine neue StockEnte zurueck
	 * @return neue StockEnte
	 */
	public abstract Quakfaehig erzeugeStockEnte();
	
	/**
	 * gibt eine neue StockEnte zurueck
	 * @return neue StockEnte
	 */
	public abstract Quakfaehig erzeugeMoorEnte();
	
	/**
	 * gibt eine neue StockEnte zurueck
	 * @return neue StockEnte
	 */
	public abstract Quakfaehig erzeugeLockPfeife();
	
	/**
	 * gibt eine neue StockEnte zurueck
	 * @return neue StockEnte
	 */
	public abstract Quakfaehig erzeugeGummiEnte();
}