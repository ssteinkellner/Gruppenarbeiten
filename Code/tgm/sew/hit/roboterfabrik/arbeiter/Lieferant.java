package tgm.sew.hit.roboterfabrik.arbeiter;

import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

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
	
	private static Logger logger;

	/**Erzeugt einen neuen Lieferanten und legt einen zufaelligen zu erzeugenden Part fest
	 * 
	 * @param sekretariat
	 */
	public Lieferant(Sekretariat sekretariat) {
		super(sekretariat);
		changePart();
		logger = Logger.getLogger(sekretariat.getBauplan().getLogPath());
	}
	
	/**
	 * aendert zufaellig den Part der geliefert werden soll
	 */

	public void changePart() {
		String[] parts = this.sekretariat.getBauplan().getPartNames();
		int random = new Random().nextInt(parts.length);
		this.currentPart = parts[random];
		logger.log(Level.INFO, "Lieferant " + this.getId() + ": Habe Art des Teils auf " + this.currentPart + " geaendert");
	}
	
	/**
	 * 
	 * @param array Ein array gefuellt mit Strings
	 * @return  Ein String der alle Elemente des Eingabearrays, getrennt mit dem im Bauplan
	 * 			festgelegten Trennzeichen, beeinhaltet
	 */
	
	private String getConcatElements(String[] array) {
		String concatParts = "";
		for (int i = 0; i < array.length-1;i++) {
			if (array[i] != null) {
				concatParts += array[i] + this.sekretariat.getBauplan().getDelimiter();
			}
		}
		//concatParts += this.parts[this.parts.length-1];
		return concatParts;
	}
	

	/**Gibt einen String zurueck, der einen Part repraesentiert
	 * 
	 * @return 	einen String der am Anfang einen Namen eines Parts und darauffolgend 
	 * 			zufaellige Zahlen, getrennt mit dem im Bauplan festgelegten Trennzeichen, 
	 * 			enthaelt. Die Anzahl der Zahlen ist ebenfalls im Bauplan festgelegt
	 */
	
	public String getRandomLine() {
		String part = currentPart;
        for (int i = 0; i < this.sekretariat.getBauplan().getPartLength(); i++) {
            part += this.sekretariat.getBauplan().getDelimiter() + new Random().nextInt(this.sekretariat.getBauplan().getMaxRandomNumber() + 1)+1;  
        }
        return part;
	}
	
	/**
	 * Fuegt staendig zufaellige Parts dem lagermitarbeiter hinzu
	 */
	public void run() {
		while(!this.sekretariat.getEmployees().isShutdown()) {
			if (new Random().nextInt(2)<1) {
				changePart();
			}
			String[] parts = null;
			int counter = 0;
			do {
				parts[counter] = getRandomLine();
				counter++;
			}
			while(new Random().nextInt(20)<1);
			this.sekretariat.getLagermitarbeiter().addParts(this.currentPart, parts);
			logger.log(Level.INFO, "Lieferant " + this.getId() + ": Habe folgende Teile dem Lagermitarbeiter uebergeben: " + getConcatElements(parts));
		}
		
	}

}
