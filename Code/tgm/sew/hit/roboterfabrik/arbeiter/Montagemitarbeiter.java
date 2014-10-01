package tgm.sew.hit.roboterfabrik.arbeiter;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.statisch.Bauplan;


/**Ist fuer die Sortierung der Parts und dadurch die Produktion der Threadees verantwortlich.
 * Dazu holt sich der Montagemitarbeiter vom Lagermitarbeiter die notwendigen Teile, die im
 * Bauplan definiert sind, und sortiert diese und gibt das fertige Produk wieder dem Lagermitarbeiter
 * 
 * @author Martin Kritzl
 * @version 20141001
 *  
 */
public class Montagemitarbeiter extends Mitarbeiter {

	private String[] parts;

	private Lagermitarbeiter auslagerung;
	
	private static Logger logger;

	/**Erzeugt einen neuen Montagemitarbeiter
	 * 
	 * @param Ein Sekretariat welches die notwendigen Objekte anderer Klassen beinhaltet
	 */
	
	public Montagemitarbeiter(Sekretariat sekretariat) {
		super(sekretariat);
		this.auslagerung = new Lagermitarbeiter(sekretariat);
		logger = Logger.getLogger(sekretariat.getBauplan().getLogPath());
		this.parts = new String[6];
	}

	/**Holt sich alle notwendigen Teile vom lagermitarbeiter und gibt sie gleich wieder
	 * zurueck wenn diese nicht vollstaendig sind. Im zweiten Fall wird ebenso eine Pause
	 * eingelegt und auf lagermitarbeiter gewartet
	 * 
	 * @return Ob alle Teile angefordert werden konnten
	 */
	
	public String[] getAllParts() {
		//Es wird die Liste der benötigten Teile vom Bauplan geholt
		String[] needParts = this.sekretariat.getBauplan().getPartNames();
		ArrayList<String[]> gotParts = new ArrayList<String[]>();
		String part = "";
		//Es werden die verschiedenen Bestandteile einzeln angefordert
		for (int i = 0; i < needParts.length; i++) {
			//Wie viele Bestandteile dieser Art werden benötigt
			int currentCount = this.sekretariat.getBauplan().getPartCount(needParts[i]);
			//Holt sich alle Teile dieser Art
			String[] currentParts = this.sekretariat.getLagermitarbeiter().getParts(needParts[i], currentCount);
			//Falls Teile nicht mehr vorhanden sind 
			if (currentParts == null) {
				giveBack(gotParts);
				return null;
			}
			//Die erhaltenen Parts der selben Art werden in gotParts gespeichert
			gotParts.add(currentParts);
			logger.log(Level.INFO, "Montagemitarbeiter ID" + this.getId() + ": Habe folgende Parts erhalten: " + getConcatElements(currentParts));
				//Die Parts werden aber auch in das Attribut gespeichert
				for(String s : gotParts.get(i)) {
					for (int j = 0; j <this.parts.length;j++) {
						if (this.parts[j] == null) {
							this.parts[j] = s;
							break;
						}
					}
			}
		}
		return this.parts;
	}
	
	
	/**
	 * Setzt alle Parts des Attributs auf null
	 */
	
	private void setNull() {
		for (int i = 0;i<this.parts.length;i++) {
			this.parts[i] = null;
		}
	}
	
	/**
	 * Gibt die Teile wieder an den Lagermitarbeiter zurück
	 */
	
	private void giveBack(ArrayList<String[]> addParts) {
		//Gibt alle Parts der gleichen Art dem Lagermitarbeiter zurück
		for (int i = 0; i < addParts.size(); i++) {
			this.sekretariat.getLagermitarbeiter().addParts(addParts.get(i)[0], addParts.get(i));
			logger.log(Level.INFO, "Montagemitarbeiter ID" + this.getId() + ": Gebe folgende Parts zurueck: " + getConcatElements(addParts.get(i)));
		}
		//Parts werden im Attribut gelöscht
		setNull();
		//Montagemitarbeiter tut für eine gewisse Zeit nichts. Diese ist im Bauplan hinterlegt
		logger.log(Level.INFO, "Montagemitarbeiter ID" + this.getId() + ": Frage in " + this.sekretariat.getBauplan().getTimeRetry() + "ms erneut nach Teilen");
		try {
			Thread.sleep(this.sekretariat.getBauplan().getTimeRetry());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**Haengt mehrere Strings eines Arrays zusammen
	 * 
	 * @param array Ein array gefuellt mit Strings
	 * @return  Ein String der alle Elemente des Eingabearrays, getrennt mit dem im Bauplan
	 * 			festgelegten Trennzeichen, beeinhaltet
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
	
	/**
	 * uebergibt dem Dateizugriff den hinzuzufuegenden Threadee
	 */
	
	private void deliverProduct() {
		//Neue Id für einen Threadee wird angefordert und das Produkt wird an den Lagermitarbeiter weitergegeben
		int idThreadee = this.sekretariat.getNewProductId();
		String addThreadee = this.sekretariat.getBauplan().getProduktName() + "-ID" + idThreadee + ", Mitarbeiter-ID" + this.getId()+ "\r\n" + getConcatElements(this.parts)+"\r\n";
		auslagerung.addParts(this.sekretariat.getBauplan().getProduktName(), new String[]{addThreadee});
		logger.log(Level.INFO, "Montagemitarbeiter ID" + this.getId() + ": Threadee Nr. " + idThreadee + " abgeliefert");
		setNull();
	}
	
	/**Sortiert einen Part. Die Nummern des Parts werden aufsteigend sortiert
	 * 
	 * @param part Ein String der einen Part repraesentiert
	 * @return Einen String der einen sortierten/fertigen Part repraesentiert
	 */

	public String sortPart(String part) {
		char delimiter = this.sekretariat.getBauplan().getDelimiter();
		//Der Name des Parts wird gespeichert
		String prefix = part.substring(0, part.indexOf(delimiter));
		//Nur noch die Zahlen und die Trennzeichen sind enthalten
		part = part.substring(part.indexOf(delimiter)+1, part.length());
		ArrayList<Integer> parts = new ArrayList<Integer>();
		//Der Part wird nach dem Trennzeichen gesplitet
		String[] sPart=new String[this.sekretariat.getBauplan().getPartLength()+1];
		sPart=part.split(""+delimiter);
		//Zur ArrayList hinzugefügt und geparst
		try {
			for (int i = 0; i<sPart.length;i++) {
				parts.add(Integer.parseInt(sPart[i]));
			}
		} catch(NumberFormatException e) {
			logger.log(Level.ERROR, "Montagemitarbeiter ID" + this.getId() + ": Fehlerhafter Part(NumberFormatException)");
		}
		//Die Nummern werden sortiert
		Collections.sort(parts);
		//Der Name wird und die Nummern, getrennt mit dem Trennzeichen werden wieder geschrieben
		String sortedPart = prefix + delimiter;
		for (int number : parts) {
			sortedPart += "" + number + delimiter;
		}
		//Trennzeichen am Ende wird entfernt
		sortedPart = sortedPart.substring(0, sortedPart.lastIndexOf(delimiter));
 		return sortedPart;
	}
	
	/**
	 * Holt staendig die erforderlichen Parts, sortiert sie und bringt sie ins Lager
	 */
	
	public void run() {
		//Wiederholt die Schleife so lange bis der Watchdog den Threadpool stoppt
		while(!this.sekretariat.getEmployees().isShutdown()) {
			if (this.getAllParts() != null) {
				for (int i = 0; i < this.parts.length;i++) {
					this.parts[i] = sortPart(this.parts[i]);
				}
				this.deliverProduct();
			}
		}
		
	}

}
