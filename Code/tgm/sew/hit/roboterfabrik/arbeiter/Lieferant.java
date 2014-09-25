package tgm.sew.hit.roboterfabrik.arbeiter;

import java.util.Random;

import tgm.sew.hit.roboterfabrik.Sekretariat;
/**
 * 
 * @author Martin Kritzl
 * @version 20140925
 * 
 * Ist fuer die Lieferung der einzelnen Teile zustaendig
 */
public class Lieferant extends Mitarbeiter {

	private String currentPart;

	/**Erzeugt einen neuen Lieferanten und legt einen zufaelligen zu erzeugenden Part fest
	 * 
	 * @param sekretariat
	 */
	public Lieferant(Sekretariat sekretariat) {
		super(sekretariat);
		changePart();
	}
	
	/**
	 * aendert zufaellig den Part der geliefert werden soll
	 */

	public void changePart() {
		String[] parts = Bauplan.getParts();
		int random = new Random().nextInt(parts.length);
		this.currentPart = parts[random];
	}

	/**Gibt einen String zurueck, der einen Part repraesentiert
	 * 
	 * @return 	einen String der am Anfang einen Namen eines Parts und darauffolgend 
	 * 			zufaellige Zahlen, getrennt mit dem im Bauplan festgelegten Trennzeichen, 
	 * 			enthaelt. Die Anzahl der Zahlen ist ebenfalls im Bauplan festgelegt
	 */
	
	public String getRandomLine() {
		if (new Random().nextInt(10)<1) {
			changePart();
		}
		String part = currentPart;
        for (int i = 0; i < Bauplan.getPartLength(); i++) {
            part += Bauplan.getDelimiter() + new Random().nextInt(1, Bauplan.getMaxRandomNumber() + 1)+1;  
        }
        return part;
	}
	
	/**
	 * Fuegt staendig zufaellige Parts dem lagermitarbeiter hinzu
	 */
	public void run() {
		while(!this.sekretariat.getEmployees().isShutdown()) {
			String[] parts = null;
			int counter = 0;
			do {
				parts[counter] = getRandomLine();
				counter++;
			}
			while(new Random().nextInt(20)<1);
			this.lagermitarbeiter.addParts(parts);
		}
		
	}

}
