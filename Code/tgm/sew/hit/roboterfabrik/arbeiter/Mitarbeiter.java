package tgm.sew.hit.roboterfabrik.arbeiter;

import tgm.sew.hit.roboterfabrik.Sekretariat;

/**
 * 
 * Die Klasse "Mitarbeiter" sorgt dafuer, dass einem neu erstellten Mitarbeiter eine ID vom Sekretariat zugewiesen wird.
 * 
 * @author Stefan Erceg
 * @version 20140927
 *
 */

public abstract class Mitarbeiter implements Runnable {

	private int id;

	protected Sekretariat sekretariat;

	/**
	 * Im Konstruktor wird dem neu erstellten Mitarbeiter eine ID vom Sekretariat zugewiesen.
	 * @param sekretariat sorgt fuer die Zuweisung der ID
	 */
	
	public Mitarbeiter(Sekretariat sekretariat) {
		this.sekretariat = sekretariat;
		this.id = sekretariat.getNewWorkerId();
	}

	/**
	 * Eine getter-Methode wird erstellt, die die aktuelle ID des jeweiligen Mitarbeiters herausliest.
	 * @return aktuelle ID
	 */
	
	public int getId() {
		return this.id;
	}
	
	/**
	 * Haengt mehrere Strings eines Arrays zusammen
	 * @param array Ein array gefuellt mit Strings
	 * @return  Ein String der alle Elemente des Eingabearrays, getrennt mit einem Tabulator, beeinhaltet
	 */
	
	public String getConcatElements(String[] array) {
		//Hängt alle Strings des Arrays zusammen und gibt dazwischen einen Tabulator 
		String concatParts = "";
		if (array != null) {
			for (int i = 0; i < array.length-1;i++) {
				if (array[i] != null) {
					concatParts += array[i]+"\t";
				}
			}
			concatParts += array[array.length-1];
			return concatParts;
		} else {
			return null;
		}
	}
	
}
