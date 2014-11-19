/**
 * Zitat aus der Angabe: "Diese Funktionalitaet soll aber im Interface jederzeit aktiviert und deaktiviert werden können".
 * Spezifikationen von Translator und BadWordFilter sollen aktiviert bzw. deaktiviert werden koeen.
 * @author Alexander Koelbl
 * @version 2014.11.19
 */
public interface Activatable {
	/**
	 * Setter Methode, mit der eine bestimmte Funktion ein- bzw. ausgeschaltet werden kann
	 * @param active wenn true, dann ist die Funktion aktiv, ansonsten nicht.
	 */
	public abstract void setEnabled(boolean active);

}
