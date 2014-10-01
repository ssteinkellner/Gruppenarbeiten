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
	 * 
	 * @param array Ein array gefuellt mit Strings
	 * @return  Ein String der alle Elemente des Eingabearrays, getrennt mit dem im Bauplan
	 * 			festgelegten Trennzeichen, beeinhaltet
	 */
	
	protected String getConcatElements(String[] array) {
		String concatParts = "";
		for (int i = 0; i < array.length-1;i++) {
			if (array[i] != null) {
				concatParts += array[i] + this.sekretariat.getBauplan().getDelimiter();
			}
		}
		//concatParts += this.parts[this.parts.length-1];
		return concatParts;
	}
	
}
