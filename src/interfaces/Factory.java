package interfaces;

import exceptions.NotAvailableException;

/**
 * interface, fuer alle factorys, die vom user aufgerufen werden koennen
 * @author SSteinkellner
 * @version 2015.01.07
 */
public interface Factory {

	/**
	 * erstellt ein objekt mit dem uebergebenen namen
	 * @param name name es objekts
	 * @return neues objekt mit dem namen
	 * @throws NotAvailableException falls das objekt mit dem uebergebenen namen nicht erzeugt werden kann
	 */
	public abstract Createable create(String name) throws NotAvailableException;

	/**
	 * gibt eine liste aller bekannter namen zurueck
	 * (um die verwendung zu erleichtern)
	 * @return liste aller bekannter namen
	 */
	public abstract String getTypeList();

}
