package interfaces;

/**
 * interface fuer einen balancer, mit register und remove methode
 * (annaeherung ans Observer Pattern)
 * @author SSteinkellner
 * @version 2015.01.07
 */
public interface CalculationBalancer {

	/**
	 * registrierung eines servers, der seinen dienst bereitstellt 
	 * @param calculator
	 */
	public abstract void registerCalculator(Calculator calculator);

	/**
	 * entfernen eines servers, der seinen dienst nicht laenger bereitstellt
	 * @param calculator
	 */
	public abstract void removeCalculator(Calculator calculator);

}
