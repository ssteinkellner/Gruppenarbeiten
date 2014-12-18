
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
	 * gibt eine neue MoorEnte zurueck
	 * @return neue MoorEnte
	 */
	public abstract Quakfaehig erzeugeMoorEnte();
	
	/**
	 * gibt eine neue LockPfeife zurueck
	 * @return neue LockPfeife
	 */
	public abstract Quakfaehig erzeugeLockPfeife();
	
	/**
	 * gibt eine neue GummiEnte zurueck
	 * @return neue GummiEnte
	 */
	public abstract Quakfaehig erzeugeGummiEnte();
}