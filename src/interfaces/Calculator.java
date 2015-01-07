package interfaces;
import java.math.BigDecimal;
/**
 * Interface aus der Aufgabenstellung
 * <br>"Als Dienst soll hier die beliebig genaue Bestimmung von pi betrachtet werden"
 * @author TMicheler, MBorko
 */
public interface Calculator {

	/**
	 * gibt pi beliebig genau zurueck
	 * @param anzahl_nachkommastellen anzahl der stellen von pi, die hinter dem komma berechnet werden sollen
	 * @return pi mit beliebig vielen nachkommastellen
	 */
	public abstract BigDecimal pi(int anzahl_nachkommastellen);

}
