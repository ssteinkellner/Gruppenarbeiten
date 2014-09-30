package tgm.sew.hit.roboterfabrik.arbeiter;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.statisch.Bauplan;


/**
 * 
 * @author Martin Kritzl
 * @version 20140925
 * 
 * Ist fuer die Sortierung der Parts und dadurch die Produktion der Threadees verantwortlich
 */
public class Montagemitarbeiter extends Mitarbeiter {

	private String[] parts;

	private Lagermitarbeiter auslagerung;
	
	private static Logger logger;

	/**Erzeugt einen neuen Montagemitarbeiter
	 * 
	 * @param sekretariat
	 */
	
	public Montagemitarbeiter(Sekretariat sekretariat) {
		super(sekretariat);
		this.auslagerung = new Lagermitarbeiter(sekretariat);
		logger = Logger.getLogger(sekretariat.getBauplan().getLogPath());
	}

	/**Holt sich alle notwendigen Teile vom lagermitarbeiter und gibt sie gleich wieder
	 * zurueck wenn diese nicht vollstaendig sind. Im zweiten Fall wird ebenso eine Pause
	 * eingelegt und auf lagermitarbeiter gewartet
	 * 
	 * @return Ob alle Teile angefordert werden konnten
	 */
	
	public boolean getAllParts() {
		String[] needParts = this.sekretariat.getBauplan().getPartNames();
		ArrayList<String[]> gotParts = new ArrayList<String[]>();
		String part;
		int count = 0;
		for (int i = 0; i < needParts.length; i++) {
			gotParts.add(this.sekretariat.getLagermitarbeiter().getParts(needParts[i], this.sekretariat.getBauplan().getPartCount(needParts[i])));
			logger.log(Level.INFO, "Montagemitarbeiter " + this.getId() + ": Habe folgende Parts erhalten: " + getConcatElements(this.parts));
			if (gotParts.get(i).length == this.sekretariat.getBauplan().getPartCount(needParts[i])) {
				for (int j = 0; j < gotParts.size(); j++) {
					for(String s : gotParts.get(j))
						this.parts[i+j] = s;
						count++;
				}
			} else {
				for (int j = 0; j < gotParts.size(); j++) {
					for(String s : gotParts.get(j))
						this.parts[i+j] = s;
						count++;
				}
				giveBack(gotParts, count);
				return false;
			}
		}
		return true;
	}
	
	/**Gibt die Teile zurück die gerade im Besitz des Montagemitarbeiters sind
	 * 
	 * @return Die Teile die gerade verarbeitet werden
	 */
	
	public String[] getParts() {
		return this.parts;
	}
	
	/**
	 * Gibt die Teile wieder an den Lagermitarbeiter zurück
	 */
	
	private void giveBack(ArrayList<String[]> addParts, int count) {
		
		for (int i = 0; i < count; i++) {
			this.sekretariat.getLagermitarbeiter().addParts(addParts.get(i)[0], addParts.get(i));
		}
		
		this.parts = null;
		logger.log(Level.INFO, "Montagemitarbeiter " + this.getId() + ": Gebe folgende Parts zurueck: " + getConcatElements(this.parts));
		logger.log(Level.INFO, "Montagemitarbeiter " + this.getId() + ": Frage in " + this.sekretariat.getBauplan().getTimeRetry() + "ms erneut nach Teilen");
		try {
			Thread.sleep(this.sekretariat.getBauplan().getTimeRetry());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param array Ein array gefuellt mit Strings
	 * @return  Ein String der alle Elemente des Eingabearrays, getrennt mit dem im Bauplan
	 * 			festgelegten Trennzeichen, beeinhaltet
	 */
	
	public String getConcatElements(String[] array) {
		String concatParts = "";
		for (int i = 0; i < array.length-1;i++) {
			if (array[i] != null) {
				concatParts += array[i] + this.sekretariat.getBauplan().getDelimiter();
			}
		}
		concatParts += array[this.parts.length-1];
		return concatParts;
	}
	
	/**
	 * uebergibt dem Dateizugriff den hinzuzufuegenden Threadee
	 */
	
	private void deliverProduct() {
		String[] addThreadee = new String[2];
		addThreadee[0]=this.sekretariat.getBauplan().getProduktName() + "-ID" + this.sekretariat.getNewProductId() + ", Mitarbeiter-ID" + this.getId();
		addThreadee[1]=getConcatElements(this.parts);
		auslagerung.addParts(this.sekretariat.getBauplan().getDeliverPath(), addThreadee);
		logger.log(Level.INFO, addThreadee[0] + "\r\n" + addThreadee[1]); 
		this.parts = null;
	}
	
	/**Sortiert einen Part
	 * 
	 * @param part Ein String der einen Part repraesentiert
	 * @return Einen String der einen sortierten/fertigen Part repraesentiert
	 */

	public String sortPart(String part) {
		char delimiter = this.sekretariat.getBauplan().getDelimiter();
		String prefix = part.substring(0, part.indexOf(delimiter));
		part = part.substring(part.indexOf(delimiter)+1, part.length());
		ArrayList<Integer> parts = new ArrayList<Integer>();
		try{
			for (int i = 0; i < this.sekretariat.getBauplan().getPartLength()-1;i++){
				parts.add(Integer.parseInt(part.substring(0, part.indexOf(delimiter))));
				part = part.substring(part.indexOf(delimiter)+1, part.length());
			}
			parts.add(Integer.parseInt(part.substring(0, part.length())));
		} catch(NumberFormatException e) {
			logger.log(Level.ERROR, "Montagemitarbeiter " + this.getId() + ": Fehlerhafter Part(NumberFormatException)");
		}
		Collections.sort(parts);
		String sortedPart = prefix + delimiter;
		for (int number : parts) {
			sortedPart += number + delimiter;
		}
		sortedPart = sortedPart.substring(0, sortedPart.lastIndexOf(delimiter));
		return sortedPart;
	}
	
	/**
	 * Holt staendig die erforderlichen Parts, sortiert sie und bringt sie ins Lager
	 */
	
	public void run() {
		while(!this.sekretariat.getEmployees().isShutdown()) {
			if (getAllParts()) {
				for (int i = 0; i < this.parts.length;i++) {
					parts[i] = sortPart(parts[i]);
				}
				deliverProduct();
			}
		}
		
	}

}
